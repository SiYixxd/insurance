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
 * 每周用户签到扫描归零
 *
 */
public class WeekSignJob implements BaseJob {

    UserSignService userSignService = SpringUtil2.getApplicationContext().getBean(UserSignService.class);


    /**
     * 执行任务，每天零点将签到表格更新。
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        userSignService.updateWeekSign();

        //每周第一天将所有用户信息更新
        // String cronExpressionWeek = "0 0 0 ? * MON";//每周一的0点0分0秒执行*/
    }



}
