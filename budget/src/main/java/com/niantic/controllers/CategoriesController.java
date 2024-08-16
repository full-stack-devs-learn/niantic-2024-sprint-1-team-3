package com.niantic.controllers;

import com.niantic.models.Category;
import com.niantic.models.Vendor;
import com.niantic.services.CategoryDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        model.addAttribute("pageTitle", "Categories");

        return "categories/index";
    }

    @GetMapping("/categories/add")
    public String addCategories (Model model)
    {
        model.addAttribute("categories", new Category());
        model.addAttribute("action", "add");

        return "categories/add_edit";
    }

    @PostMapping ("/categories/add")
    public String addVendor(Model model, @ModelAttribute("category") Category category)
    {
        categoryDao.addCategory(category);
        model.addAttribute("vendor", category);

        return "redirect:/categories";
    }
}
