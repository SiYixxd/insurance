package com.wanghuan;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

public class BeanUtilTest {


    @Test
    public void testUtil(){
        Demo1P source = new Demo1P();
        source.setName("1");
        Dem21P target = new Dem21P();
        BeanUtils.copyProperties(source, target);
        System.out.println(source);
        System.out.println(target);
    }
}


@Getter
@Setter
@ToString
class Demo1P{

    private String name;
}


@Getter
@Setter
@ToString
class Dem21P{
    private String username;
    private String name;
}