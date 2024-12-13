package com.Project.Ecommerce.CATEGORIES.Dao;

import com.Project.Ecommerce.CATEGORIES.Model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {

}
