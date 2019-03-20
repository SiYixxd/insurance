package com.wanghuan.controller;

import com.wanghuan.common.Constants;
import com.wanghuan.controller.request.LikeOrDissRequest;
import com.wanghuan.controller.request.NewsRequest;
import com.wanghuan.controller.request.UserPageRequest;
import com.wanghuan.controller.response.BaseResponse;
import com.wanghuan.controller.response.NewsDetailResponse;
import com.wanghuan.controller.response.UserNewsResponse;
import com.wanghuan.model.sys.News;
import com.wanghuan.model.sys.vo.NewsDetailVO;
import com.wanghuan.model.sys.vo.NewsVO;
import com.wanghuan.service.sys.NewsService;
import com.wanghuan.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ReUserNewsController {

    @Resource(name = "newsServiceImpl")
    private NewsService newsService;

    @Resource
    private RedisUtil redisUtil;


    //用户查看咨询列表
    public UserNewsResponse yonghuchakan(@RequestBody UserPageRequest request) {
        UserNewsResponse response = new UserNewsResponse();
        //map用来放请求的数据
        Map map = new HashMap();
        //用户要查看第几页
        map.put("page", request.getPage());
        //通过map获取到咨询列表
        List<News> list = newsService.userFindPager(map);
        //新建一个NewsVo，用来存放用户需要查看的咨询内容。有什么属性就拷贝什么属性
        List<NewsVO> voList = new ArrayList<>();
        //遍历循环newsList 并拷贝
        list.forEach(news -> {
            NewsVO vo = new NewsVO();
            BeanUtils.copyProperties(news, vo);
            //从redis中获取到咨询的点赞数
            Map zanMap = redisUtil.hgetAll(Constants.CACHE_NEWS_ACT_LIKE + request.getNewsId());
            if (zanMap != null) {
                vo.setLikeCount(zanMap.size());
            } else {
                vo.setLikeCount(0);
            }
            Map dissMap = redisUtil.hgetAll(Constants.CACHE_NEWS_ACT_DISS + request.getNewsId());
            if (dissMap != null) {
                vo.setDissCount(dissMap.size());
            } else {
                vo.setDissCount(0);
            }
            voList.add(vo);
        });
        //设置结束标记
        boolean endFlag = false;
        //判断list是否结束，还有没有下一页。
        if (list.size()<Constants.PAGE_SIZE){
            endFlag = true;
        }
        //返回封装的数据
        response.setEndflag(endFlag);
        response.setList(voList);
        response.setCode(Constants.SUCCESS_CODE);
        response.setMessage(Constants.SUCCESS_MESSAGE);
        return response;
    }


    /**
     * 用户查看咨询详情  包括看到自己是否点赞过这篇咨询
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/userFindDetails")
    public NewsDetailResponse userFindDetails(@RequestBody NewsRequest request) {
        NewsDetailResponse response = new NewsDetailResponse();
        try {
            //获取用户要看的那条咨询Id
            String id = request.getNewsId();
            // UserNews a = new UserNews();
            News news = newsService.findNewsById(id);
            //新建一个NewsVo，用来接受指定的属性，
            NewsDetailVO newsVO = new NewsDetailVO();
            BeanUtils.copyProperties(news, newsVO);
            //增加点赞
            String likeCache = redisUtil.hget(request.getNewsId() + Constants.CACHE_NEWS_ACT_LIKE, request.getNewsId());
            if (StringUtils.isNotBlank(likeCache)) {
                //说明用户点过这个赞
                newsVO.setLikeFlag(true);
            } else {
                newsVO.setLikeFlag(false);
            }
            String dissCache = redisUtil.hget(request.getNewsId() + Constants.CACHE_NEWS_ACT_LIKE, request.getNewsId());
            if (StringUtils.isNotBlank(dissCache)) {
                //说明用户点过这个踩
                newsVO.setDissFlag(true);
            } else {
                newsVO.setDissFlag(false);
            }
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            response.setData(newsVO);
            return response;
        } catch (BeansException e) {
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }

    }


    /**
     * 用户给咨询点赞
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/dianzan")
    public BaseResponse dianzan(@RequestBody LikeOrDissRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            int act = request.getAct();
            if (act == Constants.COMMENT_LIKE) {
                //说明了用户喜欢这个咨询
                redisUtil.hset(request.getNewsId() + Constants.NEWS_LIKE, request.getUserId(), "1");
                redisUtil.hdel(request.getNewsId() + Constants.NEWS_DISS, request.getUserId());
            } else if (act == Constants.COMMENT_DISS) {
                //说明了用户踩了这个咨询
                redisUtil.hset(request.getNewsId() + Constants.NEWS_DISS, request.getUserId(), "1");
                redisUtil.hdel(request.getNewsId() + Constants.NEWS_LIKE, request.getUserId());
            }
            response.setMessage(Constants.SUCCESS_MESSAGE);
            response.setCode(Constants.SUCCESS_CODE);
            return response;
        } catch (Exception e) {
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }
    }
}