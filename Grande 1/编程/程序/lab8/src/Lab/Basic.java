package Lab;

import Collection.CollectionsofPerson;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Selector;

public class Basic {
    private Selector selector;
    private CollectionsofPerson collection;
    private DatagramChannel channel;
    private InetSocketAddress address;
    private String username = null;
    private ClientInformation client;

    Basic(Selector selector,CollectionsofPerson collection,DatagramChannel channel,InetSocketAddress address,ClientInformation client){
        this.collection = collection;
        this.selector = selector;
        this.channel = channel;
        this.address = address;
        this.client = client;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public CollectionsofPerson getCollection() {
        return collection;
    }

    public DatagramChannel getChannel() {
        return channel;
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public Selector getSelector() {
        return selector;
    }

    public void close() throws IOException {
        this.channel.close();
    }

    public ClientInformation getClient() {
        return client;
    }

    public void setChannel(DatagramChannel channel) {
        this.channel = channel;
    }

    public void setClient(ClientInformation client) {
        this.client = client;
    }
}
