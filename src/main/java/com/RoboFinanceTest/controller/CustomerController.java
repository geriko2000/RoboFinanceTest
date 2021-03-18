package com.RoboFinanceTest.controller;

import com.RoboFinanceTest.models.Address;
import com.RoboFinanceTest.models.Customer;
import com.RoboFinanceTest.repos.AddressRepo;
import com.RoboFinanceTest.repos.CustomerRepo;
import com.RoboFinanceTest.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
            Model model
    ) {

        if (!checkCustomerPersonalData(first_name, last_name, middle_name, sex, model) &&
                !checkCustomerAddressData(country, region, city, street, house, flat, model)) {
            return "addcustomer";
        }

        Customer customer = addCustomer(first_name, last_name, middle_name, sex,
                addAddress(country, region, city, street, house, flat));
        model.addAttribute("message", "Пользователь " + customer.getFirst_name() + " " + customer.getLast_name() +
                "Добавлен!");
        return "addcustomer";
    }

    @GetMapping("/changeaddress")
    public String changeaddress(){

        return "changeaddress";
    }

    @PutMapping("/changeaddress/{id}")
    public String changeAddressInDatabase(){

        return "changeaddress";
    }

    /**
     * Добавить адрес в таблицу
     *
     * @param country страна
     * @param region  регион
     * @param city    город
     * @param street  улица
     * @param house   дом
     * @param flat    квартира
     * @return созданный объект адреса
     **/
    private Address addAddress(String country, String region, String city, String street, String house, String flat) {
        Timestamp created = new Timestamp(System.currentTimeMillis());
        Address address = new Address(country, region, city, street, house, flat, created);
        addressRepo.save(address);
        System.out.println(address.getId());
        return address;
    }

    // Добавить покупателя в таблицу
    private Customer addCustomer(String first_name, String last_name, String middle_name, String sex, Address address) {
        Customer customer = new Customer(address, address, first_name, last_name, middle_name, sex);
        customerRepo.save(customer);
        return customer;

    }

    // Показать всех покупателей
    private void findAllCustomers(Model model) {
        List<Customer> customers = customerRepo.findAll();
        model.addAttribute("customers", customers);
    }

    // Проверка правильности данных покупателя
    private boolean checkCustomerPersonalData(String first_name, String last_name, String middle_name, String sex,
                                              Model model) {
        if (null != first_name && !StringHelper.isLetters(first_name)) {
            model.addAttribute("message", "Введите корректное имя");
            return false;
        }
        if (null != last_name && !StringHelper.isLetters(last_name)) {
            model.addAttribute("message", "Введите корректную фамилию");
            return false;
        }
        if (null != middle_name && !StringHelper.isLetters(middle_name)) {
            model.addAttribute("message", "Введите корректное отчество");
            return false;
        }
        if (!sex.equals("male") && !sex.equals("female")) {
            model.addAttribute("message", "Введите корректный ♂SEX♂");
            return false;
        }
        return true;
    }

    // Проверка правильности данных адреса
    private boolean checkCustomerAddressData(String country, String region, String city, String street, String house,
                                             String flat, Model model) {
        if (null != country && !StringHelper.isLetters(country)) {
            model.addAttribute("message", "Введите корректную страну");
            return false;
        }
        if (null != region && !StringHelper.isLetters(region)) {
            model.addAttribute("message", "Введите корректный регион");
            return false;
        }
        if (null != city && !StringHelper.isLettersNumbersAndHyphen(city)) {
            model.addAttribute("message", "Введите корректный город");
            return false;
        }
        if (null != street && !StringHelper.isLettersNumbersAndHyphen(street)) {
            model.addAttribute("message", "Введите корректную улицу");
            return false;
        }
        if (null != house && !StringHelper.isLettersNumbersAndSlash(house)) {
            model.addAttribute("message", "Введите корректный дом");
            return false;
        }
        if (null != flat && !StringHelper.isNumbers(flat)) {
            model.addAttribute("message", "Введите корректную квартиру");
            return false;
        }
        return true;
    }

}

