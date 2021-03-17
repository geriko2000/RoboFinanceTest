package com.RoboFinanceTest.repos;

import com.RoboFinanceTest.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    List<Customer> findAll();

    @Query("SELECT c FROM Customer c WHERE c.first_name = :first_name AND c.last_name = :last_name")
    List<Customer> findByFirstnameAndLastname(@Param("first_name") String first_name,
                                              @Param("last_name") String last_name);
}
