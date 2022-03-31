package com.assignment.librarymanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class LibraryManagementSystemApplication {
	
	@Bean(name="processExecutor")
    public TaskExecutor workExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix("AsyncThread - ");
        threadPoolTaskExecutor.setCorePoolSize(100);
        threadPoolTaskExecutor.setMaxPoolSize(100);
        threadPoolTaskExecutor.setQueueCapacity(100);
        threadPoolTaskExecutor.afterPropertiesSet();
        return threadPoolTaskExecutor;
    }

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}

}
