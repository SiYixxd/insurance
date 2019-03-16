package com.wanghuan.controller.response;

import com.wanghuan.model.sys.Admin;
import com.wanghuan.model.sys.UserInfo;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponse implements Serializable {
    private long total;

    private Object list;
}
