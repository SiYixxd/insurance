package com.wanghuan.common;

public class Constants {
    //    响应常量
    public final static int SUCCESS_CODE = 10000;
    public final static String SUCCESS_MESSAGE = "成功";
    public final static int ERROR_CODE = 10001;
    public final static String ERROR_MESSAGE = "失败";

    //用户查看文章的一页大小
    public final static int PAGE_SIZE = 5;

    //    文章行为常量
    public final static int NEWS_LIKE = 1;
    public final static int NEWS_DISS = 2;

    //用户签到常量
    public final static int SIGN_TRUE = 1;
    public final static int SIGN_FALSE = 0;

    //redis key 冒号格式是redis 分目录用的  方便可视化管理
    public final static String CACHE_NEWS_ACT_LIKE = "cache::news::act::like::";
    public final static String CACHE_NEWS_ACT_DISS = "cache::news::act::diss::";

    //评论行为常量
    public final static int COMMENT_LIKE = 1;
    public final static int COMMENT_DISS = 2;

    //redis key 评论
    public final static String CACHE_COMMENT_ACT_LIKE = "cache::comment::act::like::";
    public final static String CACHE_COMMENT_ACT_DISS = "cache::comment::act::diss::";

    //redis key 用户的签到状态
    public final static String CACHE_SIGN_ACT_TRUE = "cache::sign::act::true";

    //currentDay 用户当前该签第几天
    public static final String CACHE_SIGN_DAY = "cache::sign::day";
}
