package com.wanghuan.dao;

import com.wanghuan.model.sys.MessageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sun.plugin2.message.Message;

@Mapper
public interface MessageInfoDao {
    /*新产生的code保存到数据库
     * */
    public void saveMessageInfo(MessageInfo messageInfo);

    /*
     * 通过用户请求的手机号查询出数据库中对应的code order by createTime DESC limit 1
     *  返回的是一个MessageInfoMap对象
     * */
    public MessageInfo getMsgByMobile (@Param("mobile") String mobile);

}
