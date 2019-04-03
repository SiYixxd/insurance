package com.wanghuan.service.sys;

import com.wanghuan.model.sys.News;
import com.wanghuan.model.sys.UserNews;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface NewsService {
    //添加新资讯
    public void insertNews(News news);

    //更新咨询
    public void updateNews(News news);

    //通过咨询ID查咨询
    public News findNewsById(String id);

    //通过作者查咨询
    public News findNewsByAdminUser(String adminUser);

    //后台人员分页查询咨询
    List<News> adminFindPager(Map<String, Object> map);

    //用户分页查看咨询
    List<News> userFindPager(Map<String, Object> map);

    //根据ID移除一篇咨询
    public void removeNews(String id);

    //批量移除咨询
    public void removeMutipleNews(List<String> ids);

    long selectCountByMap(HashMap<String, Object> map);
}
