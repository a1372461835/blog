package com.cr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cr.dao.BlogAndTagsDao;
import com.cr.pojo.BlogAndTags;
import com.cr.service.BlogAndTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogAndTagsServiceImpl extends ServiceImpl<BlogAndTagsDao, BlogAndTags> implements BlogAndTagsService {
    BlogAndTagsService blogAndTagsService;

    @Autowired
    public void setBlogAndTagsService(BlogAndTagsService blogAndTagsService) {
        this.blogAndTagsService = blogAndTagsService;
    }
}
