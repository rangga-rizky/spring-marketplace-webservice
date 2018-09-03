package com.rangga.tokokita.service;

import com.rangga.tokokita.model.Category;
import com.rangga.tokokita.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> index() {
        return categoryRepository.findAll();
    }

}
