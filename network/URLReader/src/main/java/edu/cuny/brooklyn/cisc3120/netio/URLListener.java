package edu.cuny.brooklyn.cisc3120.netio;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class URLListener {
    private final static int PORT = 61235; 
    private final static int BACKLOG = 5;
    private final static String IP_ADDRESS = "0.0.0.0";

    
    public static void main(String[] args) throws UnknownHostException, IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT, BACKLOG, InetAddress.getByName(IP_ADDRESS))) {
            System.out.println("Started server at port " + PORT);
            try (Socket socket = serverSocket.accept()) {
                System.out.println(String.format("Accepted connection from %s at %d", socket.getRemoteSocketAddress().toString(), socket.getLocalPort()));
                try(InputStream in = socket.getInputStream()) {
                    int c;
                    System.out.println("Started reading reqeust data: ");
                    while ((c = in.read()) != -1) {
                        System.out.print((char)c);
                    }
                    System.out.println("Read the end of the request");
                }
            }
        }
    }

}
