package com.RoboFinanceTest.repos;

import com.RoboFinanceTest.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {

}
