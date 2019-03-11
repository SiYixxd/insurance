package com.wanghuan.service.sys;

import com.wanghuan.model.sys.MessageInfo;
import org.apache.ibatis.annotations.Param;

public interface MessageInfoService {

    /*通过用户手机号保存code数据到数据库
    * @param mobile
    * */
    public void insertCode(String mobile);
    /*通过手机号获取code所有信息
    @param mobile
    * */
    public MessageInfo getMsgByMobile (String mobile);

    public void saveMessageInfo(MessageInfo info);

}
