package edu.cuny.brooklyn.cisc3120.project.game;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.cisc3120.project.game.net.StatusMessage;

public class UdpStatusMessageReceiverTester 
{
    private final static Logger LOGGER = LoggerFactory.getLogger(UdpStatusMessageReceiverTester.class);
    
    private final static int PORT = 62017;
    private final static String ADDRESS = "0.0.0.0";
    
    private final static int BUFFER_SIZE = 1024;
    
    public static void main( String[] args ) throws ClassNotFoundException
    {
        LOGGER.info("UdpStatusMessageReceiverTester starts.");
        SocketAddress address = null;
        try {
            address = new InetSocketAddress(InetAddress.getByName(ADDRESS), PORT);
        } catch (UnknownHostException e) {
            LOGGER.error(String.format("Address or name %s is not a valid address or name", ADDRESS), e);
            System.exit(-1);
        }
        
        try (DatagramSocket socket = new DatagramSocket(address)) {
            byte buf[] = new byte[BUFFER_SIZE];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            for (int i=0; i<100; i++) {
                socket.receive(packet);
                
                ByteArrayInputStream baos = new ByteArrayInputStream(packet.getData());
                ObjectInputStream oos = new ObjectInputStream(baos);
                StatusMessage msg = (StatusMessage)oos.readObject();
                LOGGER.info(String.format("Local(%d@%s) <<<< Remote (%d@%s): %s"
                        , socket.getLocalPort(), socket.getLocalAddress().toString()
                        , packet.getPort(), packet.getAddress().toString()
                        , msg.toString()));
            }
            LOGGER.info("Received 100 messages, and now ends gracefully.");
        } catch (SocketException e) {
            LOGGER.error(String.format("Failed to create a DatagramSocket bound to host %s at port %d."
                    , ADDRESS, PORT), e);
        } catch (IOException e) {
            LOGGER.error("Failed to read from the DatagramSocket", e);
        } 
    }
    
}

