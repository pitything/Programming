import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.Scanner;

public class StatementTest {

    // 使用Statement的弊端：需要拼写sql语句，并且存在SQL注入的问题
    // 注意使用idea，@Test中的Scanner出现一直等待的问题，配置VM参数：-Deditable.java.test.console=true
    @Test
    public void testLogin() {
        Scanner scan = new Scanner(System.in);

        System.out.print("用户名：");
        String userName = scan.nextLine();
        System.out.print("密   码：");
        String password = scan.nextLine();

        // SELECT user,password FROM user_table WHERE USER = '1' or ' AND PASSWORD = '='1' or '1' = '1';
        String sql = "SELECT user,password FROM user WHERE USER = '" + userName + "' AND PASSWORD = '" + password
                + "'";
        User user = get(sql, User.class);
        if (user != null) {
            System.out.println("登陆成功!");
        } else {
            System.out.println("用户名或密码错误！");
        }
    }

    // 使用Statement实现对数据表的查询操作
    public <T> T get(String sql, Class<T> clazz) {
        T t = null;

        try( Connection conn = JDBCUtils.getConn();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            // 获取结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            // 获取结果集的列数
            int columnCount = rsmd.getColumnCount();

            if (rs.next()) {
                t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    // //1. 获取列的名称
                    // String columnName = rsmd.getColumnName(i+1);

                    // 1. 获取列的别名
                    String columnName = rsmd.getColumnLabel(i + 1);

                    // 2. 根据列名获取对应数据表中的数据
                    Object columnVal = rs.getObject(columnName);

                    // 3. 将数据表中得到的数据，封装进对象
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, columnVal);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
