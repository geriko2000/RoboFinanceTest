package com.RoboFinanceTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddressController {
    @GetMapping("/changeaddress")
    public String changeAddress() {
        return "changeAddress";
    }
}
