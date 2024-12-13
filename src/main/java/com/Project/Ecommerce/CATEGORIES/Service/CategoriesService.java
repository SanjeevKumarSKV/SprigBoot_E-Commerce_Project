package com.Project.Ecommerce.CATEGORIES.Service;


import com.Project.Ecommerce.CATEGORIES.Dao.CategoriesRepository;
import com.Project.Ecommerce.CATEGORIES.Model.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {

    @Autowired
    CategoriesRepository categoriesRepository;

    public List<Categories> getAllCategories(){
        return categoriesRepository.findAll();
    }
    
    public Categories getCategories(Long id){
       Optional<Categories> cate = categoriesRepository.findById(id);
       return cate.orElse(null);
    }

    public Categories createCategories(Categories categories){
        return categoriesRepository.save(categories);
    }

    public void deleteCategories(Long id){
        boolean CategoriesExist = categoriesRepository.existsById(id);

        if (CategoriesExist){
            categoriesRepository.deleteById(id);
        }
    }

}
