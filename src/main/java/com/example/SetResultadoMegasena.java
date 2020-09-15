package com.example;

import redis.clients.jedis.Jedis;

public class SetResultadoMegasena {

    static Jedis jedis;
    static String resultado;
    static final String CHAVE_FORMAT = "resultado:%s:megasena";

    public static void main( String[] args ){
        jedis = new JedisConnectionFactory().getConnection();

        String chave = "resultado:megasena";
        String numerosDoUltimoSorteio = "2, 13, 24, 41, 42, 44";
        String resultado = jedis.set(chave, numerosDoUltimoSorteio);
        System.out.println(resultado);

        String dataDoSorteio1 = "04-09-2013";
        String numerosDoSorteio1 = "10, 11, 18, 42, 55, 56";
        String chaveDoSorteio1 = String.format(CHAVE_FORMAT,dataDoSorteio1);

        String dataDoSorteio2 = "07-09-2013";
        String numerosDoSorteio2 = "2, 21, 30, 35, 45, 50";
        String chaveDoSorteio2 = String.format(CHAVE_FORMAT,dataDoSorteio2);

        String dataDoSorteio3 = "21-09-2013";
        String numerosDoSorteio3 = "2, 13, 24, 41, 42, 44";
        String chaveDoSorteio3 = String.format(CHAVE_FORMAT,dataDoSorteio3);

        String dataDoSorteio4 = "02-10-2013";
        String numerosDoSorteio4 = "7, 15, 20, 23, 30, 41";
        String chaveDoSorteio4 = String.format(CHAVE_FORMAT,dataDoSorteio4);

        resultado = jedis.mset(chaveDoSorteio1, numerosDoSorteio1,
                               chaveDoSorteio2, numerosDoSorteio2,
                               chaveDoSorteio3, numerosDoSorteio3,
                               chaveDoSorteio4, numerosDoSorteio4);
        
        System.out.println(resultado);

        jedis.close();
    }

}
