package com.example.ecommerceweb.service;

import com.example.ecommerceweb.model.Category;
import com.example.ecommerceweb.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategory(){
        List<Category> category = categoryRepository.findAll();
        return category;
    }

    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public void deleteCategoryById(Long id){
        categoryRepository.deleteById(id);
    }

    public Category getCategoryById(Long id) throws UserNotFoundException {
        Optional <Category> result = categoryRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("Could not find any user with id" + id);

    }


}
