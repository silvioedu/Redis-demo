package com.example;

import redis.clients.jedis.Jedis;

public class ObterHash {
 
    static Jedis jedis;
    static final String GANHADORES_STRING = "ganhadores";
    static final String NUMEROS_STRING = "numeros"; 
    static final String CHAVE_STRING = "resultado:09-11-2013:megasena";

    public static void main(String[] args) {
        jedis = new JedisConnectionFactory().getConnection();
        setHash();
        getHash();
        delHash();
        existsHash();
        lenHash();
    }

    public static void setHash(){
        String ganhadores = "22";
        String numeros = "8, 18, 26, 42, 56, 58";

        long resultado1 = jedis.hset(CHAVE_STRING, GANHADORES_STRING, ganhadores);
        long resultado2 = jedis.hset(CHAVE_STRING, NUMEROS_STRING, numeros);
        String mensagem = String.format("Resultado 1 = %d, Resultado 2 = %d", resultado1, resultado2);

        System.out.println(mensagem);
    }

    public static void getHash(){
        
        String ganhadores = jedis.hget(CHAVE_STRING, GANHADORES_STRING);
        String numeros = jedis.hget(CHAVE_STRING, NUMEROS_STRING);

        String mensagem = String.format("Ganhadores = %s, Numeros = [%s]", ganhadores, numeros);
        System.out.println(mensagem);
    }

    public static void delHash(){
        Long resultado = jedis.hdel(CHAVE_STRING, GANHADORES_STRING);
        String mensagem = String.format("Apagando campo 'ganhadores' = %d",resultado);
        System.out.println(mensagem);
    }

    public static void existsHash(){
        Boolean resultado = jedis.hexists(CHAVE_STRING, GANHADORES_STRING);
        String mensagem = String.format("Existe campo 'ganhadores'? = %s",resultado);
        System.out.println(mensagem);
    }

    public static void lenHash(){
        Long resultado = jedis.hlen(CHAVE_STRING);
        String mensagem = String.format("Quantos campos existem no hash? = %d",resultado);
        System.out.println(mensagem);
    }
}
