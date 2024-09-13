package com.power.mall.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

/**
 * MyBatis相关配置
 * Created by macro on 2019/4/8.
 */
@Configuration
@MapperScan({"com.power.mall.mapper","com.power.mall.dao"})
public class MyBatisConfig {

}
