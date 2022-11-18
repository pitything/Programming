import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.Test;

import java.sql.Connection;


public class TestDBUtils {
    // 测试添加
    @Test
    public void testInsert() throws Exception {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConn();
        String sql = "insert into customers(name,email,birth)values(?,?,?)";
        int count = runner.update(conn, sql, "何成飞", "he@qq.com", "1992-09-08");
        System.out.println("添加了" + count + "条记录");
    }

    @Test
    public void testQuery(){
        QueryRunner queryRunner = new QueryRunner();

    }
}
