import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestNetProgramming {

    public static void main(String[] args) throws UnknownHostException, UnsupportedEncodingException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(inetAddress);
        System.out.println(inetAddress.getHostName());
        System.out.println(Arrays.toString(inetAddress.getAddress()));

        InetAddress inetAddress1 = InetAddress.getByName("www.baidu.com");
        System.out.println(Arrays.toString(inetAddress1.getAddress()));



    }
}
