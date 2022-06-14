package com.blogafac.kocirfan.services.IServices;

import com.blogafac.kocirfan.entity.Category;
import com.blogafac.kocirfan.entity.Post;
import com.blogafac.kocirfan.entity.Product;
import com.blogafac.kocirfan.entity.dto.CategoryDTO;
import com.blogafac.kocirfan.entity.dto.ProductDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ICategoryService {


    //varlıktan dto ya
    public CategoryDTO EntityToDto(Category category);

    // Dto dan varlık
    public Category DtoToEntity(CategoryDTO categoryDTO);

    public CategoryDTO createCategory(CategoryDTO categoryDTO);


    public List<CategoryDTO> getAllCategory();

    public List<CategoryDTO> getCategoryByParentId(CategoryDTO categoryDTO);

    public ResponseEntity<CategoryDTO> getCategoryById(Long id);

    public ResponseEntity<CategoryDTO> updateCategory(Long id, CategoryDTO categoryDTO);

    public ResponseEntity<Map<String, Boolean>> deleteCategory(Long id);

    //public List<CategoryDTO> getBySelectParentId(int parentId );
}
