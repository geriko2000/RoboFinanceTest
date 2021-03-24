package com.RoboFinanceTest.repos;

import com.RoboFinanceTest.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM Customer ORDER BY id", nativeQuery = true)
    List<Customer> findAll();

    @Query("SELECT c FROM Customer c WHERE c.first_name = :first_name AND c.last_name = :last_name")
    List<Customer> findByFirstnameAndLastname(@Param("first_name") String firstName,
                                              @Param("last_name") String lastName);

    Customer findById(long id);
}
