package com.wanghuan.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class NewsRequest  implements Serializable {
    private String newsId;
    private String adminUser;
    private String newsTitle;
    private String newsContent;
    private String newsImage;
    private String newsSource;
    private String newsIds;
}
