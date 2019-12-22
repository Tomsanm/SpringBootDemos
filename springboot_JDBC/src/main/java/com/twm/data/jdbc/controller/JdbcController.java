package com.twm.data.jdbc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class JdbcController {
    // 使用 JdbcTemplate 访问数据库
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/hello")
    @ResponseBody
    public Map<String, Object> mmp() {
        List<Map<String, Object>> list =
                jdbcTemplate.queryForList("select * from t_user");
        // 我们只要第一条数据
        return list.get(0);
    }

}
