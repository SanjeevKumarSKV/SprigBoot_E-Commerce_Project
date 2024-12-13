package com.Project.Ecommerce.USER.SERVICE;


import com.Project.Ecommerce.USER.DAO.UserRepository;
import com.Project.Ecommerce.USER.MODEL.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUser(Long id){
        Optional<User> usr = userRepository.findById(id);
        return usr.orElse(null);

    }

    public User createUser(User user){
        return userRepository.save(user);

    }

    public void deleteUser(Long id){
        boolean UserExist = userRepository.existsById(id);

        if (UserExist) {
            userRepository.deleteById(id);
        }
    }
}