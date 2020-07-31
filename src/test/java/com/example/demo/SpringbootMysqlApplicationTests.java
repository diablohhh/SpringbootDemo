package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author ZhangQiangLong
 * @Date 2020/7/2 9:29
 * @Text:
 **/
@SpringBootTest
public class SpringbootMysqlApplicationTests {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Test
    public void testFunction(){
        String sql = "select * from s_user";
        List<S_USER> list = jdbcTemplate.query(sql, new RowMapper<S_USER>() {
            @Override
            public S_USER mapRow(ResultSet resultSet, int i) throws SQLException {
                S_USER s_user = new S_USER();
                s_user.setUserid(resultSet.getString("userid"));
                s_user.setUsername(resultSet.getString("username"));
                s_user.setPassword(resultSet.getString("password"));
                System.out.println(s_user.toString());
                return s_user;
            }
        });
    }
}
