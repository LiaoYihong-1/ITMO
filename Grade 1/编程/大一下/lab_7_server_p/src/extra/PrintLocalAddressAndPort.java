package extra;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class PrintLocalAddressAndPort {

    public static void main(String[] args) {



        InetSocketAddress randomSocketAddress = new InetSocketAddress(0);

        try (ServerSocket ssOne = new ServerSocket()) {
            InetAddress inetAddress = InetAddress.getLocalHost();
            System.out.format("\nIP Address : %s" , inetAddress.getHostAddress());
            ssOne.bind(randomSocketAddress);
            System.out.format("\nFree PORT  : %s \n\n" , ssOne.getLocalPort());
        } catch (IOException e) {
            System.out.println("Error: Can't get local address.");
        }

    }
}
