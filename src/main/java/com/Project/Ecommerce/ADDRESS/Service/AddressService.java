package com.Project.Ecommerce.ADDRESS.Service;


import com.Project.Ecommerce.ADDRESS.Dao.AddressRepository;
import com.Project.Ecommerce.ADDRESS.Model.Address;
import com.Project.Ecommerce.USER.DAO.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    public List<Address> getAllAddress(){
        return addressRepository.findAll();
    }

    public Address getAddress(Long id){
        Optional<Address> add = addressRepository.findById(id);
        return add.orElse(null);
    }

    public Address createAddress(Address address){
        return addressRepository.save(address);
//        User user = userRepository.findById(userId).orElse(new User());
//        address.setUser(user);
//        return addressRepository.save(address);
    }

    public Address updateAddress(Address address, Long id){
        boolean AddressUpdate = addressRepository.existsById(id);

        if (AddressUpdate){
            return addressRepository.save(address);
        }
        return null;
    }
    public void deleteAddress(Long id){
        boolean AddressExist = addressRepository.existsById(id);

        if(AddressExist){
            addressRepository.deleteById(id);
        }
    }
}