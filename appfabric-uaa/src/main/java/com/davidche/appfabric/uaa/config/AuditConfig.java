package com.davidche.appfabric.uaa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * JPA 设置
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.davidche.appfabric.uaa.repository")
@EnableJpaAuditing
public class AuditConfig {


}
