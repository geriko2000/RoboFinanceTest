package com.RoboFinanceTest.controller;

import com.RoboFinanceTest.models.Customer;
import com.RoboFinanceTest.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Controller
public class MainController {

    @Autowired
    private CustomerRepo customerRepo;

    @GetMapping("/")
    public String index(Model model) {
        List<Customer> customers = customerRepo.findAll();
        model.addAttribute("customers", customers);
        return "index";
    }

}
