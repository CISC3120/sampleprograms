package edu.cuny.brooklyn.cisc3120.netio;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class FileDownloaderServer 
{
    private final static int PORT = 60101;
    private final static int BACKLOG = 5;
    private final static String ADDRESS = "127.0.0.1";
    
    
    public static void main(String[] args)
    {
        ServerSocket serverSocket = null;
        FileInputStream in = null;
        OutputStream out = null;
        try {
            InetAddress address = InetAddress.getByName(ADDRESS);
            serverSocket = new ServerSocket(PORT, BACKLOG, address);
            System.out.format("Server is running at port %s%n", PORT);
            Socket socket = null;
            while ((socket = serverSocket.accept()) != null) {
                in = new FileInputStream("files/demo1/file1.txt");
                out = socket.getOutputStream();
                int c;
                while ((c = in.read()) != -1) {
                    out.write((byte)c);
                }
            }
            if (out != null) {
                out.close();
                out = null;
            }
            if (in != null) {
                in.close();
                in = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }

}
