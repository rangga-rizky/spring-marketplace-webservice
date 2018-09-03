package com.rangga.tokokita.controller;

import com.rangga.tokokita.model.Category;
import com.rangga.tokokita.service.CategoryService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@Api(tags = "category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    @ApiOperation(value = "${CategoryController.index}")
    public List<Category> index() {
        return categoryService.index();
    }

}
