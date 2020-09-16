package com.cczu.spring_quartz_demo.config;

import com.cczu.spring_quartz_demo.task.DemoTask;
import com.cczu.spring_quartz_demo.task.DemoTask2;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;

/**
 * @author jianzhen.yin
 * @date 2020/9/16
 */
@Configuration
public class quartzConfig {
    //    @Bean("demoTask")
//    public JobDetailFactoryBean demoTask(){
//        return getJobDetail(DemoTask.class);
//    }


    @Bean("demoTask")
    public JobDetailFactoryBean demoTask() {
        return getJobDetail(DemoTask.class);
    }
    @Bean("demoTask2")
    public JobDetailFactoryBean demoTask2() {
        return getJobDetail(DemoTask2.class);
    }

    @Bean("demoTaskTrigger")
    public CronTriggerFactoryBean demoTaskTrigger(JobDetail demoTask) {
        return getCronTrigger(demoTask, "3/1 * * * * ? ");
    }
    @Bean("demoTaskTrigger2")
    public SimpleTriggerFactoryBean demoTaskTrigger2(JobDetail demoTask2){
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(demoTask2);
        factoryBean.setRepeatInterval(1000L);
        return factoryBean;
    }
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(Trigger demoTaskTrigger,Trigger demoTaskTrigger2) {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setTriggers(demoTaskTrigger,demoTaskTrigger2);
        return factoryBean;

    }


    public JobDetailFactoryBean getJobDetail(Class<? extends Job> clazz) {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setJobClass(clazz);
        jobDetail.setBeanName(clazz.getName());
        jobDetail.setDurability(true);
        return jobDetail;
    }

    public CronTriggerFactoryBean getCronTrigger(JobDetail jobDetail, String cronTriggerExpression) {
        CronTriggerFactoryBean cronTrigger = new CronTriggerFactoryBean();
        cronTrigger.setJobDetail(jobDetail);
        cronTrigger.setCronExpression(cronTriggerExpression);
        return cronTrigger;
    }
}
