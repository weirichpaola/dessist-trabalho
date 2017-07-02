package br.pucrs.segmanager.resources;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzListener implements Servlet {
        public static Scheduler scheduler = null;

        public void contextInitialized() {
                System.out.println("Context Initialized");
                
                try {
                        // Setup the Job class and the Job group
                        JobDetail job = newJob(MailSenderJob.class).withIdentity(
                                        "CronQuartzJob", "Group").build();

                        // Create a Trigger that fires every 5 minutes.
                        Trigger trigger = newTrigger()
                        .withIdentity("TriggerName", "Group")
                        .withSchedule(CronScheduleBuilder.cronSchedule("0/55 * * * * ?"))
                        .build();

                        // Setup the Job and Trigger with Scheduler & schedule jobs
                        scheduler = new StdSchedulerFactory().getScheduler();
                        scheduler.start();
                        scheduler.scheduleJob(job, trigger);
                }
                catch (SchedulerException e) {
                        e.printStackTrace();
                }
        }

		@Override
		public void destroy() {
		}

		@Override
		public ServletConfig getServletConfig() {
			return null;
		}

		@Override
		public String getServletInfo() {
			return null;
		}

		@Override
		public void init(ServletConfig arg0) throws ServletException {
			contextInitialized();			
		}

		@Override
		public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
			
		}
}