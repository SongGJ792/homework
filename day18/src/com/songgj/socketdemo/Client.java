package com.songgj.socketdemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 7000);

        OutputStream out = socket.getOutputStream();
        new Thread(() -> {
            try {
                Scanner sc = new Scanner(System.in);
                if (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    out.write(line.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();

        InputStream in = socket.getInputStream();

        byte[] buf = new byte[1024 * 8];
        int len = 0;
        while ((len = in.read(buf)) != -1) {
            String result = new String(buf, 0, len, "UTF-8");
            System.out.println(result);
        }
    }
}
