package com.wanghuan.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@ToString
public class AdminPageRequest implements Serializable {
    private String newsTitle;
    private String newsContent;
    private String newsId;
    private int page;
    private String adminUser;
    private String newsSource;
    //添加时间条件查询
    private String startTime;
    private String endTime;

}
