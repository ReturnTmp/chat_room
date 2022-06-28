package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public ChatServer(int port){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true){
                Thread.sleep(300);
                Socket client=serverSocket.accept();
                ClientThread clientThread = new ClientThread(client);
                clientThread.start();
                System.out.println(client.getLocalAddress());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new ChatServer(9000);
    }
}
