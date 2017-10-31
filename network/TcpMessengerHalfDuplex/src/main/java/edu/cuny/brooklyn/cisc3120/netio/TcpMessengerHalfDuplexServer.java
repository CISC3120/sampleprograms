package edu.cuny.brooklyn.cisc3120.netio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpMessengerHalfDuplexServer 
{
    private final static int SERVER_LISTENING_PORT = 62110;
    private final static int SERVER_BACKLOG = 5;
    private final static String SERVER_BIND_ADDRESS = "0.0.0.0";
    
    public static void main( String[] args ) throws IOException
    {
        System.out.println("Server stats.");
        InetAddress serverBindAddress = InetAddress.getByName(SERVER_BIND_ADDRESS);
        try (ServerSocket listeningSocket = new ServerSocket(SERVER_LISTENING_PORT, SERVER_BACKLOG, serverBindAddress)) {
            try (Socket socketCommWithClient = listeningSocket.accept();
                    PrintWriter writer 
                        = new PrintWriter(
                                new BufferedWriter(
                                        new OutputStreamWriter(socketCommWithClient.getOutputStream())));
                    BufferedReader reader
                        = new BufferedReader(
                                new InputStreamReader(socketCommWithClient.getInputStream()));
                    Scanner scanner = new Scanner(System.in);) {
                writer.println("Hello, client. I am the server.");
                writer.flush();
                System.out.println("Server: " + "Hello, client. I am the server.");
                String msg;
                while ((msg = reader.readLine()) != null) {
                    if (msg.equals("Bye bye.")) {
                        break;
                    }
                    System.out.println("Client: " + msg);
                    System.out.print("Server: ");
                    msg = scanner.nextLine();
                    writer.println(msg);
                    writer.flush();
                }
            }
        }
    }
}
