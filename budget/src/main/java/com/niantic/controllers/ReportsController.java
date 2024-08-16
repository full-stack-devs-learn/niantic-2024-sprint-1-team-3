package com.niantic.controllers;

import com.niantic.models.Transaction;
import com.niantic.services.TransactionDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class ReportsController {

    private TransactionDao transactionDao = new TransactionDao();

    @GetMapping("/reports/user")

    public String getTransactionByUser (Model model, @RequestParam(required = false, name = "user") Integer id)
    {
        if(id == null)
        {
            id = transactionDao.getTransactionsLastFive().getFirst().getUserId();

        }
        ArrayList<Transaction> transactions = transactionDao.getTransactionByUser(id);
        model.addAttribute("transactions", transactions);

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

        return "reports/index";
    }

    @GetMapping("/reports/year")

    public String getTransactionByYear (Model model, Integer year)
    {
        if(year == null) {
            year = transactionDao.getTransactionsLastFive().getFirst().getTransactionDate().getYear();
        }
        ArrayList<Transaction> transactions = transactionDao.getTransactionByYear(year);
        model.addAttribute("transactions", transactions);

        return "reports/index";
    }

    @GetMapping("/reports/category")
    public String getTransactionByCategory (Model model, Integer id)
    {
        ArrayList<Transaction> transactions = transactionDao.getTransactionByCategory(id);
        model.addAttribute("transactions", transactions);

        return "reports/index";
    }
}
