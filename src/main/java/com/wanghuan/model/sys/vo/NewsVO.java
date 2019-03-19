package com.wanghuan.model.sys.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class NewsVO {

    private String newsId;
    private String newsTitle;
    private String newsContent;
    private String newsImage;
    private String newsSource;
    private int likeCount;
    private int dissCount;

}
