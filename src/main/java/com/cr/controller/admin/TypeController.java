package com.cr.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cr.pojo.Type;
import com.cr.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class TypeController {

    TypeService typeService;

    @Autowired
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("/types")
    public String types(@RequestParam(defaultValue = "1",value = "currentPage") Integer currentPage, Model model){
        IPage<Type> page = typeService.getPage(currentPage);
        model.addAttribute("page",page);
        System.out.println(page.getSize());
        return "admin/types";
    }


    @GetMapping("types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("type",typeService.getById(id));
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(@Valid Type type, RedirectAttributes attributes, BindingResult result){
        Type type1 = typeService.getTypeByName(type.getName());
        System.out.println(type1);
        if (type1 != null)
            result.rejectValue("name","nameError","不能添加重复分类");
        if (result.hasErrors())
            return "admin/types-input";
        boolean b = typeService.save(type);
        if (b)
            attributes.addFlashAttribute("message","操作成功");
        else
            attributes.addFlashAttribute("message","操作失败");
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String post(@Valid Type type,BindingResult result,RedirectAttributes attributes,@PathVariable Long id){
        Type type1 = typeService.getTypeByName(type.getName());
        System.out.println(type1);
        if (type1 != null)
            result.rejectValue("name","nameError","不能添加重复分类");
        if (result.hasErrors())
            return "admin/types-input";
        boolean b = typeService.updateById(type);
        if (b)
            attributes.addFlashAttribute("message","更新成功");
        else
            attributes.addFlashAttribute("message","更新失败");
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        boolean b = typeService.removeById(id);
        if (b)
            attributes.addFlashAttribute("message","删除成功");
        else
            attributes.addFlashAttribute("message","删除失败");
        return "redirect:/admin/types";
    }

}
