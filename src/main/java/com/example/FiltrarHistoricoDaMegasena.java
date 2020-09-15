package com.example;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class FiltrarHistoricoDaMegasena {
    
    static Jedis jedis;

    public Set<String> filtrarResultados(int mes, int ano){
        String chave = "resultado:*-%02d-%04d:megasena";
        return jedis.keys(String.format(chave, mes, ano));
    }

    public static void main(String[] args) {
        jedis = new JedisConnectionFactory().getConnection();
        
        int mes = 10;
        int ano = 2013;

        Set<String> chaves = new FiltrarHistoricoDaMegasena().filtrarResultados(mes, ano);

        System.out.println(chaves);
        jedis.close();
    }
}
