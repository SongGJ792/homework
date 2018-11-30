package com.songgj.socketdemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(7000);
        System.out.println("服务已启动，等待连接...");

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(15, 15, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("客户端已连接");

            threadPoolExecutor.submit(() -> {
                try {
                    handle(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }


    }

    private static void handle(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();

        OutputStream out = socket.getOutputStream();

        byte[] buf = new byte[1024 * 8];
        int len = 0;
        while ((len = in.read(buf)) != -1) {
            String echo = new String(buf, 0, len, "UTF-8");
            System.out.println(echo);
            out.write(("服务器应答：" + echo).getBytes("UTF-8"));
        }
    }
}
