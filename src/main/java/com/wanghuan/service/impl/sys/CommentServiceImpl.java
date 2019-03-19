package com.wanghuan.service.impl.sys;

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
    public List<CommentVO> showComment(Map<String, Object> map) {
        int pageIndex =Integer.valueOf(map.get("page").toString()); //0
        int pageSize = 5;
        //条数
        List<Comment> commentList = commentDao.showComment(map,new RowBounds(pageIndex*pageSize, pageSize));
       //第二种获取userName的方法
        List<CommentVO> commentVOList = new ArrayList<>();
        commentList.forEach(comment -> {
            CommentVO vo = new CommentVO();
            BeanUtils.copyProperties(comment, vo);
            //todo
//            UserInfo userInfo = userService.findByUserId(comment.getUserId());
            vo.setUserName("");
        });

        return commentVOList;
    }


}
