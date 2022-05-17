import org.ietf.jgss.Oid;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TestTcpServer {
    public static void main(String[] args) throws IOException {
        while(true)
        server();
    }

    /** 服务端 */
    public static void server() throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(8080);
            ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            Socket accept = serverSocket.accept();
            String hostAddress = accept.getInetAddress().getHostAddress();
            InputStream inputStream = accept.getInputStream();

            // 会将中文转换的字节数组截断，导致出现乱码，不推荐
//            byte[] bytes = new byte[20];
//            StringBuffer stringBuffer = new StringBuffer();
//            while((inputStream.read(bytes) != -1)){
//                stringBuffer.append(new String(bytes));
//            }

            byte [] bytes = new byte[20];
            int len;
            while((len = inputStream.read(bytes)) != -1){
                baos.write(bytes, 0, len);
            }
            System.out.println("接收到来自" + hostAddress + "数据：" + baos);

            baos.close();
            inputStream.close();
            accept.close();
            serverSocket.close();
        }
    }
}
