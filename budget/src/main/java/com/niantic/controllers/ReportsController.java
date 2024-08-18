package com.niantic.controllers;

import com.niantic.models.Category;
import com.niantic.models.Transaction;
import com.niantic.services.CategoryDao;
import com.niantic.services.TransactionDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class ReportsController {

    private TransactionDao transactionDao = new TransactionDao();
    private CategoryDao categoryDao = new CategoryDao();

    @GetMapping("/reports/user")
    public String getTransactionByUser (Model model, @RequestParam(required = false) Integer user)
    {
        if(user == null)
        {
            user = transactionDao.getTransactionsLastFive().getFirst().getUserId();

        }
        ArrayList<Transaction> transactions = transactionDao.getTransactionByUser(user);
        model.addAttribute("transactions", transactions);
        model.addAttribute("type", "user");
        model.addAttribute("pageTitle", "Report by user");

        return "reports/index";
    }

    @GetMapping("/reports/month")
    public String getTransactionByMonth (Model model, Integer month)
    {
        if(month == null)
        {
            month = transactionDao.getTransactionsLastFive().getFirst().getTransactionDate().getMonthValue();
        }
        ArrayList<Transaction> transactions = transactionDao.getTransactionByMonth(month);
        model.addAttribute("transactions", transactions);
        model.addAttribute("type", "month");
        model.addAttribute("pageTitle", "report by month");

        if(transactions.isEmpty())
        {
            model.addAttribute("message", "There were no transactions that matched this criteria.");
        }

        return "reports/index";
    }

    @GetMapping("/reports/year")
    public String getTransactionByYear (Model model, Integer year)
    {
        if(year == null)
        {
            year = transactionDao.getTransactionsLastFive().getFirst().getTransactionDate().getYear();
        }

        ArrayList<Transaction> transactions = transactionDao.getTransactionByYear(year);
        model.addAttribute("transactions", transactions);
        model.addAttribute("type", "year");
        model.addAttribute("pageTitle", "Report by year");

        if(transactions.isEmpty())
        {
            model.addAttribute("message", "There were no transactions that matched this criteria.");
        }

        return "reports/index";
    }

    @GetMapping("/reports/category")
    public String getTransactionByCategory (Model model, Integer category)
    {
        ArrayList<Category> categories = categoryDao.getAllCategories();

        if(category == null)
        {
            category = transactionDao.getTransactionsLastFive().getFirst().getCategoryId();
        }

        ArrayList<Transaction> transactions = transactionDao.getTransactionByCategory(category);

        model.addAttribute("categories", categories);
        model.addAttribute("transactions", transactions);
        model.addAttribute("type", "category");
        model.addAttribute("pageTitle", "Report by category");

        if(transactions.isEmpty())
        {
            model.addAttribute("message", "There were no transactions that matched this criteria.");
        }

        return "reports/index";
    }

    @GetMapping("/reports/vendor")
    public String getTransactionByVendor (Model model, Integer vendor)
    {
        if(vendor == null)
        {
            vendor = transactionDao.getTransactionsLastFive().getFirst().getVendorId();
        }

        ArrayList<Transaction> transactions = transactionDao.getTransactionByVendor(vendor);
        model.addAttribute("transactions", transactions);
        model.addAttribute("type", "vendor");
        model.addAttribute("pageTitle", "Report by vendor");

        if(transactions.isEmpty())
        {
            model.addAttribute("message", "There were no transactions that matched this criteria.");
        }

        return "reports/index";
    }
}
