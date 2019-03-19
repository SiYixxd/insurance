package com.wanghuan.dao;

import com.wanghuan.model.sys.News;
import com.wanghuan.model.sys.UserNews;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface NewsDao {
    /*增加一篇新资讯
    @param news
    * */
    public void insertNews(News news);

    /*根据ID删除一篇咨询
     * @param news
     * */
    public void removeNews(String newsId);

    /*批量移除咨询 用到了mybatis遍历  这里的删除一般都是软删除 即更新一个status字段，查询的时候where 判断领一个状态
     * @param news
     * */
    public void removeMutipleNews(List<String> ids);

    /*更新一篇咨询
    * @param news
    * */
    public void updateNews(News news);

    /*后台人员分页查看咨询
     * */
    List<News> adminFindPager(Map<String, Object> map, RowBounds rowBounds);

    /*根据id查询咨询
     * @param id
     * */
    public News findNewsById(@Param("newsId") String id);

    /*根据AdminUser查询咨询
     * @param adminUser
     * */
    public News findNewsByAdminUser(@Param("adminUser")String adminUser);

    /*用户查看咨询
    * @param newsTitle
    * 用户查看咨询的部分内容，不需要全部咨询全部信息，
    * */
    //news不返回全部内容，UserNews？
    List<News> userFindPager(Map<String, Object> map, RowBounds rowBounds);

    /*根据标题查询咨询
     * @param id
     * */
    public News findNewsByTitle(@Param("newsTitle") String title);

    //查询数据库中总记录条数，分页相关
    long selectCountByMap(HashMap<String, Object> map);
}
