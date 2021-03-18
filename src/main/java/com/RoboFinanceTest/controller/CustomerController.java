package com.RoboFinanceTest.controller;

import com.RoboFinanceTest.models.Address;
import com.RoboFinanceTest.models.Customer;
import com.RoboFinanceTest.repos.AddressRepo;
import com.RoboFinanceTest.repos.CustomerRepo;
import com.RoboFinanceTest.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            Model model) {

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
    public String changeAddress(Model model) {
        List<Customer> customers = customerRepo.findAll();
        model.addAttribute("customers", customers);
        return "changeaddress";
    }

    @GetMapping("/changeaddress/{id}")
    public String changeAddressById(Model model, @PathVariable String id) {
        try {
            long customerId = Long.parseLong(id);
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

    //@PutMapping("/changeaddress/{id}")
    @RequestMapping(value={"/changeaddress/{id}"},
            method={RequestMethod.POST,RequestMethod.PUT})
    public String changeAddressMapping(@PathVariable String id,
                                       @RequestParam(required = false) String country,
                                       @RequestParam(required = false) String region,
                                       @RequestParam(required = false) String city,
                                       @RequestParam(required = false) String street,
                                       @RequestParam(required = false) String house,
                                       @RequestParam(required = false) String flat,
                                       Model model) {
        if (!checkCustomerAddressData(country, region, city, street, house, flat, model)) {
            //System.out.println("kek");
            return "redirect:/changeaddress";
        }
        try {
            long customerId = Long.parseLong(id);
            Customer customer = customerRepo.findById(customerId);

            System.out.println(customer);

            Address address = addressRepo.findById(customer.getActualAddressId().getId());

            if (country != null) address.setCountry(country);
            if (region != null) address.setRegion(region);
            if (city != null) address.setCity(city);
            if (street != null) address.setStreet(street);
            if (house != null) address.setHouse(house);
            if (flat != null) address.setFlat(flat);
            address.setModified(new Timestamp(System.currentTimeMillis()));
            addressRepo.save(address);
            model.addAttribute("message", "Адрес успешно изменен!");

            return "redirect:/changeaddress";
        } catch (NumberFormatException e) {
            return "redirect:/changeaddress";
        }

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

    /**
     * Добавить покупателя в таблицу
     *
     * @param first_name имя
     * @param last_name фамилия
     * @param middle_name отчество
     * @param sex пол
     * @param address адрес
     * @return созданный объект покупателя
     **/
    private Customer addCustomer(String first_name, String last_name, String middle_name, String sex, Address address) {
        Customer customer = new Customer(address, address, first_name, last_name, middle_name, sex);
        customerRepo.save(customer);
        return customer;

    }

    /**
     * Показать всех покупателей из базы
     **/
    private void findAllCustomers(Model model) {
        List<Customer> customers = customerRepo.findAll();
        model.addAttribute("customers", customers);
    }

    /**
     * Проверка правильности данных покупателя
     *
     * @param first_name имя
     * @param last_name фамилия
     * @param middle_name отчество
     * @param sex пол
     * @return созданный объект покупателя
     **/
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

    /**
     * Проверка правильности данных адреса
     *
     * @param country страна
     * @param region  регион
     * @param city    город
     * @param street  улица
     * @param house   дом
     * @param flat    квартира
     * @return созданный объект адреса
     **/
    private boolean checkCustomerAddressData(String country, String region, String city, String street, String house,
                                             String flat, Model model) {
        if (null != country && !StringHelper.isLetters(country)) {
            model.addAttribute("message", "Введите корректную страну");
            System.out.println("kek1");
            return false;
        }
        if (null != region && !StringHelper.isLetters(region)) {
            model.addAttribute("message", "Введите корректный регион");
            System.out.println("kek2");
            return false;
        }
        if (null != city && !StringHelper.isLettersNumbersAndHyphen(city)) {
            model.addAttribute("message", "Введите корректный город");
            System.out.println("kek3");
            return false;
        }
        if (null != street && !StringHelper.isLettersNumbersAndHyphen(street)) {
            model.addAttribute("message", "Введите корректную улицу");
            System.out.println("kek4");
            return false;
        }
        if (null != house && !StringHelper.isLettersNumbersAndSlash(house)) {
            model.addAttribute("message", "Введите корректный дом");
            System.out.println(house + "asdf");
            return false;
        }
        if (null != flat && !StringHelper.isNumbers(flat)) {
            System.out.println("kek6");
            model.addAttribute("message", "Введите корректную квартиру");
            return false;
        }
        return true;
    }

}

