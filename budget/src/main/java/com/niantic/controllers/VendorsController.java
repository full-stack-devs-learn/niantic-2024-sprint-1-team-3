package com.niantic.controllers;

import com.niantic.models.Vendor;
import com.niantic.services.VendorDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class VendorsController {
    private VendorDao vendorDao = new VendorDao();

    @GetMapping ("/vendors")

    public String getAllVendors (Model model)
    {
        ArrayList<Vendor> vendors = vendorDao.getAllVendors();
        model.addAttribute("vendors", vendors);
        model.addAttribute("pageTitle", "All Vendors");

        return "vendors/index";
    }

    @GetMapping ("/vendors/add")
    public String addVendor(Model model)
    {
        model.addAttribute("vendor", new Vendor());
        model.addAttribute("action", "add");
        model.addAttribute("pageTitle", "Add vendor");

        return "vendors/add_edit";
    }
    @PostMapping ("/vendors/add")
    public String addVendor(Model model, @ModelAttribute("vendor") Vendor vendor)
    {
        vendorDao.addVendor(vendor);
        model.addAttribute("vendor", vendor);

        return "redirect:/vendors";
    }

    @GetMapping ("vendors/{id}/edit")
    public String editVendor (Model model, @PathVariable int id)
    {
        Vendor vendor = vendorDao.getVendorById(id);

        model.addAttribute("vendor", vendor);
        model.addAttribute("action", "edit");
        model.addAttribute("pageTitle", "Edit Vendor");

        return "vendors/add_edit";
    }

    @PostMapping ("vendors/{id}/edit")
    public String editVendor (@ModelAttribute("vendor") Vendor vendor, @PathVariable int id)
    {
        vendor.setVendorId(id);
        vendorDao.updateVendor(vendor);

        return "redirect:/vendors";
    }

    @GetMapping ("vendors/{id}/delete")
    public String deleteVendor(Model model, @PathVariable int id)
    {
        Vendor vendor = vendorDao.getVendorById(id);

        if(vendor == null)
        {
            model.addAttribute("message", String.format("There is no vendor with id %d.", id));
            return "404";
        }

        model.addAttribute("vendor", vendor);
        model.addAttribute("pageTitle", "Delete Vendor");
        return "vendors/delete";
    }

    @PostMapping("vendors/{id}/delete")

    public String deleteVendor(@PathVariable int id)
    {
        vendorDao.deleteVendor(id);
        return "redirect:/vendors";
    }

}
