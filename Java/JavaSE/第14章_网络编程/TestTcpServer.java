import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TestTcpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(9898);
        System.out.println("等待客户连接...");
        Socket server = socket.accept();
        System.out.println("客户已经连接...");
        Scanner scanner = new Scanner(System.in);
        byte[] bytes = new byte[1024];
        while(true){
            InputStream in = server.getInputStream();
            int leng = in.read(bytes);
            System.out.println(new String(bytes, 0, leng));

            System.out.print("我：");
            OutputStream out = server.getOutputStream();
            String temp = scanner.nextLine();
            out.write(("服务器：" + temp).getBytes(StandardCharsets.UTF_8));
        }
    }
}
