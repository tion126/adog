package vip.adog.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by jekay on 17-11-26.
 */
@Repository
public class PhotoDao {
    @Resource
    private JdbcTemplate jdbcTemplate;
}
