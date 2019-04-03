package com.wanghuan.controller;

import com.wanghuan.task.BaseJob;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/job")
public class JobController {


    //加入Qulifier注解，通过名称注入bean
    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    @PostMapping(value = "/addjob")
    public void addjob(@RequestBody Map<String, String> paraMap) throws Exception {
        addJob(paraMap.get("jobClassName"), paraMap.get("jobGroupName"), paraMap.get("cronExpression"));
    }

    public void addJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception {

        // 启动调度器
        scheduler.start();

        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).build();

        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
                .withSchedule(scheduleBuilder).build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            System.out.println("创建定时任务失败" + e);
            throw new Exception("创建定时任务失败");
        }
    }


    @PostMapping(value = "/pausejob")
    public void pausejob(@RequestBody Map<String, String> paraMap) throws Exception {
        jobPause(paraMap.get("jobClassName"), paraMap.get("jobGroupName"));
    }

    public void jobPause(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    @PostMapping(value = "/resumejob")
    public void resumejob(@RequestBody Map<String, String> paraMap) throws Exception {
        jobresume(paraMap.get("jobClassName"), paraMap.get("jobGroupName"));
    }

    public void jobresume(String jobClassName, String jobGroupName) throws Exception {
        scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
    }


    @PostMapping(value = "/reschedulejob")
    public void rescheduleJob(@RequestBody Map<String, String> paraMap) throws Exception {
        jobreschedule(paraMap.get("jobClassName"), paraMap.get("jobGroupName"), paraMap.get("cronExpression"));
    }

    public void jobreschedule(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            System.out.println("更新定时任务失败" + e);
            throw new Exception("更新定时任务失败");
        }
    }


    @PostMapping(value = "/deletejob")
    public void deletejob(@RequestBody Map<String, String> paraMap) throws Exception {
        jobdelete(paraMap.get("jobClassName"), paraMap.get("jobGroupName"));
    }

    public void jobdelete(String jobClassName, String jobGroupName) throws Exception {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
    }

    @PostMapping(value = "/getalljob")
    public List<String> getalljob() throws Exception {
        return getAllJobs();
    }

    public List<String> getAllJobs() {
        try {
            List<String> list = new ArrayList<String>();
            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    String jobName = jobKey.getName();
                    String jobGroup = jobKey.getGroup();
                    //get job's trigger
                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    Date nextFireTime = triggers.get(0).getNextFireTime();
                    list.add(("[jobName] : " + jobName + " [groupName] : "
                            + jobGroup + " - " + nextFireTime));

                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static BaseJob getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (BaseJob) class1.newInstance();
    }


}
