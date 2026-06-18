package com.adolfo.finance_tracker.service;

import com.adolfo.finance_tracker.model.Category;
import com.adolfo.finance_tracker.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    //Dependencias (Atributos)
    private final CategoryRepository categoryRepository;

    //Métodos
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(Long id){
       return categoryRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public void deleteById(Long id){
        categoryRepository.deleteById(id);
    }

    public Category update(Long id, Category category){
        Category aux = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        aux.setDescription(category.getDescription());
        aux.setName(category.getName());

        return categoryRepository.save(aux);
    }
}
