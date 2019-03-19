package com.wanghuan.controller;

import com.wanghuan.common.Constants;
import com.wanghuan.controller.request.LikeOrDissRequest;
import com.wanghuan.controller.request.NewsRequest;
import com.wanghuan.controller.request.UserPageRequest;
import com.wanghuan.controller.response.BaseResponse;
import com.wanghuan.controller.response.NewsDetailResponse;
import com.wanghuan.model.sys.News;
import com.wanghuan.model.sys.UserNews;
import com.wanghuan.model.sys.vo.NewsDetailVO;
import com.wanghuan.model.sys.vo.NewsVO;
import com.wanghuan.service.sys.NewsService;
import com.wanghuan.utils.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReUserNewsController {

    @Resource(name = "newsServiceImpl")
    private NewsService newsService;

    @Resource
    private RedisUtil redisUtil;


    //用户查看咨询列表
    public BaseResponse yonghuchakan(@RequestBody UserPageRequest request ){
        BaseResponse response = new BaseResponse();
        Map map = new HashMap();

        List<NewsVO>  list =  newsService.userFindPager(map);


        return  response;
    }



    //用户查看咨询详情
    /**
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/userFindDetails")
    public NewsDetailResponse userFindDetails (@RequestBody NewsRequest request){
        NewsDetailResponse response = new NewsDetailResponse();
        //获取用户要看的哪条咨询
        String id  =request.getNewsId();
        //或者我已经有了一个UserNews，vo总是比原来的多一些属性。因为要加点赞功能
        //新建一个UserNews，用来接受指定的属性， UserNews a = new UserNews();
        NewsDetailVO newsVO = new NewsDetailVO();
        News news =  newsService.findNewsById(id);
        BeanUtils.copyProperties(news,newsVO);
        //增加点赞
        String cache = redisUtil.hget(request.getNewsId()+Constants.CACHE_NEWS_ACT_LIKE,request.getNewsId());
//        if (cahe)
        response.setData(newsVO);
        return response;
    }


    /**
     * 用户给咨询点赞
     * @param request
     * @return
     */
    @PostMapping(value = "/dianzan")
    public BaseResponse dianzan(@RequestBody LikeOrDissRequest request){
        BaseResponse response = new BaseResponse();
       int act =request.getAct();
       Integer integer = Integer.valueOf(act);
       if(integer==null){

       }
        if (act ==1){
            //说明了用户喜欢这个咨询
            redisUtil.hset(request.getNewsId()+ Constants.NEWS_LIKE,request.getUserId(),"1");
            redisUtil.hdel(request.getNewsId()+ Constants.NEWS_DISS,request.getUserId());
        }else if (act == 2){
            //说明了用户踩了这个咨询
            redisUtil.hset(request.getNewsId()+ Constants.NEWS_DISS,request.getUserId(),"1");
            redisUtil.hdel(request.getNewsId()+ Constants.NEWS_LIKE,request.getUserId());
        }
        //用户什么也没做?
        response.setMessage(Constants.SUCCESS_MESSAGE);
        response.setCode(Constants.SUCCESS_CODE);
        return response;
    }


}
