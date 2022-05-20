package com.cr.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cr.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TagDao extends BaseMapper<Tag> {

    Tag getTagsByBlogId(@Param("id") Long id);
}
