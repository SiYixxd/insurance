package com.wanghuan.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class CommentPageRequest implements Serializable {
    private String commentId;
    private String parentId;
    private String commentContent;
    private String userName;
    private String newsId;
    private int page;

    //添加时间条件查询
    private String startTime;
    private String endTime;
}
