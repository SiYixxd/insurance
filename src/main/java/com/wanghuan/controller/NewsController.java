package com.wanghuan.controller;

import com.wanghuan.controller.request.AdminPageRequest;
import com.wanghuan.controller.request.NewsRequest;
import com.wanghuan.controller.request.UserPageRequest;
import com.wanghuan.controller.response.BaseResponse;
import com.wanghuan.controller.response.NewsResponse;
import com.wanghuan.model.sys.News;
import com.wanghuan.service.sys.NewsService;
import com.wanghuan.utils.IdGeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@Slf4j
public class NewsController {
    @Resource(name = "newsServiceImpl")
    private NewsService newsService;


    //接口0 后台人员查询文章 分页 按照时间倒叙排序
    //request使用不方便，频繁。用一个写一个？
    @PostMapping(value = "/adminFindPager")
    public NewsResponse AdminFindNews(@RequestBody AdminPageRequest adminPageRequest) {
        NewsResponse response = new NewsResponse();
        HashMap<String , Object> map = new HashMap<>();
        map.put("page", adminPageRequest.getPage());
        map.put("newsTitle", adminPageRequest.getNewsTitle());
        map.put("adminUser", adminPageRequest.getAdminUser());
        map.put("newsSource",adminPageRequest.getNewsSource());
        List<News>  list =  newsService.adminFindPager(map);
        response.setTotal(newsService.selectCountByMap(map));
        response.setList(list);
        return response;
    }

    /*新建一个咨询
    @param news
    @return
    * */
    //接口 1  后台人员添加咨询文章
    @PostMapping(value = "/insertNews")
    public BaseResponse insertNews(@RequestBody NewsRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            //新建news对象，将request.get到的属性set到新的news对象中
            News news = new News();
            BeanUtils.copyProperties(request, news);
            news.setCreateTime(new Date());
            news.setNewsStatus(0);
            news.setNewsId(IdGeneratorUtil.generatId());
            newsService.insertNews(news);
            response.setCode(10000);
            response.setMessage("新增咨询成功");
            return response;
        } catch (Exception e) {
            log.error("新增咨询异常", e);
            response.setCode(10001);
            response.setMessage("抱歉，出错了");
            return response;
        }
    }

    //接口2  后台人员修改咨询文章
    @PostMapping(value = "/updateNews")
    public BaseResponse updateNews(@RequestBody NewsRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            //通过newsId来获取要修改的咨询，再set
            News news = new News();
            BeanUtils.copyProperties(request, news);
            newsService.updateNews(news);
            return response;
        } catch (Exception e) {
            log.error("修改咨询异常", e);
            response.setCode(10001);
            response.setMessage("抱歉，出错了");
            return response;
        }
    }

    //接口3 后台人员查看咨询详情
    @PostMapping(value = "/newsDetails")
    //以ID为参数，怎么解决？String id？
    public News getNewsDetailsById(@RequestBody NewsRequest request) {
        News news = newsService.findNewsById(request.getNewsId());
        return news;
    }

    //接口3 后台人员查看咨询详情
    @PostMapping(value = "/newsDetails2")
    //以ID为参数，怎么解决？String id？
    public News getNewsDetailsById(String id) {
        News news = newsService.findNewsById(id);
        return news;
    }
    //移除一篇咨询
    @PostMapping(value = "/removeNews")
    public BaseResponse removeNews(@RequestBody NewsRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            //通过newsId来获取要
            newsService.removeNews(request.getNewsId());
            return response;
        } catch (Exception e) {
            log.error("修改咨询异常", e);
            response.setCode(10001);
            response.setMessage("抱歉，出错了");
            return response;
        }
    }
    //批量移除咨询
    @PostMapping(value = "/removeMutipleNews")
    public BaseResponse removeMutipleNews(@RequestBody NewsRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            //获取传递的多个id字符串
            String  newsIds =  request.getNewsIds();
            String []  ids =  newsIds.split(",");
            newsService.removeMutipleNews(Arrays.asList(ids));
            return response;
        } catch (Exception e) {
            log.error("修改咨询异常", e);
            response.setCode(10001);
            response.setMessage("抱歉，出错了");
            return response;
        }
    }


    //用户在APP上查看咨询列表 倒叙 分页
    @PostMapping(value = "/userFindPager")
    public List<News> userFindNews(@RequestBody UserPageRequest userPageRequest) {
        HashMap<String , Object> map = new HashMap<>();
        map.put("page", userPageRequest.getPage());
        List<News>  list =  newsService.adminFindPager(map);
        return list;
    }




    //....
    //用户点赞咨询

}
