package com.wanghuan.task;

import com.wanghuan.common.Constants;
import com.wanghuan.service.sys.UserSignService;
import com.wanghuan.utils.RedisUtil;
import com.wanghuan.utils.SpringUtil2;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户签到扫描归零
 * 每天凌晨 0 点 触发
 */

public class SignJob implements BaseJob {

    UserSignService userSignService = SpringUtil2.getApplicationContext().getBean(UserSignService.class);
    RedisUtil redisUtil = SpringUtil2.getApplicationContext().getBean(RedisUtil.class);


    /**
     * 执行任务，每天零点将签到表格更新。
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        userSignService.updateDailySign();

      /*   //每天0点将断签的用户更新
        String cronExpression = "0 0 0 * * ?";//每天的0点0分0秒执行


       //每天0点将续签的用户更新
        String cronExpressionContinuous = "0 0 0 * * ?";

        //每周第一天将所有用户信息更新
        String cronExpressionWeek = "0 0 0 ? * MON";//每周一的0点0分0秒执行*/
    }



    }
