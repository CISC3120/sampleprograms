package edu.cuny.brooklyn.cisc3120.netio;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PingPongClient {
    private final static Logger LOGGER = LoggerFactory.getLogger(PingPongServer.class);
    
    private final static int SERVER_PORT = 60101;
    private final static String SERVER_ADDRESS = "127.0.0.1";
    private final static int BUFFER_LENGTH = 1024;

    public static void main( String[] args )
    {
        LOGGER.info("C: client starts.");
        SocketAddress address = null;
        try {
            address = new InetSocketAddress(InetAddress.getByName(SERVER_ADDRESS), SERVER_PORT);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(-1);
        }
        
        try (DatagramSocket socket = new DatagramSocket()) {
            byte buf[] = new byte[BUFFER_LENGTH];
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address);
            
            packet.setData("Knock, knock.".getBytes());
            socket.send(packet);
            LOGGER.debug("C >>>> S: " + new String(packet.getData()));

            /* you can resue the packet object, but make sure the receiving buffer is set/reset;
             * other wise received data may get truncated due to length set from previous 
             * operation */
            packet.setData(buf);
            socket.receive(packet);
            LOGGER.debug("C <<<< S: " + new String(packet.getData()));
            
            packet.setData("Anna Partridge.".getBytes());
            socket.send(packet);
            LOGGER.debug("C >>>> S: " + new String(packet.getData()));

            /* you can resue the packet object, but make sure the receiving buffer is set/reset;
             * other wise received data may get truncated due to length set from previous 
             * operation */
            packet.setData(buf);
            socket.receive(packet);
            LOGGER.debug("C <<<< S: " + new String(packet.getData()));
            packet.setData("Anna Partridge in a pear tree!".getBytes());

            socket.send(packet);
            LOGGER.debug("C >>>> S: " + new String(packet.getData()));
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
}
