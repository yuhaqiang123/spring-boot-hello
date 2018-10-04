package com.muppet.auth.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/muppet/hello")
public class HelloController {

    private Logger logger = Logger.getRootLogger();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/index/name/{name}/msg/{msg}", method = RequestMethod.POST)
    public void index(@PathVariable String name
            , @PathVariable String msg
            , @CookieValue(value = "Accept-Encoding") String encoding
            , @RequestBody String body
    ) {
        logger.info(String.format("name:%s, msg:%s, encoding:%s, body:%s ", name, msg, encoding, body));
    }

}
