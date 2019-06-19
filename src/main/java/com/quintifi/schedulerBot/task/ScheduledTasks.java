package com.quintifi.schedulerBot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
 * Before adding tasks, Let’s first create the container for all the scheduled tasks
 */
@Component
public class ScheduledTasks {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	/*
	 * All the scheduled methods should follow the following two criteria -
	 * 
	 * The method should have a void return type. The method should not accept any
	 * arguments.
	 */

	// schedule a method to be executed at a fixed interval by using fixedRate
	// parameter in the @Scheduled annotation. executed every 2 seconds
	@Scheduled(fixedRate = 2000)
	public void scheduleTaskWithFixedRate() {
		logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
	}

	// execute a task with a fixed delay between the completion of the last
	// invocation and the start of the next, using fixedDelay parameter.
	@Scheduled(fixedDelay = 2000)
	public void scheduleTaskWithFixedDelay() {
		logger.info("Fixed Delay Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException ex) {
			logger.error("Ran into an error {}", ex);
			throw new IllegalStateException(ex);
		}
	}

	// initialDelay parameter with fixedRate and fixedDelay to delay the first
	// execution of the task with the specified number of milliseconds.the first
	// execution of the task will be delayed by 5 seconds and then it will be
	// executed normally at a fixed interval of 2 seconds
	@Scheduled(fixedRate = 2000, initialDelay = 5000)
	public void scheduleTaskWithInitialDelay() {
		logger.info("Fixed Rate Task with Initial Delay :: Execution Time - {}",
				dateTimeFormatter.format(LocalDateTime.now()));
	}

	/*
	 * Below is a breakdown of the components that build a cron expression.
	 * 
	 * Seconds can have values 0-59 or the special characters , - * / . Minutes can
	 * have values 0-59 or the special characters , - * / . Hours can have values
	 * 0-59 or the special characters , - * / . Day of month can have values 1-31 or
	 * the special characters , - * ? / L W C . Month can have values 1-12, JAN-DEC
	 * or the special characters , - * / . Day of week can have values 1-7, SUN-SAT
	 * or the special characters , - * ? / L C # . Year can be empty, have values
	 * 1970-2099 or the special characters , - * / .
	 * 
	 * @Scheduled(cron =
	 * "[Seconds] [Minutes] [Hours] [Day of month] [Month] [Day of week] [Year]")
	 * 
	 * 
	 * Before we can move on, we need to go through what the special characters
	 * mean.
	 * 
	 * represents all values. So, if it is used in the second field, it means every
	 * second. If it is used in the day field, it means run every day. ? represents
	 * no specific value and can be used in either the day of month or day of week
	 * field — where using one invalidates the other. If we specify it to trigger on
	 * the 15th day of a month, then a ? will be used in the Day of week field. -
	 * represents an inclusive range of values. For example, 1-3 in the hours field
	 * means the hours 1, 2, and 3. , represents additional values. For example,
	 * putting MON,WED,SUN in the day of week field means on Monday, Wednesday, and
	 * Sunday. / represents increments. For example 0/15 in the seconds field
	 * triggers every 15 seconds starting from 0 (0, 15, 3,0 and 45). L represents
	 * the last day of the week or month. Remember that Saturday is the end of the
	 * week in this context, so using L in the day of week field will trigger on a
	 * Saturday. This can be used in conjunction with a number in the day of month
	 * field, such as 6L to represent the last Friday of the month or an expression
	 * like L-3 denoting the third from the last day of the month. If we specify a
	 * value in the day of week field, we must use ? in the day of month field, and
	 * vice versa. W represents the nearest weekday of the month. For example, 15W
	 * will trigger on the 15th day of the month if it is a weekday. Otherwise, it
	 * will run on the closest weekday. This value cannot be used in a list of day
	 * values. # specifies both the day of the week and the week that the task
	 * should trigger. For example, 5#2 means the second Thursday of the month. If
	 * the day and week you specified overflows into the next month, then it will
	 * not trigger.
	 */
	
	

	@Scheduled(cron = "0 * * * * ?")
	public void scheduleTaskWithCronExpression() {
		logger.info("Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
	}

}