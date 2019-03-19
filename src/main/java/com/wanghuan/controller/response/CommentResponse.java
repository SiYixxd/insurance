package com.wanghuan.controller.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse implements Serializable {
    private long total;
    private Object list;
    private boolean endFlag;
    private int code;
    private String message;
    private int page;
    public CommentResponse(int code, String message){
        this.code = code;
        this.message = message;
    }
}
