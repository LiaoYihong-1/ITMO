package ru.itmo.connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

// Server
public class ConnectionManager {

    private final DatagramSocket serverSocket;
    private DatagramPacket packet;

    private InetAddress clientInetAddress;
    private int clientPort;

    private int bufferSize;


    public ConnectionManager(int serverPort) {
        bufferSize = 65536;
        try {
            serverSocket = new DatagramSocket(serverPort);

        } catch (SocketException e) {
            throw new IllegalArgumentException("Error: Can't connect to specified port or get LocalHost address.");
        }

        System.out.println("Server is running...");
    }


    public void send(byte[] bytes) throws IOException {
        packet = new DatagramPacket(bytes, bytes.length, clientInetAddress, clientPort);
        serverSocket.send(packet);
    }


    public byte[] receive() throws IOException {
        byte[] buffer = new byte[bufferSize];

        packet = new DatagramPacket(buffer, buffer.length);
        serverSocket.receive(packet);

        clientInetAddress = packet.getAddress();
        clientPort = packet.getPort();

        return buffer;
    }


    public int getBufferSize() {
        return bufferSize;
    }


    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }
}
