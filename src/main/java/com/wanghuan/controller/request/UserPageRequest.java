package com.wanghuan.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class UserPageRequest implements Serializable {
    private String newsId;
    private String userId;
    private String newsTitle;
    private String newsContent;
    private String newsImage;
    private String newsSource;
    private int page;

}
