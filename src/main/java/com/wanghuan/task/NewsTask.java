package com.wanghuan.task;

import com.wanghuan.model.sys.News;
import com.wanghuan.service.sys.NewsService;
import com.wanghuan.utils.SpringUtil2;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class NewsTask implements BaseJob {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("我的job执行了");
        //定时删除我的资讯
        NewsService newsService = SpringUtil2.getApplicationContext().getBean(NewsService.class);
        News news = newsService.findNewsById("456");
        System.out.println(news.toString());

    }
}
