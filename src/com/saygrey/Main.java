package com.saygrey;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        (new Thread(new Server())).start();
        Thread.sleep(300);
        (new Thread(new Client())).start();
    }
}