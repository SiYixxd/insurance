package com.wanghuan.controller.request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class CategoryRequest implements Serializable {
    private String categoryId;
    private String categoryName;

}
