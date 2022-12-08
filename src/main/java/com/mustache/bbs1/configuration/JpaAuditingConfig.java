package com.mustache.bbs1.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // Auditing 설정 어노테이션
public class JpaAuditingConfig {
}
