package com.lxg.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author: tonghp
 * @create: 2021/07/26 16:22
 */
@Configuration
public class DruidConfig {

    @Resource
    private JdbcParamConfig jdbcParamConfig ;

    @Bean
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(jdbcParamConfig.getUrl());
        datasource.setDriverClassName(jdbcParamConfig.getDriverClassName());
        datasource.setInitialSize(jdbcParamConfig.getInitialSize());
        datasource.setMinIdle(jdbcParamConfig.getMinIdle());
        datasource.setMaxActive(jdbcParamConfig.getMaxActive());
        datasource.setMaxWait(jdbcParamConfig.getMaxWait());
        datasource.setUsername(jdbcParamConfig.getUsername());
        datasource.setPassword(jdbcParamConfig.getPassword());
        return datasource;
    }
}