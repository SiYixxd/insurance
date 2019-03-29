package com.wanghuan.common;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceException extends Exception {

    private int code;
    private String message;
}
