package com.wanghuan.model.sys;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Admin implements Serializable {

    private String username;
    private String password;
}
