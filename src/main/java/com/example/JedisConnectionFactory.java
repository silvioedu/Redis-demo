package com.example;

import redis.clients.jedis.Jedis;

public class JedisConnectionFactory {
    
    public Jedis getConnection(){
        return new Jedis("localhost");
    }
}
