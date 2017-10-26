package edu.cuny.brooklyn.cisc3120.netio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class FileDownloaderClient {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 60101;
    
    public static void main(String[] args) {
        Socket socket = new Socket();
        InputStream in = null;
        FileOutputStream out = null;
        try {
            SocketAddress serverEndPoint = new InetSocketAddress(InetAddress.getByName(SERVER_ADDRESS), SERVER_PORT);
            socket.connect(serverEndPoint);
            in = socket.getInputStream();
            out = new FileOutputStream("files/demo1/file1dup.txt");
            int c;
            while((c = in.read()) != -1) {
                out.write((byte)c);
            }
            System.out.println("File is downloaded to files/demo1/file1dup.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
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
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
