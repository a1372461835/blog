package com.cr.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cr.pojo.BlogAndTags;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface BlogAndTagsDao extends BaseMapper<BlogAndTags> {
}
