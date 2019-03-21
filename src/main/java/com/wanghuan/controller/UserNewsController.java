package com.wanghuan.controller;

import com.wanghuan.common.Constants;
import com.wanghuan.controller.request.LikeOrDissRequest;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@Slf4j
public class UserNewsController {
    @Resource(name = "newsServiceImpl")
    private NewsService newsService;

    @Resource
    private RedisUtil redisUtil;


    //用户在APP上查看咨询列表 倒叙 分页
    @PostMapping(value = "/userFindPager")
    public UserNewsResponse userFindNews(@RequestBody UserPageRequest request) {
        UserNewsResponse response = new UserNewsResponse(); //response
        try {
            Boolean endFlag = false;
            HashMap<String , Object> map = new HashMap<>(); //用于传递数据到service和dao
            map.put("page", request.getPage());//用户要查看第几页，page就是几
            //返回一个第二页的list，要多少，查多少。pageSize是20，所以返回20条
            //可能就返回部分内容。几行文字
            List<News> list =  newsService.userFindPager(map);  //查询出的当前页数据
            //遍历list，
            list.forEach(news -> {
                //得到文章内容
                String content = news.getNewsContent();
                //切割文章内容，切割前100个字符。
                String shortContent = StringUtils.substring(content,0,100);
            });
            List<NewsVO> voList = new ArrayList<>();    //准备封装的最后返回的list
            getLikeAndDissCount(list,voList, request.getNewsId());//因为点赞数在redis缓存中，而不需要从数据库获取
            //response.setList(voList);
            //list.size 小于了5，那么这就是最后一页，endFlag就为true，然后就没有下一页了，就告诉用户，这是最后一页。
            if(list.size() < Constants.PAGE_SIZE)
                endFlag = true; //是否最后一页的判断
            response.setEndflag(endFlag);
            response.setList(voList);
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;//code message ,list, endFlag ,  // page
        } catch (Exception e) {
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }
    }

    /**
     * 封装最后返回包括点赞数的文章分页数据
     * @param list
     * @param voList
     * @param newsId
     */
    private void getLikeAndDissCount(List<News> list, List<NewsVO> voList, String newsId){
        list.forEach(news -> {
            NewsVO vo  = new NewsVO();
            BeanUtils.copyProperties(news, vo); //拷贝所有属性
            //点赞的量
            Map likeMap = redisUtil.hgetAll(Constants.CACHE_NEWS_ACT_LIKE + newsId);
            if(likeMap != null){
                vo.setLikeCount(likeMap.size());
            }else {
                vo.setLikeCount(0);
            }
            //踩 的量
            Map dissMap = redisUtil.hgetAll(Constants.CACHE_NEWS_ACT_DISS + newsId);
            if(dissMap != null){
                vo.setDissCount(dissMap.size());
            }else {
                vo.setDissCount(0);
            }
            voList.add(vo);
        });
    }

    /**
     * app接口  用户获取文章详情 里面包括文章的具体内容  和 用户的点赞行为 文章所有点赞数量
     * @param request
     * @return
     */
    @PostMapping(value = "/getNewsDetail")
    public NewsDetailResponse getNewsDetail(@RequestBody UserPageRequest request) {
        NewsDetailResponse response = new NewsDetailResponse();
        NewsDetailVO vo = new NewsDetailVO();
        //1 查询出文章详情
        News news = newsService.findNewsById(request.getNewsId());
        BeanUtils.copyProperties(news, vo);
        //2 设置用户的文章行为
        //通过request获取了用户id，咨询id再通过hget方法获取到了  NEWS_LIKE 的int值
        String likeCache = redisUtil.hget(Constants.CACHE_NEWS_ACT_LIKE + request.getNewsId(), request.getUserId());
        if(StringUtils.isNoneBlank(likeCache)){  //判断只有要有缓存就是点过赞了
            vo.setLikeFlag(true);
        }else vo.setLikeFlag(false);

        String dissCache = redisUtil.hget(Constants.CACHE_NEWS_ACT_DISS + request.getNewsId(), request.getUserId());
        if(StringUtils.isNoneBlank(dissCache)){ //判断只有要有缓存就是踩过了
            vo.setDissFlag(true);
        }else vo.setDissFlag(false);

        //3 返回信息
        response.setCode(Constants.SUCCESS_CODE);
        response.setMessage(Constants.SUCCESS_MESSAGE);
        response.setData(vo);
        return response;
    }

    //....
    //用户点赞咨询 肯定要用缓存做。 综合自己的内存和redis， 首选redis来操作
//    @PostMapping(value = "/likeOrDissNews")
//    public BaseResponse likeOrDissNews(@RequestBody LikeOrderDissRequest request) {
//        BaseResponse response = new BaseResponse();
//        try{
//            switch ( request.getAct()){
//                case Constants.NEWS_LIKE:
//                    //逻辑
//                    break;
//                case Constants.NEWS_DISS:
//                    //逻辑
//                    break;
//                default:
//                    response.setCode(10003);
//                    response.setMessage("请求参数超出范围！");
//                    return response;
//            }
//            response.setCode(Constants.SUCCESS_CODE);
//            response.setMessage(Constants.SUCCESS_MESSAGE);
//            return response;
//        }catch (Exception e){
//            response.setCode(10001);
//            response.setMessage("请求失败...");
//            return response;
//        }
//    }

    /**
     * APP 用接口 点赞或者踩文章的接口
     * @param request
     * @return
     */
    @PostMapping(value = "/likeOrDissNews")
    public BaseResponse likeOrDissNews(@RequestBody LikeOrDissRequest request) {
        BaseResponse response = new BaseResponse();
        try{
           if(request.getAct() == Constants.NEWS_LIKE){
               //点赞
               //记录点赞缓存 key是唯一的，
               redisUtil.hset(Constants.CACHE_NEWS_ACT_LIKE + request.getNewsId(), request.getUserId(), "1");
               //点赞时候把踩删除掉
               redisUtil.hdel(Constants.CACHE_NEWS_ACT_DISS + request.getNewsId(), request.getUserId());
           }else {
               //踩
               //踩的时候把点赞删除
               redisUtil.hset(Constants.CACHE_NEWS_ACT_DISS + request.getNewsId(), request.getUserId(), "2");
               redisUtil.hdel(Constants.CACHE_NEWS_ACT_LIKE + request.getNewsId(), request.getUserId());
           }
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        }catch (Exception e){
            response.setCode(10001);
            response.setMessage("请求失败...");
            return response;
        }
    }

}
