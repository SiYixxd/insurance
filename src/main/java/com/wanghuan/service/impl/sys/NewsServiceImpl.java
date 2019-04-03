package com.wanghuan.service.impl.sys;

import com.wanghuan.common.Constants;
import com.wanghuan.dao.NewsDao;
import com.wanghuan.model.sys.News;
import com.wanghuan.model.sys.UserNews;
import com.wanghuan.service.sys.NewsService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "newsServiceImpl")
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Override
    public void insertNews(News news) {
        newsDao.insertNews(news);
    }

    @Override
    public void updateNews(News news) {
        newsDao.updateNews(news);
    }

    @Override
    public News findNewsById(String id) {

        return newsDao.findNewsById(id);
    }

    @Override
    //用户分页查看咨询
    public List<News> userFindPager(Map<String, Object> map) {
        int pageIndex = Integer.valueOf(map.get("page").toString());
        List<News> userPageageNews = newsDao.userFindPager(map, new RowBounds(pageIndex * Constants.PAGE_SIZE, Constants.PAGE_SIZE));
        /*
        *   offset是起止，limit是限制，也就是去多少条。pagesize是多大，limit就是多大， 也就是取多少条
            而offset就是从那条记录开始取。
            当pageIndex是0的时候，也就是用户所看到的第一页的时候，那么从pageIndex*pageSize开始，也就是从第零条开始取。
            当pageIndex是1的时候，也就是用户所看到的第二页的时候，那么从pageIndex*pageSize开始，1*5，也就是从第五条开始取。
            因为索引是包含0的，所以第一页的数据是0——4，第二页的数据就是5——9
        * */
        return userPageageNews;
    }

    @Override
    public News findNewsByAdminUser(String adminUser) {
        return newsDao.findNewsByAdminUser(adminUser);
    }

    /**
     * //第一种 物理分页 每次都访问数据库
     * /*String 索引?  Object news对象，
     * select * from table where ... order by ... desc limit start, length
     * length 一页的9...
     * request 里面前端传来的参数 pageNum  0 第一页大小  20条
     * start 参数不固定
     * 第一页 0,19  20,3  1 第二页 ...=> start = pageNum * length
     * 第二种 逻辑分页 从数据库中拿出所有符合要求的数据，然后再从这些数据中拿到我们需要的分页数据
     * // 说明: new RowBounds(0, 5)，即第一页，每页取5条数据
     * offset:起始行数
     * limit：需要的数据行数
     *
     * @param map
     * @return
     */
    @Override
    public List<News> adminFindPager(Map<String, Object> map) {
        int pageSize = 5;
        int page = Integer.valueOf(map.get("page").toString()) - 1;
        List<News> pageNews = newsDao.adminFindPager(map, new RowBounds(page * pageSize, pageSize));
        return pageNews;
    }

    @Override
    public void removeNews(String id) {
        newsDao.removeNews(id);
    }

    @Override
    public void removeMutipleNews(List<String> ids) {
        newsDao.removeMutipleNews(ids);
    }

    @Override
    public long selectCountByMap(HashMap<String, Object> map) {
        long count = newsDao.selectCountByMap(map);
        return count;
    }
}
