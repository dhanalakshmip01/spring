package com.spring.springmvcproject.config;

import java.util.concurrent.Executor;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configurable
@EnableAsync
public class AsyncConfig {
	
	@Bean
	public Executor taskExecutor() {
	ThreadPoolTaskExecutor texecutor = new ThreadPoolTaskExecutor();
	texecutor.setCorePoolSize(2);
	texecutor.setMaxPoolSize(2);
	texecutor.setQueueCapacity(10);
	texecutor.setThreadNamePrefix("employeeThread -");
	texecutor.initialize();
		return texecutor;
		
	}

}
