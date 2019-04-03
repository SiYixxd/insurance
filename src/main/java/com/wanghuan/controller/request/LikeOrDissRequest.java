package com.wanghuan.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class LikeOrDissRequest implements Serializable {
    private String userId;
    private String newsId;
    private String commentId;
    //    1 表示喜欢  2 表示不喜欢
    private int act;

}
