package com.RoboFinanceTest.controller;

import com.RoboFinanceTest.models.Address;
import com.RoboFinanceTest.models.Customer;
import com.RoboFinanceTest.repos.AddressRepo;
import com.RoboFinanceTest.repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Controller
public class CustomerController {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private AddressRepo addressRepo;

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
                findAllCustomers(model);
                return "search";
            }
            List<Customer> customers = customerRepo.findByFirstnameAndLastname(customerData[0], customerData[1]);
            model.addAttribute("customers", customers);
        } else {
            findAllCustomers(model);
        }
        return "search";
    }

    @GetMapping("/addcustomer")
    public String addCustomerMapping() {
        return "addcustomer";
    }

    @PostMapping("/addcustomer")
    public String addCustomerInDatabase(
            @RequestParam(required = false) String first_name,
            @RequestParam(required = false) String last_name,
            @RequestParam(required = false) String middle_name,
            @RequestParam(required = false) String sex,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String street,
            @RequestParam(required = false) String house,
            @RequestParam(required = false) String flat,
            Model model
    ) {


        Customer customer = addCustomer(first_name, last_name, middle_name, sex,
                addAddress(country, region, city, street, house, flat));
        model.addAttribute("message", "Пользователь " + customer.getFirst_name() + " " + customer.getLast_name() +
                "Добавлен!");
        return "addcustomer";
    }

    private Address addAddress(String country, String region, String city, String street, String house, String flat) {
        Timestamp created = new Timestamp(System.currentTimeMillis());
        Address address = new Address(country, region, city, street, house, flat, created);
        addressRepo.save(address);
        System.out.println(address.getId());
        return address;
    }

    private Customer addCustomer(String first_name, String last_name, String middle_name, String sex, Address address) {
        Customer customer = new Customer(address, address, first_name, last_name, middle_name, sex);
        customerRepo.save(customer);
        return customer;

    }

    private void findAllCustomers(Model model) {
        List<Customer> customers = customerRepo.findAll();
        model.addAttribute("customers", customers);
    }
}

