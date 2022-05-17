import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TestTcpClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        while (true)
        client();
    }

    /** 客户端 */
    public static void client() throws IOException, ClassNotFoundException {
        try(Socket socket = new Socket("localhost", 8080);
            OutputStream outputStream = socket.getOutputStream();
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("/Users/leon_chiang/Desktop/test.txt")))){
            Man object = (Man) inputStream.readObject();
            outputStream.write(object.toString().getBytes(StandardCharsets.UTF_8));
        }
    }
}
