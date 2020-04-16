package com.tp.testProject.config;

import java.beans.PropertyVetoException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class ProjectConfiguration {
	@Bean
	public ComboPooledDataSource dateSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/spring_test?characterEncoding=utf8&serverTimezone=UTC");
		dataSource.setUser("root");
		dataSource.setPassword("dogbook7!");
		return dataSource;
	}
}
