package com.quintifi.schedulerBot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/*
 * By default, all the @Scheduled tasks are executed in a default thread pool of size one created by Spring
 * You can create your own thread pool and configure Spring to use that thread pool for executing all the scheduled tasks.
 * SchedulingConfigurer - @FunctionalInterface
 */
@Configuration
public class SchedulerConfig implements SchedulingConfigurer {

	private final int POOL_SIZE = 10;

	@Override
	public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();

		threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
		threadPoolTaskScheduler.setThreadNamePrefix("schedulerBot-pool-");
		threadPoolTaskScheduler.initialize();

		scheduledTaskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
	}
}
