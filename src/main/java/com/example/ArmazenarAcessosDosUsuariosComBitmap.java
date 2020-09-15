package com.example;

import java.util.Random;

import redis.clients.jedis.Jedis;

public class ArmazenarAcessosDosUsuariosComBitmap {
    
    static Jedis jedis;

    public static void armazenar(long codigoDoUsuario, String data) {
        jedis = new JedisConnectionFactory().getConnection();
    
        String chave = String.format("acesso:%s", data);
        jedis.setbit(chave, codigoDoUsuario, true);
    }
  
    public static void main(String[] args) {
        int quantidadeDeUsuarios = 500;
        int quantidadeDeAcessos = 1000;
        int quantidadeDeDias = 30;
        
        Random random = new Random();
        ArmazenarAcessosDosUsuariosComBitmap acesso = new ArmazenarAcessosDosUsuariosComBitmap();

        for (Integer numero=1; numero<=quantidadeDeAcessos; numero++) {
            long usuario = (random.nextInt(quantidadeDeUsuarios) + 1);

            String data = String.format("%02d/11/2013", (random.nextInt(quantidadeDeDias) + 1));
            acesso.armazenar(usuario, data);
        }
    }
}