package com.wanghuan.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class SetRequest implements Serializable {
    private String imageUrl;
    private String mobile;
    private String userName;
}
