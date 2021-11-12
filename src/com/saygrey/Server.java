package com.saygrey;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Server implements Runnable{
    public void run()    {
        int port = 4041;
        ArrayList<String> rubais = new ArrayList<>();
        String line;
        String rubai = "";
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("SERVER: Waiting for a client...");

            Socket socket = ss.accept();
            System.out.println("SERVER: Got a client");

            File serverFile = new File("serverFile.txt");

            InputStream in = socket.getInputStream();
            FileOutputStream fos = new FileOutputStream(serverFile);
            DataOutputStream out = new DataOutputStream( socket.getOutputStream());

            FileReader fr = new FileReader(serverFile);
            BufferedReader reader = new BufferedReader(fr);

            byte[] bytes = new byte[16 * 1024];
            in.read(bytes);
            fos.write(bytes);
            fos.flush();
            line=reader.readLine();
            while (line != null) {
                rubai+=line+System.lineSeparator();
                if(line.isEmpty()){
                   rubais.add(rubai);
                   rubai="";
               }
                line = reader.readLine();
            }
            rubais.add(rubai);

            System.out.println("SERVER: File received");
            System.out.println("SERVER: Sending rubai from him");
            int x= (int)(Math.random()*50);
            out.writeUTF(rubais.get(x));
            in.close();
            out.close();
            fos.close();

        } catch(Exception x) { x.printStackTrace(); }
    }
}