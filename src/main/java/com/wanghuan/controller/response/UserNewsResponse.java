package com.wanghuan.controller.response;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserNewsResponse implements Serializable {
    private int code;
    private int page;
    private String message;
    private boolean endflag;
    private Object list;

}
