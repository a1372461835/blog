package com.cr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cr.pojo.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService extends IService<User> {
    User checkUser(String username,String password);
}
