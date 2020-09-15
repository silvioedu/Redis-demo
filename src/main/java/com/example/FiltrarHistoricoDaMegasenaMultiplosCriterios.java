package com.example;

import java.util.List;

import redis.clients.jedis.Jedis;

public class FiltrarHistoricoDaMegasenaMultiplosCriterios {
    
    static Jedis jedis;

    public List<String> filtrarResultados(){
        String[] chaves = new String[5];
        chaves[0] = "resultado:02-10-2013:megasena";
        chaves[1] = "resultado:21-09-2013:megasena";
        chaves[2] = "resultado:07-09-2013:megasena";
        chaves[3] = "resultado:04-09-2013:megasena";
        chaves[4] = "resultado:megasena";
        return jedis.mget(chaves);
    }

    public static void main(String[] args) {
        jedis = new JedisConnectionFactory().getConnection();

        List<String> chaves = new FiltrarHistoricoDaMegasenaMultiplosCriterios().filtrarResultados();

        System.out.println(chaves);
        jedis.close();
    }
}
