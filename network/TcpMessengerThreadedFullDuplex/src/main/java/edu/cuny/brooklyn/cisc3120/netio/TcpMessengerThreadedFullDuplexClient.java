package edu.cuny.brooklyn.cisc3120.netio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class TcpMessengerThreadedFullDuplexClient {
    public final static String SERVER_ADDRESS = "127.0.0.1";
    public final static int SERVER_PORT = 62110;

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Client stats.");
        try (Socket socket = new Socket()) {
            SocketAddress serverSocketAddress = new InetSocketAddress(InetAddress.getByName(SERVER_ADDRESS),
                    SERVER_PORT);
            socket.connect(serverSocketAddress);
            Thread inputThread = new Thread(new InputMsgHandlerRunnable("Client", socket));
            inputThread.start();
            Thread outputThread = new Thread(new OutputMsgHandlerRunnable("Client", socket));
            outputThread.start();
            // block the try-with-resources block before threads exit.
            inputThread.join();
            outputThread.join();
        }
    }
}
