package com.wanghuan.model.sys;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class Comment implements Serializable {
    private String pkSid;
    private String commentId;
    private String userId;
    private String commentContent;
    private String parentId;
    private Date createTime;
    private int commentStatus;
    private int newsId;
}
