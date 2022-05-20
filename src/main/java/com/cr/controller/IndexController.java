package com.cr.controller;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cr.pojo.Blog;
import com.cr.service.BlogService;
import com.cr.service.TagService;
import com.cr.service.TypeService;
import com.cr.utils.PagesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    BlogService blogService;
    TypeService typeService;
    TagService tagService;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @Autowired
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping("/")
    public String index(@RequestParam(defaultValue = "1",value = "currentPage") Integer currentPage, Model model){
        List<Blog> blogs = blogService.getBlogPage(currentPage);
        Page page = PagesUtils.getPages(currentPage, 8, blogs);
        page.addOrder(OrderItem.desc("update_time"));
        model.addAttribute("page",page);
        model.addAttribute("types",typeService.list());
        model.addAttribute("tags",tagService.list());
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam(defaultValue = "1",value = "currentPage") Integer currentPage, Model model,@RequestParam String query){
        List<Blog> blogs = blogService.getBlogSearch(query, currentPage);
        Page page = PagesUtils.getPages(currentPage, 8, blogs);
        model.addAttribute("page",page);
        return "search";
    }
}
