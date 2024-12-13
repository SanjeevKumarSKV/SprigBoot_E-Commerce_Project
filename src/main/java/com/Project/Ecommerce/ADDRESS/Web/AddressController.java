package com.Project.Ecommerce.ADDRESS.Web;


import com.Project.Ecommerce.ADDRESS.Model.Address;
import com.Project.Ecommerce.ADDRESS.Service.AddressService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping
    public List<Address> getAllAddress(){
        return addressService.getAllAddress();
    }

    @GetMapping("{id}")
    public Address getAddress(@PathVariable Long id){
        return addressService.getAddress(id);
    }

    @PostMapping
    public Address createAddress(@RequestBody Address address){
        return addressService.createAddress(address);
    }

    @PutMapping("{id}")
    public Address updateAddress(@RequestBody Address address, @PathVariable Long id){
        return addressService.updateAddress(address,id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id){
        addressService.deleteAddress(id);
        return ResponseEntity.ok("Delete Success");
    }
}