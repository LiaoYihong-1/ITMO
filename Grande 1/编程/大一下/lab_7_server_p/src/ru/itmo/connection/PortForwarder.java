package ru.itmo.connection;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class PortForwarder {

    public static String forwardPort() {
        String host = "se.ifmo.ru";
        int port = 2222;

        String user = "s284704";
        String pass = "hxy284";

        String listenerHost = "localhost";
        int listenerPort = 9100;

        String listeningHost = "pg";
        int listeningPort = 5432;

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(pass);
            session.setConfig("StrictHostKeyChecking", "no");

//			System.out.println("Establishing Connection...");
            session.connect();

            session.setPortForwardingL(listenerPort, listeningHost, listeningPort);
//			System.out.println(listenerHost + ":" + listenerPort + " -> " + listeningHost + ":" + listeningPort);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "jdbc:postgresql://" + listenerHost + ":" + listenerPort + "/studs";
    }
}