package com.shreyansh.food_backend_springboot.repository;

import com.shreyansh.food_backend_springboot.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
