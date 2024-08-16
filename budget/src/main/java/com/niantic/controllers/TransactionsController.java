package com.niantic.controllers;

import com.niantic.models.Transaction;
import com.niantic.services.TransactionDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping ("/transactions/add")
    public String addTransaction (Model model)
    {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("action", "add");
        return "transactions/add_edit";
    }

    @PostMapping ("/transactions/add")
    public String addTransaction (Model model, @ModelAttribute ("transaction") Transaction transaction)
    {
        transactionDao.addTransaction(transaction);
        model.addAttribute("transaction", transaction);
        return "redirect:/transactions";
    }

    @GetMapping ("transactions/{id}/edit")
    public String editTransaction (Model model, @PathVariable int id)
    {
        Transaction transaction = transactionDao.getTransactionById(id);

        model.addAttribute("transaction", transaction);
        model.addAttribute("action", "edit");

        return "transactions/add_edit";
    }

    @PostMapping ("transactions/{id}/edit")
    public String editTransaction (@ModelAttribute("transaction") Transaction transaction, @PathVariable int id)
    {
        transaction.setTransactionId(id);
        transactionDao.updateTransaction(transaction);

        return "redirect:/transactions";
    }

    @GetMapping ("transactions/{id}/delete")
    public String deleteTransaction(Model model, @PathVariable int id)
    {
        Transaction transaction = transactionDao.getTransactionById(id);

        if(transaction == null)
        {
            model.addAttribute("message", String.format("There is no transaction with id %d.", id));
            return "404";
        }

        model.addAttribute("transaction", transaction);
        return "transactions/delete";
    }

   @PostMapping("transactions/{id}/delete")

    public String deleteTransaction (@PathVariable int id)
   {
       transactionDao.deleteTransaction(id);
       return "redirect:/transactions";
   }

   @GetMapping("transactions/reports/user/{id}")

    public String getTransactionByUser (Model model, @PathVariable int id)
   {
       ArrayList<Transaction> transactions = transactionDao.getTransactionByUser(id);
       model.addAttribute("transactions", transactions);

       return "transactions/reports";
   }

    @GetMapping("transactions/reports/month/{month}")

    public String getTransactionByMonth (Model model, @PathVariable int month)
    {
        ArrayList<Transaction> transactions = transactionDao.getTransactionByUser(month);
        model.addAttribute("transactions", transactions);

        return "transactions/reports";
    }

    @GetMapping("transactions/reports/year/{year}")

    public String getTransactionByYear (Model model, @PathVariable int year)
    {
        ArrayList<Transaction> transactions = transactionDao.getTransactionByUser(year);
        model.addAttribute("transactions", transactions);

        return "transactions/reports";
    }

    @GetMapping("transactions/reports/category/{category}")
    public String getTransactionByCategory (Model model, @PathVariable int category)
    {
        ArrayList<Transaction> transactions = transactionDao.getTransactionByCategory(category);
        model.addAttribute("transactions", transactions);

        return "transactions/reports";
    }

    @GetMapping("transactions/{id}")
    public String getTransactionById(Model model, @PathVariable int id)
    {
        var transaction = transactionDao.getTransactionById(id);
        model.addAttribute("transaction", transaction);

        return "transactions/details";
    }
}
