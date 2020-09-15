package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class ExpirarChave {

    static Jedis jedis;
    static String chave;

    public static void main(String[] args) {
        jedis = new JedisConnectionFactory().getConnection();

        setMultHash();
        verificarTTL();
        configurarExpiracaoSegundos();
        verificarTTL();
        removerExpiracao();
        verificarTTL();
        obterCampos();
        configurarExpiracaoMilisegundos();

        jedis.close();
    }

    public static void setMultHash(){
        final String codigoDoUsuario = "1962";
        final String nomeDoUsuario = "Peter Parker";
        final String emailDoUsuario = "spidey@marvel.com";

        chave = "usuario:" + codigoDoUsuario + ":sessao";

        Map<String,String> campos = new HashMap<String,String>() {{
            put("codigo", codigoDoUsuario);
            put("nome", nomeDoUsuario);
            put("email", emailDoUsuario);
        }};

        String resultado = jedis.hmset(chave, campos);
        System.out.println(resultado);
    }

    public static void verificarTTL(){
        Long resultado = jedis.ttl(chave);
        
        String descricaoResultado = "Configurado";
        if (resultado == -1) {
            descricaoResultado = "Não possui tempo de expiração";
        } else if(resultado == -2) {
            descricaoResultado = "Não localizado";
        }

        String mensagem = String.format("TTL esta %d (%s)", resultado,descricaoResultado);
        System.out.println(mensagem);
    }  
    
    public static void configurarExpiracaoSegundos() {
        int minutos = 30;
        int segundos = minutos * 60;

        Long resultado = jedis.expire(chave, segundos);
        String mensagem = String.format("Expiracao configurada para %02d minutos (%d segundos) = %d", minutos, segundos, resultado);
        System.out.println(mensagem);
    }

    public static void removerExpiracao() {
        Long resultado = jedis.persist(chave);
        String mensagem = String.format("Remover expiracao = %d", resultado);
        System.out.println(mensagem);
    }

    public static void obterCampos() {
        List<String> resultado = jedis.hmget(chave, "codigo", "nome", "email");
        String mensagem = String.format("Campos = %s", resultado);
        System.out.println(mensagem);
    }

    public static void configurarExpiracaoMilisegundos() {
        int minutos = 30;
        long milisegundos = minutos * 60 * 1000L;
        System.out.println(milisegundos);

        Long resultado = jedis.pexpire(chave, milisegundos);
        String mensagem = String.format("Expiracao configurada para %02d minutos (%d milisegundos) = %d", minutos, milisegundos, resultado);
        System.out.println(mensagem);
    }

}
