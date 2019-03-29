package com.wanghuan.service.sys;


import com.wanghuan.model.sys.UserSign;

import java.util.List;

public interface UserSignService {

    void insertSign(UserSign userSign);
    //完成签到功能
    void updateSign(UserSign userSign);
    //查询保险
    UserSign findSignStatus(String userId);

    /**
     * 签到方法
     * @param userId
     */
    void saveSign(String userId) throws Exception;

    void saveSignByRedis(String userId) throws Exception;

    /**
     * 每天更新签到信息
     * 连续签到和断签的
     */
    void updateDailySign();

    void updateWeekSign();
}
