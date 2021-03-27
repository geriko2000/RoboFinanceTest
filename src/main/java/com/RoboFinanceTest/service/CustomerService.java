package com.RoboFinanceTest.service;

import com.RoboFinanceTest.models.Address;
import com.RoboFinanceTest.models.Customer;
import com.RoboFinanceTest.repos.AddressRepo;
import com.RoboFinanceTest.repos.CustomerRepo;
import com.RoboFinanceTest.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.sql.Timestamp;
import java.util.List;

@Service
@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private AddressRepo addressRepo;

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
    public Address addAddress(String country, String region, String city, String street, String house, String flat) {
        Timestamp created = new Timestamp(System.currentTimeMillis());
        Address address = new Address(country, region, city, street, house, flat, created);
        addressRepo.save(address);

        return address;
    }

    /**
     * Изменить фактический адрес
     *
     * @param country    страна
     * @param region     регион
     * @param city       город
     * @param street     улица
     * @param house      дом
     * @param flat       квартира
     * @param customerId id пользователя
     **/
    public void changeActualAddress(String country, String region, String city, String street, String house,
                                    String flat, long customerId) {
        Customer customer = customerRepo.findById(customerId);
        Timestamp created = new Timestamp(System.currentTimeMillis());

        if (customer.getActualAddressId().getId() == customer.getRegisteredAddressId().getId()) {

            Address address = new Address(country, region, city, street, house, flat, created);
            address.setModified(new Timestamp(System.currentTimeMillis()));
            addressRepo.save(address);
            customer.setActualAddressId(address);
            customerRepo.save(customer);
        } else {
            Address address = customer.getActualAddressId();
            if (country != null) address.setCountry(country);
            if (region != null) address.setRegion(region);
            if (city != null) address.setCity(city);
            if (street != null) address.setStreet(street);
            if (house != null) address.setHouse(house);
            if (flat != null) address.setFlat(flat);
            address.setModified(new Timestamp(System.currentTimeMillis()));
            addressRepo.save(address);
        }
    }

    /**
     * Добавить покупателя в таблицу
     *
     * @param first_name  имя
     * @param last_name   фамилия
     * @param middle_name отчество
     * @param sex         пол
     * @param address     адрес
     * @return созданный объект покупателя
     **/
    public Customer addCustomer(String first_name, String last_name, String middle_name, String sex, Address address) {
        Customer customer = new Customer(address, address, first_name, last_name, middle_name, sex);
        customerRepo.save(customer);
        return customer;

    }

    /**
     * Показать всех покупателей из базы
     **/
    public void findAllCustomers(Model model) {
        List<Customer> customers = customerRepo.findAll();
        model.addAttribute("customers", customers);
    }

    /**
     * Проверка правильности данных покупателя
     *
     * @param first_name  имя
     * @param last_name   фамилия
     * @param middle_name отчество
     * @param sex         пол
     * @return true если прошло проверку
     **/
    public boolean checkCustomerPersonalData(String first_name, String last_name, String middle_name, String sex, Model model) {
        if (!first_name.isEmpty() && !StringHelper.isLetters(first_name)) {
            model.addAttribute("message", "Введите корректное имя!");
            return false;
        }
        if (!last_name.isEmpty() && !StringHelper.isLetters(last_name)) {
            model.addAttribute("message", "Введите корректную фамилию!");
            return false;
        }
        if (!middle_name.isEmpty() && !StringHelper.isLetters(middle_name)) {
            model.addAttribute("message", "Введите корректное отчество!");
            return false;
        }
        if (!sex.equals("male") && !sex.equals("female")) {
            model.addAttribute("message", "Введите корректный пол!");
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
     * @return true если прошло проверку
     **/
    public boolean checkCustomerAddressData(String country, String region, String city, String street, String house,
                                            String flat, Model model) {
        if (!country.isEmpty() && !StringHelper.isLetters(country)) {
            model.addAttribute("message", "Введите корректную страну!");
            return false;
        }
        if (!region.isEmpty() && !StringHelper.isLetters(region)) {
            model.addAttribute("message", "Введите корректный регион!");
            return false;
        }
        if (!city.isEmpty() && !StringHelper.isLettersNumbersAndHyphen(city)) {
            model.addAttribute("message", "Введите корректный город!");
            return false;
        }
        if (!street.isEmpty() && !StringHelper.isLettersNumbersAndHyphen(street)) {
            model.addAttribute("message", "Введите корректную улицу!");
            return false;
        }
        if (!house.isEmpty() && !StringHelper.isLettersNumbersAndSlash(house)) {
            model.addAttribute("message", "Введите корректный номер дома!");
            return false;
        }
        if (!flat.isEmpty() && !StringHelper.isNumbers(flat)) {
            model.addAttribute("message", "Введите корректный этаж!");
            return false;
        }
        return true;
    }

}

