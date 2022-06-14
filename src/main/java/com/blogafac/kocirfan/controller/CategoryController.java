package com.blogafac.kocirfan.controller;

import com.blogafac.kocirfan.entity.Category;
import com.blogafac.kocirfan.entity.dto.CategoryDTO;
import com.blogafac.kocirfan.services.CategoryService;
import com.blogafac.kocirfan.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth/v1/category")
@CrossOrigin("http://127.0.0.1:5500/")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @PostMapping("/categories")
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.createCategory(categoryDTO);

        return categoryDTO;
    }

    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategory() {
        List<CategoryDTO> categoryDTO = (List<CategoryDTO>) categoryService.getAllCategory();
        return categoryDTO;
    }

    @GetMapping("/categories/parent")
    public List<CategoryDTO> getCategoryByParentId( CategoryDTO categoryDTO){
        List<CategoryDTO> categoryDTO1 = (List<CategoryDTO>) categoryService.getCategoryByParentId(categoryDTO);
        return categoryDTO1;
    }




    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id){
        List<CategoryDTO> categoryDTO = (List<CategoryDTO>) categoryService.getAllCategory();
        return ResponseEntity.ok(categoryDTO.get(0));
    }


    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDetails){
        categoryService.updateCategory(id, categoryDetails);
        return ResponseEntity.ok(categoryDetails);
    }



    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("silindi", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }



    //    @GetMapping("/categories/parents")
//    public List<CategoryDTO> getBySelectParentId(int  parentId  ){
//        List<CategoryDTO> categoryDTOS =  (List<CategoryDTO>) categoryService.getBySelectParentId(parentId);
//        return categoryDTOS;
//    }
}
