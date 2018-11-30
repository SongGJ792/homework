package com.songgj.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(2459);
        System.out.println("服务已启动，等待连接...");
        //创建线程池，供多个客户端使用
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        //创建集合，存放用户信息存放集合中
        ConcurrentHashMap<Socket, SocketAddress> map = new ConcurrentHashMap<>();

        while (true) {
            //等待用户连接
            Socket accept = s.accept();
            System.out.println();
            //获取用户端地址，将用户端和用户端地址存放集合中
            map.put(accept, accept.getRemoteSocketAddress());

            poolExecutor.submit(() -> {
                try {
                    InputStream in = accept.getInputStream();
                    //获取用户地址
                    SocketAddress socketAddress = accept.getRemoteSocketAddress();
                    byte[] buf = new byte[1024];
                    int len = 0;
                    while ((len = in.read(buf)) != -1) {

                        String content = socketAddress + ":" + new String(buf, 0, len);
                        for (Socket socket : map.keySet()) {
                            OutputStream outputStream = socket.getOutputStream();
                            outputStream.write(content.getBytes());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });


        }

    }
}
