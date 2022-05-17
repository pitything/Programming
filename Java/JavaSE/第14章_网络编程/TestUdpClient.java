import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class TestUdpClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();
        byte[] bytes = "hello".getBytes(StandardCharsets.UTF_8);
        DatagramPacket datagramPacket = new DatagramPacket
                (bytes, 0, bytes.length, InetAddress.getLoopbackAddress(), 8081);
        datagramSocket.send(datagramPacket);
    }
}
