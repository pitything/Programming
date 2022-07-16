import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class TestUdpServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket server = new DatagramSocket(8989, InetAddress.getByName("127.0.0.1"));
        byte[] bytes;
        while(true){
            bytes = new byte[1024];
            DatagramPacket data = new DatagramPacket(bytes, 0, bytes.length);
            server.receive(data);
            System.out.println(new String(data.getData()));
        }
    }
}
