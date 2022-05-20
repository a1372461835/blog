package com.cr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cr.pojo.Blog;
import com.cr.vo.BlogQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogService extends IService<Blog> {
    List<Blog> getBlogPage(@Param("currentPage") int currentPage);
    //IPage<Blog> getPageByLike(Page<Blog> page, Blog blog);
    //IPage<Blog> getPageByLike(Integer currentPage, BlogQuery blogQuery);
    List<Blog> getPageByLike(Integer currentPage, BlogQuery blogQuery);
    List<Blog> getB(@Param("currentPage") int currentPage, @Param("blog") Blog blog);
    int saveBlog(Blog blog);
    Blog getBlogById(@Param("id") Long id);
    int updateBlogById(Blog blog);
    int deleteBlogById(Long id);
    List<Blog> getBlogSearch(String query,Integer currentPage);

}
