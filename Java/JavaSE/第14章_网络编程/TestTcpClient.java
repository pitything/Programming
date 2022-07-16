import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TestTcpClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Socket client = new Socket("127.0.0.1", 9898);
        Scanner scanner = new Scanner(System.in);
        byte[] bytes = new byte[1024];
        StringBuffer sb = new StringBuffer();
        String line;
        while(true) {
            System.out.print("我：");
            OutputStream out = client.getOutputStream();
            out.write(("客户：" + scanner.next()).getBytes(StandardCharsets.UTF_8));

            InputStream in = client.getInputStream();
            int len = in.read(bytes);
            System.out.println(new String(bytes, 0, len));
        }
    }
}
