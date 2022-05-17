import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TestUdpServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(8081);
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        datagramSocket.receive(datagramPacket);
        System.out.println(new String(datagramPacket.getData()));
    }
}
