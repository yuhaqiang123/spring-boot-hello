package com.muppet.auth.dao;

import com.muppet.auth.jdbc.JdbcSourceScan;
import com.muppet.auth.model.User;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class JdbcTemplateDao {

    @org.springframework.beans.factory.annotation.Autowired
    private JdbcTemplate jdbcTemplate;

    @org.springframework.beans.factory.annotation.Autowired
    private JdbcSourceScan jdbcSourceScan;

    private Logger logger = Logger.getLogger(this.getClass());

    public void rowCallbackHandler() {
        String sql = "select * from tb_user";
        List<User> users = jdbcTemplate.query(sql, new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getString("user_id"));

                return user;
            }
        });
        logger.debug(logger.isDebugEnabled());
        logger.debug(jdbcSourceScan.toString());
        logger.debug(users);
    }

}
