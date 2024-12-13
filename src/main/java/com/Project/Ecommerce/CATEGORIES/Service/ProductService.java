package com.Project.Ecommerce.CATEGORIES.Service;


import com.Project.Ecommerce.CATEGORIES.Dao.CategoriesRepository;
import com.Project.Ecommerce.CATEGORIES.Dao.ProductRepository;
import com.Project.Ecommerce.CATEGORIES.Model.Categories;
import com.Project.Ecommerce.CATEGORIES.Model.Product;
import com.Project.Ecommerce.USER.DAO.UserRepository;
import com.Project.Ecommerce.USER.MODEL.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    UserRepository userRepository;

    public Product addProduct(Product product, Long categoryId)throws Exception {
        Categories categories = categoriesRepository.findById(categoryId).orElse(new Categories());
        product.setCategories(categories);
        User user = userRepository.findById(categoryId).orElse(new User());
        product.setUser(user);

        return productRepository.save(product);
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Product getProduct(Long id){
        Optional<Product> pro = productRepository.findById(id);
        return pro.orElse(null);
    }

    public void updateProduct (Product updateProduct, Long id) throws Exception {
        Optional<Product> maybeProduct = productRepository.findById(id);

        if (maybeProduct.isPresent()){
            Product oldProduct = maybeProduct.get();
            oldProduct.setProductName(updateProduct.getProductName());
            oldProduct.setProductDescription(updateProduct.getProductDescription());
            oldProduct.setPrice(updateProduct.getPrice());
            oldProduct.setGstPercentage(updateProduct.getGstPercentage());

            Categories categories = categoriesRepository.findById(updateProduct.getTempCategoryId()).orElseThrow(() -> new Exception("Product Not Found"));
            oldProduct.setCategories(categories);

             productRepository.save(oldProduct);
        }
        else {
            throw new Exception("Product Not Found");
        }
    }
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
