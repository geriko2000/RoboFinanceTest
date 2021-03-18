package com.RoboFinanceTest.models;


import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "registred_address_id", referencedColumnName = "id")
    private Address registredAddressId;

    @OneToOne
    @JoinColumn(name = "actual_address_id", referencedColumnName = "id")
    private Address actualAddressId;

    public Customer() {
    }

    public Customer(Address registred_address_id, Address actual_address_id, String first_name, String last_name, String middle_name, String sex) {
        this.registredAddressId = registred_address_id;
        this.actualAddressId = actual_address_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.sex = sex;
    }

    private String first_name;
    private String last_name;
    private String middle_name;
    private String sex;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Address getRegistered_address_id() {
        return registredAddressId;
    }

    public void setRegistered_address_id(Address registered_address_id) {
        this.registredAddressId = registered_address_id;
    }

    public Address getActualAddressId() {
        return actualAddressId;
    }

    public void setActualAddressId(Address actual_address_id) {
        this.actualAddressId = actual_address_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
