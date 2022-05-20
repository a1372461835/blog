package com.cr.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cr.dao.BlogAndTagsDao;
import com.cr.dao.BlogDao;
import com.cr.dao.TagDao;
import com.cr.pojo.Blog;
import com.cr.pojo.BlogAndTags;
import com.cr.pojo.Tag;
import com.cr.service.BlogService;
import com.cr.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogDao,Blog> implements BlogService {

    BlogDao blogDao;
    BlogAndTagsDao blogAndTagsDao;
    TagDao tagDao;

    @Autowired
    public void setTagDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Autowired
    public void setBlogAndTagsDao(BlogAndTagsDao blogAndTagsDao) {
        this.blogAndTagsDao = blogAndTagsDao;
    }

    @Autowired
    public void setBlogDao(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    @Override
    public List<Blog> getBlogPage(int currentPage) {
        return blogDao.getBlogPage(currentPage);
    }

    @Override
    public List<Blog> getPageByLike(Integer currentPage, BlogQuery blogQuery) {
        return blogDao.getPageByLike(currentPage, blogQuery);
    }


//    @Override
//    public IPage<Blog> getPageByLike(Page<Blog> page, Blog blog) {
//
//
//        return blogDao.getPageByLike(page,blog);
//    }

//    @Override
//    public IPage<Blog> getPageByLike(Integer currentPage, BlogQuery blogQuery) {
//
//        QueryWrapper<Blog> lqw = new QueryWrapper<Blog>();
//        lqw.like(Strings.isNotEmpty(blogQuery.getTitle()),"title",blogQuery.getTitle());
//        lqw.like("type_id",blogQuery.getTypeId());
//        IPage page = new Page(currentPage,2);
//        blogDao.selectPage(page,lqw);
//
//        return page;
//    }



    @Override
    public List<Blog> getB(int currentPage, Blog blog) {
        return blogDao.getB(currentPage,blog);
    }

    @Override
    @Transactional
    public int saveBlog(Blog blog) {

        int insert = blogDao.insert(blog);
        for (Tag tag : blog.getTags()){
            BlogAndTags blogAndTags = new BlogAndTags();
            blogAndTags.setBlogsId(blog.getId());
            blogAndTags.setTagsId(tag.getId());
            blogAndTagsDao.insert(blogAndTags);
        }
        return insert;
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    @Override
    @Transactional
    public int updateBlogById(Blog blog) {
        List<Tag> tags = blog.getTags();
        boolean equals = tags.equals(tagDao.getTagsByBlogId(blog.getId()));
        if (equals){
            return blogDao.updateById(blog);
        }
        else {
            UpdateWrapper<BlogAndTags> wrapper = new UpdateWrapper<>();
            wrapper.eq("blogs_id",blog.getId());
            blogAndTagsDao.delete(wrapper);
            for (Tag tag : blog.getTags()){
                BlogAndTags blogAndTags = new BlogAndTags();
                blogAndTags.setBlogsId(blog.getId());
                blogAndTags.setTagsId(tag.getId());
                blogAndTagsDao.insert(blogAndTags);
            }
        }
        return blogDao.updateById(blog);
    }

    @Override
    @Transactional
    public int deleteBlogById(Long id) {
        UpdateWrapper<BlogAndTags> wrapper = new UpdateWrapper<>();
        wrapper.eq("blogs_id",id);
        blogAndTagsDao.delete(wrapper);
        return blogDao.deleteById(id);
    }

    @Override
    public List<Blog> getBlogSearch(String query, Integer currentPage) {
        return blogDao.getBlogSearch(query,currentPage);
    }


}
