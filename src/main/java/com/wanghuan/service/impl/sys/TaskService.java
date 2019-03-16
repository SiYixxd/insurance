package com.wanghuan.service.impl.sys;

import com.wanghuan.service.sys.NewsService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Timer;
import java.util.TimerTask;

/**
 * java 原生定时任务的例子
 * Timer 方式 扩展性有问题 不建议哈使用
 */
@Component
public class TaskService {

    @Autowired
    NewsService newsService;

//    @PostConstruct
    public void test(){
        //创建定时器对象
        Timer t=new Timer();
        //在3秒后执行MyTask类中的run方法,后面每10秒跑一次
        MyTask task = new MyTask();
        task.setNewsService(newsService);
        t.schedule(task, 3000,10000);
    }

}

@Getter
@Setter
@ToString
class MyTask extends TimerTask {

    private NewsService newsService;



    //MyTask 是new出来的，不是通过spring ioc创建的对象，所以不能依赖注入
//    @Autowired
//    NewsService newsService;

    @Override
    public void run() {
        System.out.println("查询开始");
        newsService.findNewsById("1603");
        System.out.println("查询结束");
    }
}
