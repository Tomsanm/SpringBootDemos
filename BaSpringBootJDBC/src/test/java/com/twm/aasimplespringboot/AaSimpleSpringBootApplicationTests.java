package com.twm.aasimplespringboot;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
@Slf4j
class AaSimpleSpringBootApplicationTests {
    @Autowired
    private JdbcTemplate template;

    @Test
    void contextLoads() {
    }

    @Test
    void jdbcConnect(){
        Long ans = template.queryForObject("Select count(*) from tb_user", Long.class);
        log.info("答案是： " + String.valueOf(ans));

    }

}
