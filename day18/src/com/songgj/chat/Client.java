package com.songgj.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 2459);

        new Thread(() -> {
            try {
                InputStream in = socket.getInputStream();
                byte[] buf = new byte[1024];
                int len = 0;
                while ((len = in.read(buf)) != -1) {
                    System.out.println(new String(buf, 0, len));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String content = sc.nextLine();
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(content.getBytes());
        }

    }
}
