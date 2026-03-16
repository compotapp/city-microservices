package com.pot.app.mayorofcity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Включает аудит постоянных(persistent) сущностей.
 * Включает @CreatedDate, @LastModifiedDate
 */
@Configuration
@EnableJpaAuditing
public class DataConfig {
}