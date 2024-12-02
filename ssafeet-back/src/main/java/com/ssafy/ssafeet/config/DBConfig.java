package com.ssafy.ssafeet.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ssafy.ssafeet.model.dao")
public class DBConfig {

}
