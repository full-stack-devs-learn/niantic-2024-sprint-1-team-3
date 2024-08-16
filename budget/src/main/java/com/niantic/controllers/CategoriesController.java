package com.niantic.controllers;

import com.niantic.models.Category;
import com.niantic.models.Vendor;
import com.niantic.services.CategoryDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class CategoriesController {
    private CategoryDao categoryDao = new CategoryDao();

    @GetMapping("/categories")

    public String getAllCategories (Model model)
    {
        ArrayList<Category> categories = categoryDao.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("pageTitle", "All Categories");

        return "categories/index";
    }

    @GetMapping("/categories/add")
    public String addCategory (Model model)
    {
        model.addAttribute("category", new Category());
        model.addAttribute("action", "add");
        model.addAttribute("pageTitle", "Add Category");

        return "categories/add_edit";
    }

    @PostMapping ("/categories/add")
    public String addCategory(Model model, @ModelAttribute("category") Category category)
    {
        categoryDao.addCategory(category);
        model.addAttribute("category", category);

        return "redirect:/categories";
    }

    @GetMapping("categories/{id}/edit")
    public String editCategory(Model model, @PathVariable int id)
    {
        Category category = categoryDao.getCategoryById(id);

        model.addAttribute("category", category);
        model.addAttribute("action", "edit");

        return "categories/add_edit";
    }

    @PostMapping("users/{id}/edit")
    public String editCategory(@ModelAttribute("category") Category category, @PathVariable int id)
    {
        category.setCategoryId(id);
        categoryDao.updateCategory(category);

        return "redirect:/categories";
    }

    @GetMapping("categories/{id}/delete")
    public String deleteCategory(Model model, @PathVariable int id)
    {
        Category category = categoryDao.getCategoryById(id);

        if(category == null)
        {
            model.addAttribute("message", String.format("There is no category with id %d.", id));
            return "404";
        }

        model.addAttribute("category", category);
        return "categories/delete";
    }

    @PostMapping("categories/{id}/delete")
    public String deleteCategory(@PathVariable int id)
    {
        categoryDao.deleteCategory(id);
        return "redirect:/categories";
    }
}
