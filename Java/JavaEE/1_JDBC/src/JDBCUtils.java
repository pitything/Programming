import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCUtils {

    public static Connection getConn(){
        InputStream is = null;
        Connection connection = null;
        try {
            is = JDBCUtils.class.getClassLoader().getResourceAsStream("resource/jdbc.properties");
            Properties properties = new Properties();
            properties.load(is);

            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String driverClass = properties.getProperty("driverClass");

            Class.forName(driverClass);
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
