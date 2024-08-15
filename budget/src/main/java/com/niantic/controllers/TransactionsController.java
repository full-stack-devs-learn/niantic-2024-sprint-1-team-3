package com.niantic.controllers;

import com.niantic.models.Transaction;
import com.niantic.services.TransactionDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class TransactionsController
{
    private TransactionDao transactionDao = new TransactionDao();

    @GetMapping ("/transactions")
    public String getTransactionsLastFive (Model model)
    {
        ArrayList<Transaction> transactions = transactionDao.getTransactionsLastFive();
        model.addAttribute("transactions", transactions);
        model.addAttribute("pageTitle", "hello");

        return "transactions/index";
    }
}
