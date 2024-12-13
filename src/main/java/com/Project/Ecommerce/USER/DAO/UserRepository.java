package com.Project.Ecommerce.USER.DAO;

import com.Project.Ecommerce.USER.MODEL.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public  interface UserRepository extends JpaRepository<User, Long> {


}
