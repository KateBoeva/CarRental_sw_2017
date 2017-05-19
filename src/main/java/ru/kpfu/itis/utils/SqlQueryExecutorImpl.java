package ru.kpfu.itis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by katemrrr on 17.05.17.
 */

@Component
public class SqlQueryExecutorImpl implements SqlQueryExecutor {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//    @Autowired
    public SqlQueryExecutorImpl(DataSource dataSource) {
        NamedParameterJdbcDaoSupport jdbcDaoSupport = new NamedParameterJdbcDaoSupport();
        jdbcDaoSupport.setDataSource(dataSource);
        this.jdbcTemplate = jdbcDaoSupport.getJdbcTemplate();
        this.namedParameterJdbcTemplate = jdbcDaoSupport.getNamedParameterJdbcTemplate();
    }

    public  <T> List<T> queryForObjects(String sql, RowMapper<T> rowMapper) {
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public <T> List<T> queryForObjects(String sql, Map<String, Object> paramMap, RowMapper<T> rowMapper) {
        return namedParameterJdbcTemplate.query(sql, paramMap, rowMapper);
    }

    public <T> T queryForObject(String sql, Map<String, Object> paramMap, RowMapper<T> rowMapper) {
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, rowMapper);
    }

    public void updateQuery(String sql, Map<String, Object> paramMap) {
        namedParameterJdbcTemplate.update(sql, paramMap);
    }

//    @Override
//    public int queryForInt(String sql, Map<String, Object> paramMap) {
//        try {
//            return namedParameterJdbcTemplate.queryForInt(sql, paramMap);
//        } catch(EmptyResultDataAccessException e){
//            return 0;
//        }
//    }
//
//    @Override
//    public int queryForInt(String sql) {
//        return jdbcTemplate.queryForInt(sql);
//    }
}