package com.wanghuan.model.sys;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class UserNews implements Serializable {
    private String adminUser;
    private String newsTitle;
    private String newsContent;
    private String newsImage;
    private String newsSource;
}
