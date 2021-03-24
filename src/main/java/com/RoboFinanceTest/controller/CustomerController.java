package com.RoboFinanceTest.controller;

import com.RoboFinanceTest.models.Customer;
import com.RoboFinanceTest.repos.CustomerRepo;
import com.RoboFinanceTest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Controller
public class CustomerController {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CustomerService customerService;

    //Гет маппинг поиска пользователя
    @GetMapping("/search")
    public String searchMapping(
            @RequestParam(required = false, defaultValue = "") String customer,
            Model model) {
        if (!customer.isEmpty()) {
            String[] customerData = customer.trim()
                    .replaceAll("\\s+", " ")
                    .split(" ");
            if (customerData.length != 2) {
                model.addAttribute("message", "Введите в формате *имя фамилия*");
                customerService.findAllCustomers(model);
                return "search";
            }
            List<Customer> customers = customerRepo.findByFirstnameAndLastname(customerData[0], customerData[1]);
            model.addAttribute("customers", customers);
        } else {
            customerService.findAllCustomers(model);
        }
        return "search";
    }

    //Гет маппинг для добавления покупателя
    @GetMapping("/addcustomer")
    public String addCustomerMapping() {
        return "addcustomer";
    }

    // Пост маппинг для добавления покупателя
    @PostMapping("/addcustomer")
    public String addCustomerInDatabase(
            @RequestParam(required = false) String first_name,
            @RequestParam(required = false) String last_name,
            @RequestParam(required = false) String middle_name,
            @RequestParam String sex,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String street,
            @RequestParam(required = false) String house,
            @RequestParam(required = false) String flat,
            Model model) {

        if (customerService.checkCustomerPersonalData(first_name, last_name, middle_name, sex) ||
                customerService.checkCustomerAddressData(country, region, city, street, house, flat)) {
            Customer customer = customerService.addCustomer(first_name, last_name, middle_name, sex,
                    customerService.addAddress(country, region, city, street, house, flat));
            model.addAttribute("message", "Пользователь " + customer.getFirst_name() + " " + customer.getLast_name() +
                    "Добавлен!");
        }

        return "addcustomer";
    }

    // Гет маппинг для смены адреса
    @GetMapping("/changeaddress")
    public String changeAddress(Model model) {
        List<Customer> customers = customerRepo.findAll();
        model.addAttribute("customers", customers);
        return "changeaddress";
    }

    // Гет маппинг для смены адреса нужного пользователя
    @GetMapping("/changeaddress/{id}")
    public String changeAddressById(Model model, @PathVariable String id) {
        try {
            long customerId = Long.parseLong(id);
            if (customerRepo.findById(customerId) == null) {
                return "redirect:/changeaddress";
            }
            String first_name = customerRepo.findById(customerId).getFirst_name();
            String last_name = customerRepo.findById(customerId).getLast_name();
            String middle_name = customerRepo.findById(customerId).getMiddle_name();
            model.addAttribute("customerFirstName", first_name);
            model.addAttribute("customerLastName", last_name);
            model.addAttribute("customerMiddleName", middle_name);
            return "changeaddressbyid";
        } catch (NumberFormatException e) {
            return "redirect:/changeaddress";
        }
    }

    // Пут маппинг для смены адреса нужного пользователя
    @RequestMapping(value = {"/changeaddress/{id}"},
            method = {RequestMethod.POST, RequestMethod.PUT})
    public String changeAddressMapping(@PathVariable String id,
                                       @RequestParam(required = false) String country,
                                       @RequestParam(required = false) String region,
                                       @RequestParam(required = false) String city,
                                       @RequestParam(required = false) String street,
                                       @RequestParam(required = false) String house,
                                       @RequestParam(required = false) String flat,
                                       Model model) {

        if (customerService.checkCustomerAddressData(country, region, city, street, house, flat)) {
            try {
                long customerId = Long.parseLong(id);
                if (customerRepo.findById(customerId) == null) {
                    return "redirect:/changeaddress";
                }
                customerService.changeActualAddress(country, region, city, street, house, flat, customerId);
            } catch (NumberFormatException e) {

            }
        }

        return "redirect:/changeaddress";
    }

}

