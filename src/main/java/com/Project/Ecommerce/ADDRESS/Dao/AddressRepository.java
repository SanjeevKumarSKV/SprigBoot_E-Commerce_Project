package com.Project.Ecommerce.ADDRESS.Dao;

import com.Project.Ecommerce.ADDRESS.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository  extends JpaRepository<Address, Long> {

}
