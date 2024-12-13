package com.Project.Ecommerce.CATEGORIES.Web;

import com.Project.Ecommerce.CATEGORIES.Model.Product;
import com.Project.Ecommerce.CATEGORIES.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable Long id){
        return productService.getProduct(id);
    }

    @PostMapping("/{id}")
    public Product addproduct(@RequestBody Product product, @PathVariable("id") Long categoryId) throws Exception {
        return productService.addProduct(product, categoryId);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateProduct(@RequestBody Product updateProduct, @PathVariable Long id) throws Exception {
         productService.updateProduct(updateProduct,id);
        return ResponseEntity.ok("Update Success");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
         productService.deleteProduct(id);
        return ResponseEntity.ok("Delete Success");
    }
}