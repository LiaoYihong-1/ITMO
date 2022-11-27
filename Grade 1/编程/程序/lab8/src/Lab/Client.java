package Lab;
import Collection.*;
import Frames.Login;
import java.io.*;
import java.net.*;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

public class Client {
    private static String username;
    public static void main(String[] args) throws IOException,ClassNotFoundException{
        Selector selector = Selector.open();
        CollectionsofPerson collection = new CollectionsofPerson();
        collection.doInitialization();
        DatagramChannel channel = DatagramChannel.open();
        channel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost",5555);
        channel.register(selector,SelectionKey.OP_READ|SelectionKey.OP_WRITE);
        //InetSocketAddress inetSocketAddress = new InetSocketAddress(args[0],Integer.parseInt(args[1]));

        //login
        new Login(new Basic(selector,collection,channel,inetSocketAddress,null));
    }

}