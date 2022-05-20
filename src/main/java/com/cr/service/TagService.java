package com.cr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cr.pojo.Tag;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TagService extends IService<Tag> {
    IPage<Tag> getPage(int currentPage);
    Tag getTagByName(String name);
}
