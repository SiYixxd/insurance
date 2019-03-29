package com.wanghuan.service.impl.sys;

import com.wanghuan.common.Constants;
import com.wanghuan.common.InsuranceException;
import com.wanghuan.dao.UserSignDao;
import com.wanghuan.model.sys.UserSign;
import com.wanghuan.service.sys.UserSignService;
import com.wanghuan.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "userSignServiceImpl")
public class UserSignServiceImpl implements UserSignService {
    @Autowired
    private UserSignDao userSignDao;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void insertSign(UserSign userSign) {
        userSignDao.insertSign(userSign);
    }

    @Override
    public void updateSign(UserSign userSign) {
        userSignDao.updateSign(userSign);
    }

    @Override
    public UserSign findSignStatus(String userId) {
        return userSignDao.findSignStatus(userId);
    }


    public void saveSign2(String userId) {
        //判断请求签到的用户是否有记录
        UserSign userSign = userSignDao.findSignStatus(userId);
        if (userSign == null) {
            //如果没有记录则直接进行保存操作
            userSign.setSignStatus(1);
            userSign.setCurrentDay(1);
            userSign.setTotalDay(1);
            userSignDao.insertSign(userSign);
        } else {
            //如果有记录则进一步判断今天用户是否已经签到了
            if (userSign.getSignStatus() == 1) {
                //说明用户已经签过到了
                //直接返回！
            } else {
                //用户今天没签到
                userSign.setTotalDay(userSign.getTotalDay() + 1);
                userSign.setSignStatus(1);
                userSignDao.updateSign(userSign);
            }
        }
    }

    @Override
    public void saveSign(String userId) throws Exception {
        //1 查出这个用户签到记录
        UserSign userSign = userSignDao.findSignStatus(userId);
        //2 判断是否是为空  如果是空 就是第一次签到
        if (userSign == null) {
            //2.1 封装userSign
            UserSign newSign = new UserSign();
            newSign.setUserId(userId);
            newSign.setTotalDay(1);
            newSign.setCurrentDay(1);
            newSign.setSignStatus(1);
            //2.2 保存
            userSignDao.insertSign(newSign);
            //2.3 todo 给用户发平台金币 或者是平台积分
        } else {
            //否则n次签到 进行更新
            //3.1 判断用户今天是否已经签到了
            //已经签过到了：
            if (userSign.getSignStatus() == 1) {
                //3.2 直接告诉用户已经签过到了
                throw new InsuranceException(10005, "您已经签过到了!");
            } else {
                //今天还没有签到
                //3.3 更新数据
                userSign.setTotalDay(userSign.getTotalDay() + 1);
                userSign.setSignStatus(1);
                userSignDao.updateSign(userSign);
                //todo 给用户发平台金币 或者是平台积分
            }
        }
    }

    /**
     * quartz 自动完成更新用户签到表格信息
     */
    @Override
    public void updateDailySign() {
        //1  把没有续签的用户归零 ->  查询出所有currentDay > 1 并且 signStatus 是 0 的记录 ：
        // 应该签的第5天，但是没有签，第六天的凌晨0点要更新
        //查到的这些用户批量更新一下， //1.1  把他们的currentDay改成１　表示他们明天开始签到记录归零，从第一天开始
        userSignDao.updateIrregularSign();
        //2 把续签的用户的应该签到天数变成今天，也就是 + 1 -> 查询出 currentDay >= 1 并且 signStatus = 1
        userSignDao.updateContinousSign();


    }

    public void updateWeekSign() {
        //todo
        // 2.2 currentDay == 7  -> currentDay = 1
        userSignDao.uopdateAll();
    }


    @Override
    public void saveSignByRedis(String userId) throws Exception {
        //1 查看这个人签到记录,先获取到签到缓存，
        String currentDayCache = redisUtil.hget(Constants.CACHE_SIGN_DAY, userId);
        //2 拿到记录之后判断是否签到完成
        if (StringUtils.isBlank(currentDayCache)) {
            //3 没有签到  进行签到
            //如果用户的签到缓存是空，就说明用户还没有签到，开始保存
            redisUtil.hset(Constants.CACHE_SIGN_ACT_TRUE, userId, "1");
            redisUtil.hset(Constants.CACHE_SIGN_DAY, userId, "1");
        } else {//有缓存记录的，
            //判断用户是否签过到了
            String signStatusCache = redisUtil.hget(Constants.CACHE_SIGN_ACT_TRUE, userId);
            if (StringUtils.isNotBlank(signStatusCache) && signStatusCache == "") {
                //说明用户已经签过到了 抛一个异常
                throw new InsuranceException(10005, "您已经签过到了!");
            } else {
                //用户还没有签到，完成签到，开始保存
                redisUtil.hset(Constants.CACHE_SIGN_ACT_TRUE, userId, "1");
                //并且将用户的currentDay+1 todo
                //在job中完成了，有缺点， 不能立即显示当前已经签了多少天
                //  redisUtil.hincry(Constants.CACHE_SIGN_DAY,userId,1L);
            }
        }


    }

}
