package com.cr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cr.dao.TagDao;
import com.cr.dao.TypeDao;
import com.cr.pojo.Tag;
import com.cr.pojo.Type;
import com.cr.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends ServiceImpl<TagDao,Tag> implements TagService {

    TagDao tagDao;

    @Autowired
    public void setTypeDao(TagDao tagDao) {
        this.tagDao = tagDao;
    }



    @Override
    public IPage<Tag> getPage(int currentPage) {
        IPage page = new Page(currentPage,10);
        return tagDao.selectPage(page,null);
    }

    @Override
    public Tag getTagByName(String name) {
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        Tag tag = tagDao.selectOne(wrapper);
        return tag;
    }
}
