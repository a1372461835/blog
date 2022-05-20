package com.cr;

import com.cr.dao.BlogAndTagsDao;
import com.cr.pojo.Blog;
import com.cr.pojo.BlogAndTags;
import com.cr.service.BlogAndTagsService;
import com.cr.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class BlogApplicationTests {

    @Autowired
    UserService userService;
    @Autowired
    BlogAndTagsService blogAndTagsService;
    @Autowired
    BlogAndTagsDao blogAndTagsDao;

    @Test
    void contextLoads() {
        BlogAndTags blogAndTags = new BlogAndTags();
        blogAndTags.setTagsId(3l);
        blogAndTags.setBlogsId(2l);
        blogAndTagsDao.insert(blogAndTags);
    }

}
