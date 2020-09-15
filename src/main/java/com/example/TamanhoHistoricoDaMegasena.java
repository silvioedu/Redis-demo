package com.example;

import redis.clients.jedis.Jedis;

public class TamanhoHistoricoDaMegasena {

    static Jedis jedis;

    public Long identificarTamanhoResultado(String chave){
        return jedis.strlen(chave);
    }

    public static void main(String[] args) {
        jedis = new JedisConnectionFactory().getConnection();
        
        String chave = "resultado_megasena";
        Long resultado = new TamanhoHistoricoDaMegasena().identificarTamanhoResultado(chave);
        System.out.println(chave + " -> " + resultado);

        chave = "resultado:megasena";
        resultado = new TamanhoHistoricoDaMegasena().identificarTamanhoResultado(chave);
        System.out.println(chave + " -> " + resultado);


        jedis.close();
    }
    
}
