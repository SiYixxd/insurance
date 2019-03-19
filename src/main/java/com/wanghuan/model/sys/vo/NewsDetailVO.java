package com.wanghuan.model.sys.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewsDetailVO {
    private String newsId;
    private String newsTitle;
    private String newsContent;
    private String newsImage;
    private String newsSource;
    private Boolean likeFlag;
    private Boolean dissFlag;

    private int likeCount;
    private int dissCount;
}
