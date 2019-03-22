package com.gjw.netty.demo.socket.server.config;

public interface FinalConfig {
    byte HEAD = (byte) 0xaa;
    byte END1 = (byte) 0x0d;//'\r'
    byte END2 = (byte) 0x0a;//'\n'
    int BASE_LENGTH = 8;
    String CHARSET = "utf-8";
    String HEART_BEAT = "h_t";
}
