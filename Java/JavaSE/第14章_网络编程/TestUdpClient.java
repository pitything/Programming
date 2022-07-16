import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TestUdpClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket client = new DatagramSocket();
        Scanner scanner = new Scanner(System.in);
        String str;
        byte[] bytes;
        while(true){
            str = scanner.next();
            bytes = str.getBytes(StandardCharsets.UTF_8);
            DatagramPacket data = new DatagramPacket(bytes, 0, bytes.length,
                    InetAddress.getByName("127.0.0.1"), 8989);
            client.send(data);
        }
    }
}
