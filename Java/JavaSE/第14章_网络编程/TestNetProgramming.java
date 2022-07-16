import org.junit.Test;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestNetProgramming {
    @Test
    public void testInetAddress() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        System.out.println(address.getHostName());
        System.out.println(address.getHostAddress());

        InetAddress inetAddress1 = InetAddress.getByName("www.baidu.com");
        System.out.println(Arrays.toString(inetAddress1.getAddress()));
        System.out.println(inetAddress1.getHostAddress());
    }

    @Test
    public void testTCPClient() throws IOException, InterruptedException {
        while(true) {
            // 1.创建 Socket：根据指定服务端的 IP 地址或端口号构造 Socket 类对象。若服务器端响应，则建立客户端到服务器的通信线路。若连接失败，会出现异常
            Socket socket = new Socket("127.0.0.1", 9898);
            // 2.打开连接到 Socket 的输入/出流： 使用 getInputStream()方法获得输入流，使用getOutputStream()方法获得输出流，进行数据传输
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            // 3.按照一定的协议对 Socket 进行读/写操作：通过输入流读取服务器放入线路的信息（但不能读取自己放入线路的信息），通过输出流将信息写入线程。
            outputStream.write("hello, i am client".getBytes(StandardCharsets.UTF_8));
            // 4.关闭 Socket：断开客户端到服务器的连接，释放线路
            socket.close();
            Thread.sleep(1);
        }
    }

    @Test
    public void testTCPServer() throws IOException {
        while (true) {
            // 1.调用 ServerSocket(int port) ：创建一个服务器端套接字，并绑定到指定端口上。用于监听客户端的请求
            ServerSocket serverSocket = new ServerSocket(9898);
            // 2.调用 accept()：监听连接请求，如果客户端请求连接，则接受连接，返回通信套接字对象
            Socket accept = serverSocket.accept();
            // 3.调用 该Socket类对象的 getOutputStream() 和 getInputStream ()：获取输出流和输入流，开始网络数据的发送和接收
            InputStream inputStream = accept.getInputStream();
            byte[] bytes = new byte[1024];
            inputStream.read(bytes);
            System.out.println(new String(bytes));
            // 4.关闭ServerSocket和Socket对象：客户端访问结束，关闭通信套接字
            accept.close();
            serverSocket.close();
        }
    }

    @Test
    public void testUDPClient(){
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket();
            byte[] by = "hello,atguigu.com".getBytes();
            DatagramPacket dp = new DatagramPacket(by, 0, by.length,
                    InetAddress.getByName("127.0.0.1"), 8989);
            ds.send(dp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ds != null)
                ds.close();
        }
    }

    @Test
    public void testUDPServer(){
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(8989);
            byte[] by = new byte[1024];
            DatagramPacket dp = new DatagramPacket(by, by.length);
            ds.receive(dp);
            String str = new String(dp.getData(), 0, dp.getLength());
            System.out.println(str + "--" + dp.getAddress());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ds != null)
                ds.close();
        }
    }

    @Test
    public void testURL() throws IOException {
        URL url = new URL("https://www.youpin898.com/market/csgo?gameId=730&weapon=weapon_knife_m9_bayonet&quality=unusual&exterior=WearCategory0");
//        URL url = new URL("https://www.youpin898.com/market/csgo?gameId=730");
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        byte[] bytes = new byte[1024];
        while(inputStream.read(bytes) != -1){
            System.out.println(new String(bytes));
        }
    }
}

