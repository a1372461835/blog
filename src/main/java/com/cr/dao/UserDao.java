package com.cr.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cr.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
    User checkUser(String username,String password);

    void selectOne();
}
