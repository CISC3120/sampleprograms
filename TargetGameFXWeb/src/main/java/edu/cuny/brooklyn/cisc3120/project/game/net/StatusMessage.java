package edu.cuny.brooklyn.cisc3120.project.game.net;

import java.io.Serializable;
import java.net.InetAddress;

public class StatusMessage implements Serializable {
    private static final long serialVersionUID = -666227046530917575L;

    private InetAddress address;
    private int tcpServicePort;
    
    
    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public int getTcpServicePort() {
        return tcpServicePort;
    }

    public void setTcpServicePort(int tcpServicePort) {
        this.tcpServicePort = tcpServicePort;
    }

    public StatusMessage(InetAddress address, int tcpServicePort) {
        this.address = address;
        this.tcpServicePort = tcpServicePort;
    }
    
    public String toString() {
        return String.format("{ InetAddress= %s, Port = %d }", address.toString(), tcpServicePort);
    }
}
