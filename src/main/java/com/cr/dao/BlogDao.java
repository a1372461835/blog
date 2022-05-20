package com.cr.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cr.pojo.Blog;;
import com.cr.vo.BlogQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogDao extends BaseMapper<Blog> {

    List<Blog> getBlogPage(@Param("currentPage") int currentPage);
    List<Blog> getB(@Param("currentPage") int currentPage,Blog blog);

    List<Blog> getPageByLike(Integer currentPage, BlogQuery blogQuery);
    List<Blog> getBlogSearch(String query,Integer currentPage);
    Blog getBlogById(@Param("id") Long id);
    int updateBlogById(Blog blog);
}
