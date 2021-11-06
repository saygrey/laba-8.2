package com.saygrey;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client implements Runnable {
    public void run() {
        int serverPort = 4041;
        String address = "127.0.0.1";
        String line;
        byte[] bytes = new byte[16 * 1024];


        try {
            InetAddress ipAddress = InetAddress.getByName(address); // создаем объект который отображает вышеописанный IP-адрес.
            System.out.println("CLIENT: Socket with IP address " + address + " and port " + serverPort + "?");
            Socket socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.
            System.out.println("CLIENT: Exists");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            System.out.println("CLIENT: Type file adress");
            //File file = new File(new Scanner(System.in).nextLine());
            File file =new File("C:\\Users\\Dima\\Desktop\\test\\50RubaiToSend.txt");

            FileInputStream fin = new FileInputStream(file);
            fin.read(bytes);
            fin.close();
            System.out.println("CLIENT: Sending this file...");
            out.write(bytes);
            out.flush();
            line = in.readUTF();
            System.out.println("CLIENT: Server sent this : " + System.lineSeparator() + line);
            in.close();
            out.close();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}