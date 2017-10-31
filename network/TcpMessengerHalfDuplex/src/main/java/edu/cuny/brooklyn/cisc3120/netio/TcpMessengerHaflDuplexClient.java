package edu.cuny.brooklyn.cisc3120.netio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class TcpMessengerHaflDuplexClient {
    public final static String SERVER_ADDRESS = "127.0.0.1";
    public final static int SERVER_PORT = 62110;

    public static void main(String[] args) throws IOException {
        System.out.println("Client stats.");
        try (Socket socket = new Socket()) {
            SocketAddress serverSocketAddress = new InetSocketAddress(InetAddress.getByName(SERVER_ADDRESS),
                    SERVER_PORT);
            socket.connect(serverSocketAddress);
            try (PrintWriter writer = new PrintWriter(
                    new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    Scanner scanner = new Scanner(System.in)) {
                String msg;
                System.out.println("Client waiting for msg");
                while ((msg = reader.readLine()) != null) {
                    System.out.println("Server: " + msg);
                    System.out.print("Client: ");
                    msg = scanner.nextLine();
                    writer.println(msg); writer.flush();
                }
            }
        }
    }
}
