package com.wanghuan.controller.response;

import com.wanghuan.model.sys.vo.NewsDetailVO;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewsDetailResponse implements Serializable {

    private int code;
    private String message;
    private NewsDetailVO data;


}
