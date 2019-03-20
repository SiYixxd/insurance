package com.wanghuan.service.impl.sys;

import com.wanghuan.common.Constants;
import com.wanghuan.dao.CommentDao;
import com.wanghuan.model.sys.Comment;
import com.wanghuan.model.sys.vo.CommentVO;
import com.wanghuan.service.sys.CommentService;
import com.wanghuan.service.sys.UserInfoService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service(value = "commentServiceImpl")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private UserInfoService userService;

    @Override
    public void insertComment(Comment comment) {
        commentDao.insertComment(comment);
    }

    @Override
    public void removeComment(String commentId) {
        commentDao.removeComment(commentId);
    }

    @Override
    public void updateComment(String commentId) {
        commentDao.updateComment(commentId);
    }

    @Override
    public List<Comment> showComment(Map<String, Object> map) {
        int pageIndex = Integer.valueOf(map.get("page").toString());
        List<Comment> commentList = commentDao.showComment(map, new RowBounds(pageIndex * Constants.PAGE_SIZE, Constants.PAGE_SIZE));
        return commentList;
    }

    @Override
    public List<CommentVO> findCommentLeftJoin(Map<String, Object> map) {
        int pageIndex = Integer.valueOf(map.get("page").toString());
        List<CommentVO> commentVOList = commentDao.findCommentLeftJoin(map, new RowBounds(pageIndex * Constants.PAGE_SIZE, Constants.PAGE_SIZE));
        return commentVOList;
    }

    @Override
    public List<CommentVO> findChildComment(String commentId) {
        List<CommentVO> childCommentVOList = commentDao.findChildComment(commentId);
        return childCommentVOList;
    }

    @Override
    public List<CommentVO> findCommentListByNewsId(String newsId) {
        List<CommentVO> commentVOList = commentDao.findCommentListByNewsId(newsId);
        return commentVOList;
    }


}
