package com.cr.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cr.pojo.Blog;
import com.cr.pojo.Type;
import com.cr.pojo.User;
import com.cr.service.BlogService;
import com.cr.service.TagService;
import com.cr.service.TypeService;
import com.cr.utils.PagesUtils;
import com.cr.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    TypeService typeService;
    BlogService blogService;
    TagService tagService;
    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }
    @Autowired
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }
    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/blogs")
    public String blogs(@RequestParam(defaultValue = "1",value = "currentPage") Integer currentPage, Model model){
        List<Type> types = typeService.list();
        List<Blog> b = blogService.getBlogPage(currentPage);
        Page page = PagesUtils.getPages(currentPage, 10,b);
        model.addAttribute("page",page);
        model.addAttribute("types",types);
        model.addAttribute("aaa","aaa");
        System.out.println(page.getCurrent());
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(@RequestParam(defaultValue = "1",value = "currentPage") Integer currentPage,
                         BlogQuery blogQuery,Model model){
        List<Blog> blogs = blogService.getPageByLike(currentPage, blogQuery);


        Page page = PagesUtils.getPages(currentPage, 2, blogs);
        model.addAttribute("page",page);
        System.out.println(page.getCurrent());
        System.out.println(currentPage);
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("blog",new Blog());
        model.addAttribute("tags",tagService.list());
        model.addAttribute("types",typeService.list());
        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String post(Blog blog, HttpSession session){
        blog.setType(typeService.getById(blog.getType().getId()));
        User user = (User) session.getAttribute("user");
        blog.setUserId(user.getId());
        blog.setTypeId(blog.getType().getId());
        blog.setTags(tagService.listByIds(Arrays.asList(blog.getTagIds().split(","))));
        boolean b;
        System.out.println(blog.getId());
        if (blog.getId() == null){
             blogService.saveBlog(blog);
            System.out.println("----------------------null");
        }
        else {
             blogService.updateBlogById(blog);
        }

        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        Blog blog = blogService.getBlogById(id);
        System.out.println(blog.getId());
        System.out.println(blog.getTags());
        blog.init();
        System.out.println(blog.getTagIds());
        model.addAttribute("blog",blog);
        model.addAttribute("tags",tagService.list());
        model.addAttribute("types",typeService.list());
        return "admin/blogs-input";
    }

    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        int i = blogService.deleteBlogById(id);
        if (i != 0)
            attributes.addFlashAttribute("message","删除成功");
        else
            attributes.addFlashAttribute("message","删除失败");
        return "redirect:/admin/blogs";
    }

//    @PostMapping("/blogs/{curr}")
//    public Page<Blog> blogs(@PathVariable("curr") Integer curr, @RequestBody Blog blog){
//        List<Blog> b = blogService.getB(curr, blog);
//        Page pages = PagesUtils.getPages(curr, 10, b);
//        return pages;
//    }


}
