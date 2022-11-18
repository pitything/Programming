import jdk.nashorn.internal.scripts.JD;
import org.junit.jupiter.api.Test;

import java.awt.print.PrinterAbortException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.Scanner;

public class PreparedStatementTest {

    @Test
    public void testPreparedStatement() {
        String sql1 = "update user set user = ? where id = ?";
        update(sql1, "张三1", 1);

        String sql2 = "select user, password from user where id = ?";
        System.out.println(getInstance(User.class, sql2, 1));
    }

    //通用的增、删、改操作（体现一：增、删、改 ； 体现二：针对于不同的表）
    public void update(String sql,Object ... args){
        try(Connection conn = JDBCUtils.getConn();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            //3.填充占位符
            for(int i = 0;i < args.length;i++){
                ps.setObject(i + 1, args[i]);
            }

            //4.执行sql语句
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 通用的针对于不同表的查询:返回一个对象 (version 1.0)
    public <T> T getInstance(Class<T> clazz, String sql, Object... args) {
        ResultSet rs = null;
        try (Connection conn = JDBCUtils.getConn();
             PreparedStatement ps = conn.prepareStatement(sql)){

            // 3.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            // 4.执行executeQuery(),得到结果集：ResultSet
            rs = ps.executeQuery();

            // 5.得到结果集的元数据：ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();

            // 6.1通过ResultSetMetaData得到columnCount,columnLabel；通过ResultSet得到列值
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {// 遍历每一个列

                    // 获取列值
                    Object columnVal = rs.getObject(i + 1);
                    // 获取列的别名:列的别名，使用类的属性名充当
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    // 6.2使用反射，给对象的相应属性赋值
                    Field field = clazz.getDeclaredField(columnLabel);
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
