package com.example.SpringbootApi.config;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.SpringbootApi.quartz.QuartzJob;

@Configuration
public class QuartzConfig {
	
	private static final String JOB_ID = "quartzJob";
	private static final String TRIGGER_ID = "trigger";
	
	@Bean
	public JobDetail quartzJobDetail() {
		return JobBuilder.newJob(QuartzJob.class)
				.withIdentity(JOB_ID)
				.storeDurably()
				.build();
	}
	
	@Bean
	public Trigger testQuartzJobTrigger(JobDetail quartzJobDetail) {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
				.simpleSchedule()
				.withIntervalInMinutes(1)
				.repeatForever();

		return TriggerBuilder.newTrigger()
				.forJob(quartzJobDetail)
				.withIdentity(TRIGGER_ID)
				.withSchedule(scheduleBuilder)
				.build();
	}

}
