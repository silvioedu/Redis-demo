package com.example;

import redis.clients.jedis.Jedis;

public class ObterDoisPrimeirosNumeros {

    static Jedis jedis;

    public String buscarDoisPrimeirosDigitos(){
        String chave = "resultado:megasena";
        return jedis.getrange(chave, 0, 4);
    }

    public static void main(String[] args) {
        jedis = new JedisConnectionFactory().getConnection();
        
        String resultado = new ObterDoisPrimeirosNumeros().buscarDoisPrimeirosDigitos();
        System.out.println(resultado);

        jedis.close();
    }
}
