package com.cr.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cr.pojo.Tag;
import com.cr.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagController {

    TagService tagService;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/tags")
    public String tags(@RequestParam(defaultValue = "1",value = "currentPage") Integer currentPage, Model model){
        IPage<Tag> page = tagService.getPage(currentPage);
        model.addAttribute("page",page);
        System.out.println(page);
        return "admin/tags";
    }


    @GetMapping("tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.getById(id));
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String post(@Valid Tag tag, RedirectAttributes attributes, BindingResult result){
        Tag tag1 = tagService.getTagByName(tag.getName());
        System.out.println(tag1);
        if (tag1 != null)
            result.rejectValue("name","nameError","不能添加重复标签");
        if (result.hasErrors())
            return "admin/tags-input";
        boolean b = tagService.save(tag);
        if (b)
            attributes.addFlashAttribute("message","操作成功");
        else
            attributes.addFlashAttribute("message","操作失败");
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags/{id}")
    public String post(@Valid Tag tag,BindingResult result,RedirectAttributes attributes,@PathVariable Long id){
        Tag tag1 = tagService.getTagByName(tag.getName());
        System.out.println(tag1);
        if (tag1 != null)
            result.rejectValue("name","nameError","不能添加重复分类");
        if (result.hasErrors())
            return "admin/tags-input";
        boolean b = tagService.updateById(tag);
        if (b)
            attributes.addFlashAttribute("message","更新成功");
        else
            attributes.addFlashAttribute("message","更新失败");
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        boolean b = tagService.removeById(id);
        if (b)
            attributes.addFlashAttribute("message","删除成功");
        else
            attributes.addFlashAttribute("message","删除失败");
        return "redirect:/admin/tags";
    }
}
