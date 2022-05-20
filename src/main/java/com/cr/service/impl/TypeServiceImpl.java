package com.cr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cr.dao.TypeDao;
import com.cr.pojo.Type;
import com.cr.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl extends ServiceImpl<TypeDao,Type> implements TypeService {

    TypeDao typeDao;

    @Autowired
    public void setTypeDao(TypeDao typeDao) {
        this.typeDao = typeDao;
    }

    @Override
    public IPage<Type> getPage(int currentPage) {
        IPage page = new Page(currentPage,10);
        return typeDao.selectPage(page,null);
    }

    @Override
    public Type getTypeByName(String name) {
        QueryWrapper<Type> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        Type type = typeDao.selectOne(wrapper);
        return type;
    }
}
