package io.github.pleuvoir.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import io.github.pleuvoir.kit.QuartzConst;
import io.github.pleuvoir.model.Message;

@Component
public class DynamicCreateJob{
	
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	private static Logger logger = LoggerFactory.getLogger(DynamicCreateJob.class);
	
	
	public void handler(Message msg) {
		createJob(msg);
	}
	
	
	private void createJob(Message msg){
		
		logger.info("准备创建动态定时任务，msg：{}", msg.toJSON());
		
		Scheduler scheduler = schedulerFactoryBean.getScheduler();

		JobKey jobKey = new JobKey(msg.getId(), QuartzConst.JobKeyGroup.NORMAL_JOB);
		TriggerKey triggerKey = new TriggerKey(msg.getId(), QuartzConst.TriggerKeyGroup.NORMAL_JOB);

		JobDetail jobDetail = JobBuilder.newJob(ActualExcuteJob.class)
				.withIdentity(jobKey)
				.usingJobData("id", msg.getId())
				.build();

		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity(triggerKey)
				.startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(1)
						.repeatForever())
				.build();

		try {
			scheduler.scheduleJob(jobDetail, trigger);
			logger.info("创建动态定时任务，id：{}", msg.getId());
		} catch (SchedulerException e) {
			logger.error("创建动态定时任务失败，消息内容：{}", msg.toJSON(), e);
		}
	}
}
