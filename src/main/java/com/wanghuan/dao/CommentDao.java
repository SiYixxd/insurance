package com.wanghuan.dao;

import com.wanghuan.model.sys.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface CommentDao {
    //添加评论
    void insertComment(Comment comment);
    //删除评论
    void removeComment(String commentId);
    //更新评论
    void updateComment(String commentId);
    //查找评论 分页显示
    List<Comment> showComment(Map<String, Object> map, RowBounds rowBounds);

    List<Comment> findCommentLeftJoin(Map<String, Object> map, RowBounds rowBounds);


    //点赞评论

}
