package com.wanghuan;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class TestString {
    @Test
    public void testQieGe() {
        String content = "是个会意字，从音从十。古代奏音乐，连奏十段才能结束（十，数之终也），" +
                "这十段乐就是一章。所以，文章文章，也有段落。" +
                "文章既从“音乐”里会意出来，应是用文字表达出来的东西，读起来如音乐一样美妙无穷、" +
                "悦耳动听的文字，传诵开来，才配得上“文章”一词的真正含义。";
        String a = StringUtils.substring(content, 0, 20);
        System.out.println(a);
    }
}
