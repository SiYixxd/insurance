package com.wanghuan.common;

public class Constants {
//    响应常量
    public final static int SUCCESS_CODE = 10000;
    public final static String SUCCESS_MESSAGE = "成功";

    //    文章行为常量
    public final static int NEWS_LIKE = 1;
    public final static int NEWS_DISS = 2;

    //redis key 冒号格式是redis 分目录用的  方便可视化管理
    public final static String CACHE_NEWS_ACT_LIKE = "cache::news::act::like::";
    public final static String CACHE_NEWS_ACT_DISS = "cache::news::act::diss::";
    //评论行为常量
    public final static int COMMENT_LIKE = 1;
    public final static int COMMENT_DISS = 2;


    public final static String CACHE_COMMENT_ACT_LIKE = "cache::comment::act::like::";
    public final static String CACHE_COMMENT_ACT_DISS = "cache::comment::act::diss::";
}
