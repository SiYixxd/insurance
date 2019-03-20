package com.wanghuan.controller;

import com.wanghuan.common.Constants;
import com.wanghuan.controller.request.CommentPageRequest;
import com.wanghuan.controller.request.LikeOrDissRequest;
import com.wanghuan.controller.response.BaseResponse;
import com.wanghuan.controller.response.CommentResponse;
import com.wanghuan.model.sys.Comment;
import com.wanghuan.model.sys.vo.CommentVO;
import com.wanghuan.service.sys.CommentService;
import com.wanghuan.utils.IdGeneratorUtil;
import com.wanghuan.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@Slf4j
public class UserCommentController {

    @Resource(name = "commentServiceImpl")
    private CommentService commentService;

    @Resource
    private RedisUtil redisUtil;


    /**
     * 增加一条评论
     * 用户给咨询评论
     * 就是UserCommentController中的insertComment方法
     *
     */
    @PostMapping(value = "/insertComment")
    public BaseResponse insertComment(@RequestBody CommentPageRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            //新建news对象，将request.get到的属性set到新的news对象中
            Comment comment = new Comment();
            BeanUtils.copyProperties(request, comment);
            comment.setCreateTime(new Date());
            comment.setCommentStatus(0);
            comment.setCommentId(IdGeneratorUtil.generatId());
            commentService.insertComment(comment);
            response.setCode(10000);
            response.setMessage("评论成功");
            return response;
        } catch (Exception e) {
            log.error("评论失败", e);
            response.setCode(10001);
            response.setMessage("抱歉，出错了");
            return response;
        }
    }


    //移除一条评论
    @PostMapping(value = "/removeComment")
    public BaseResponse RemoveComment(@RequestBody CommentPageRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            commentService.removeComment(request.getCommentId());
            return response;
        } catch (Exception e) {
            log.error("删除失败！", e);
            response.setCode(10001);
            response.setMessage("抱歉，出错了");
            return response;
        }
    }

    //更新评论
    @PostMapping(value = "/updateComment")
    public BaseResponse UpdateComment(@RequestBody CommentPageRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            commentService.updateComment(request.getCommentId());
            return response;
        } catch (Exception e) {
            log.error("编辑失败！", e);
            response.setCode(10001);
            response.setMessage("抱歉，出错了");
            return response;
        }
    }


    /**
     * 1 news_ID 谁的 userId  哪篇文章  newsId  的评论 commentId commentContent
     * 2 userName 条件查询  或者 展示userName
     * a  连表查
     * b  先查出列表，再往里面放数据
     * 3 给后台页面用的一般需要总条数   给app用的一般不用总条数，给一个分页结束标志
     * 查看评论列表
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/findCommentLeftJoin")
    public CommentResponse findCommentLeftJoin(@RequestBody CommentPageRequest request) {
        CommentResponse response = new CommentResponse();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("page", request.getPage());
            map.put("userName", request.getPage());
            map.put("commentContent", request.getCommentContent());
            List<CommentVO> list = commentService.findCommentLeftJoin(map);
           List<CommentVO> voList = new ArrayList<>();
          /*  因为需要返回点赞数，需要遍历一下newslisit把数据copy到volist中，再加上点赞数。
           而comment目前没有，所以不需要多此一举。
            list.forEach(comment -> {
                CommentVO vo = new CommentVO();
                BeanUtils.copyProperties(comment, vo);
            });*/
            getLikeAndDissCount(list,voList, request.getNewsId());
            Boolean endFlag = false;
            if (list.size() < Constants.PAGE_SIZE) {
                endFlag = true;
            }
            response.setEndFlag(endFlag);
            response.setList(list);
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (Exception e) {
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }
    }

    private void getLikeAndDissCount(List<CommentVO> list, List<CommentVO> voList, String commentId){
        list.forEach(comment -> {
            CommentVO vo  = new CommentVO();
            BeanUtils.copyProperties(comment, vo); //拷贝所有属性
            //点赞的量
            Map likeMap = redisUtil.hgetAll(Constants.CACHE_COMMENT_ACT_LIKE + commentId);
            if(likeMap != null){
                vo.setLikeCount(likeMap.size());
            }else {
                vo.setLikeCount(0);
            }
            //踩 的量
            Map dissMap = redisUtil.hgetAll(Constants.CACHE_COMMENT_ACT_DISS + commentId);
            if(dissMap != null){
                vo.setDissCount(dissMap.size());
            }else {
                vo.setDissCount(0);
            }
            voList.add(vo);
        });
    }




    /**
     * APP 用接口 点赞或者踩评论的接口
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/likeOrDissComment")
    public BaseResponse likeOrDissComment(@RequestBody LikeOrDissRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            if (request.getAct() == Constants.COMMENT_LIKE) {
                redisUtil.hset(Constants.CACHE_COMMENT_ACT_LIKE + request.getCommentId(), request.getUserId(), "1");
                redisUtil.hdel(Constants.CACHE_COMMENT_ACT_DISS + request.getCommentId(), request.getUserId());
            } else {
                //用户没有操作，就不请求该接口，不用考虑getAct为null的情况
                redisUtil.hset(Constants.CACHE_COMMENT_ACT_DISS + request.getCommentId(), request.getUserId(), "2");
                redisUtil.hdel(Constants.CACHE_COMMENT_ACT_LIKE + request.getCommentId(), request.getUserId());
            }
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (Exception e) {
            response.setCode(10001);
            response.setMessage("请求失败...");
            return response;
        }
    }

    /**
     * 用户查看子评论 列表
     *
     * @param request
     * @return
     */
    public CommentResponse commentDetails(@RequestBody CommentPageRequest request) {
        CommentResponse response = new CommentResponse();
        try {
            String commentId = request.getCommentId();
            List<CommentVO> childCommentList = commentService.findChildComment(commentId);
            response.setList(childCommentList);
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (Exception e) {
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }
    }


    /**
     * 用户通过newsId来查看评论列表
     *
     * 用户查看咨询详情的时候，点击查看评论，然后把评论表中对应newsId的数据查出来并显示。
     * @param request
     * @return
     */
    @PostMapping(value = "/findCommentListByNewsId")
    public CommentResponse findCommentListByNewsId(@RequestBody CommentPageRequest request) {
        CommentResponse response = new CommentResponse();
        try {
            List<CommentVO> voList = commentService.findCommentListByNewsId(request.getNewsId());
        /*    Boolean endFlag = false;
            if (voList.size() < Constants.PAGE_SIZE) {
                endFlag = true;
            }
            response.setEndFlag(endFlag);*/
            response.setList(voList);
            response.setCode(Constants.SUCCESS_CODE);
            response.setMessage(Constants.SUCCESS_MESSAGE);
            return response;
        } catch (Exception e) {
            response.setCode(Constants.ERROR_CODE);
            response.setMessage(Constants.ERROR_MESSAGE);
            return response;
        }
    }

}
