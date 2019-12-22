package com.twm.data.jdbc;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class JdbcApplicationTests {
    @Autowired
    DataSource dataSource;

    @Test
    void testJDBCConnection() throws SQLException {
        System.out.println("目前使用的数据源是"+ dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println("链接是否成功："+connection);
        connection.close(); // 关闭数据库连接
    }

}
