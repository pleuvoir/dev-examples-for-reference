package io.github.pleuvoir.quartz;

import java.util.Random;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.pleuvoir.kit.QuartzConst;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ActualExcuteJob implements Job {

	private static Logger logger = LoggerFactory.getLogger(ActualExcuteJob.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		String id = context.getJobDetail().getJobDataMap().getString("id");
		
		
		while (new Random().nextBoolean()) {
			logger.info("开始执行定时任务，id：{}", id);
		}

		try {
			logger.info("开始移除定时任务，id：{}", id);
			removeTrigger(context, id);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	
	private void removeTrigger(JobExecutionContext context, String id) throws SchedulerException {
		TriggerKey triggerKey = new TriggerKey(id, QuartzConst.TriggerKeyGroup.NORMAL_JOB);
		try {
			context.getScheduler().unscheduleJob(triggerKey);
		} catch (SchedulerException e) {
			logger.error("移除当前定时任务失败，TriggerKey: {}", triggerKey.toString(), e);
			throw e;
		}
	}

}
