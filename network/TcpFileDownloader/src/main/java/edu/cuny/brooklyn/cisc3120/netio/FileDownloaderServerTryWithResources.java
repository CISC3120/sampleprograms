package edu.cuny.brooklyn.cisc3120.netio;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileDownloaderServerTryWithResources 
{
    private final static int PORT = 60101;
    private final static int BACKLOG = 5;
    private final static String ADDRESS = "127.0.0.1";
    
    
    public static void main(String[] args)
    {
        InetAddress address = null;
        try {
            address = InetAddress.getByName(ADDRESS);
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage() + " Check the address: " + ADDRESS);
        }
        
        
        try (ServerSocket serverSocket = new ServerSocket(PORT, BACKLOG, address)) {
            System.out.format("Server is running at port %s%n", PORT);
            while (true) {
                try (Socket socket = serverSocket.accept();
                        FileInputStream in = new FileInputStream("files/demo1/file1.txt");
                        OutputStream out = socket.getOutputStream()) {
                    int c;
                    while ((c = in.read()) != -1) {
                        out.write((byte) c);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

}
