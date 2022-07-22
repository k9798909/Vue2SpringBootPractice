package com.example.SpringbootApi.quartz;

import java.time.LocalDateTime;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.example.SpringbootApi.utility.DateTimeUtility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuartzJob extends QuartzJobBean {
	
	@Value("${server.port}")
	public String test;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		log.info("啟動時間：{}", LocalDateTime.now().format(DateTimeUtility.DATE_TIME_UI));
		log.info(test);
		log.info("結束時間：{}", LocalDateTime.now().format(DateTimeUtility.DATE_TIME_UI));
	}

}
