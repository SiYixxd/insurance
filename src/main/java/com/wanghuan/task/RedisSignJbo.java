package com.wanghuan.task;

import com.wanghuan.common.Constants;
import com.wanghuan.model.sys.UserInfo;
import com.wanghuan.utils.RedisUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisSignJbo implements BaseJob {

    private RedisUtil redisUtil;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取到签到的缓存，返回了了用户id和用户签到状态的mp
        //current day  中有记录的用户
        Map signMap = redisUtil.hgetAll(Constants.CACHE_SIGN_DAY);
        //签到状态缓存中的用户map
        Map<String, String> trueMap = redisUtil.hgetAll(Constants.CACHE_SIGN_ACT_TRUE);
        //定义一个list，存放没签到的用户的id
        List<String> noSignUsers = new ArrayList<>();
        //遍历trueMap，查看里面的用户状态为0的，就是没有签到的。遍历的时候，把这些用户的id存入list当中
        for (Map.Entry<String, String> entry : trueMap.entrySet()) {
            if ("0".equals(entry.getValue())) {
                noSignUsers.add(entry.getKey());
            }
        }
        Set<String> userIds = signMap.entrySet();
        userIds.forEach(userId -> {
            //缓存中有记录，而该用户的id在list当中，说明该用户没有签到，就把他的缓存删除，
            if (noSignUsers.contains(userId)) {
                redisUtil.hdel(Constants.CACHE_SIGN_DAY, userId);
            } else {
                //缓存中有记录，而且该用户也已经签过到了，就把currentday+1
                redisUtil.hincry(Constants.CACHE_SIGN_DAY, userId, 1L);
                //并且把他的签到记录改为0，因为是每天零点执行。需要用户再明天看到自己还没有签到
                redisUtil.hset(Constants.CACHE_SIGN_ACT_TRUE, userId, "0");
            }
        });
    }
}