package com.Project.Ecommerce.CATEGORIES.Web;

import com.Project.Ecommerce.CATEGORIES.Model.Categories;
import com.Project.Ecommerce.CATEGORIES.Service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoriesController {

    @Autowired
    CategoriesService categoriesService;


    @GetMapping
    public List<Categories> getAllCategories(){
        return categoriesService.getAllCategories();
    }

    @GetMapping("{id}")
    public Categories getCategories(@PathVariable Long id){
        return categoriesService.getCategories(id);
    }

    @PostMapping
    public Categories createCategories(@RequestBody Categories categories){
        return categoriesService.createCategories(categories);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategories(@PathVariable Long id){
        categoriesService.deleteCategories(id);
        return ResponseEntity.ok("Delete Success");
    }
}