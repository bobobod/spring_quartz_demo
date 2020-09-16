package com.cczu.spring_quartz_demo.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author jianzhen.yin
 * @date 2020/9/16
 */
public class DemoTask2 extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("demoTask2 init");
    }
}
