package com.wanghuan.service.sys;

import com.wanghuan.model.sys.Comment;
import com.wanghuan.model.sys.vo.CommentVO;
import org.apache.ibatis.session.RowBounds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CommentService  {
    //添加评论
    void insertComment(Comment comment);
    //删除评论
    void removeComment(String commentId);
    //更新评论
    void updateComment(String commentId);
    //查找评论
    List<Comment>  showComment(Map<String, Object> map);
    //查找评论 分页显示 leftJoin
    List<CommentVO> findCommentLeftJoin(Map<String, Object> map);
    //查看子评论
    List<CommentVO> findChildComment(String commentId );
    //通过newsId查看对应的评论列表
    List<CommentVO> findCommentListByNewsId(String newsId);
}
