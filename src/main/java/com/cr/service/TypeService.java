package com.cr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cr.pojo.Type;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TypeService extends IService<Type> {

    IPage<Type> getPage(int currentPage);
    Type getTypeByName(String name);
}
