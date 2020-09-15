package com.example;

import redis.clients.jedis.Jedis;

public class GerarEstatisticaDePaginaVisitada {
    
    static Jedis jedis;
    static String chave;

    public static void main(String[] args) {
        jedis = new JedisConnectionFactory().getConnection();

        gerarDados();
        jedis.close();
    }

    public static void gerarEstatistica(String pagina, String data){
        chave = String.format("pagina:%s:%s", pagina, data);
        String mensagemFormatada = "Página %s teve %d acesso(s) em %s";
        String mensagem;

        long resultado = 0;
        //Double resultadoDouble = 0;
        //if (numInteger == 0 && numFloat == 0){
            resultado = jedis.incr(chave);
            mensagem = String.format(mensagemFormatada, pagina, resultado, data);
        //} else if (numInteger != 0){
        //    resultado = jedis.incrBy(chave, numInteger);
        //    mensagem = String.format(mensagemFormatada, pagina, resultado, data);
        //} else if (numFloat != 0){
        //    resultadoDouble = jedis.incrByFloat(chave, numFloat);
        //    mensagem = String.format(mensagemFormatada, pagina, resultadoDouble, data);
        //}

        System.out.println(mensagem);
    }

    public static void gerarDados(){
        String data = "02/09/2013";
        String[] paginasVisitadas = {
            "/inicio",
            "/contato",
            "/sobre-mim",
            "/todos-os-posts",
            "/armazenando-dados-no-redis"
        };

        GerarEstatisticaDePaginaVisitada gerador = new GerarEstatisticaDePaginaVisitada();

        gerador.gerarEstatistica(paginasVisitadas[0], data);
        gerador.gerarEstatistica(paginasVisitadas[1], data);
        gerador.gerarEstatistica(paginasVisitadas[2], data);
        gerador.gerarEstatistica(paginasVisitadas[1], data);
        gerador.gerarEstatistica(paginasVisitadas[1], data);

        gerador.gerarEstatistica(paginasVisitadas[1], data);

    }
}
