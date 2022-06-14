package com.blogafac.kocirfan.services;

import com.blogafac.kocirfan.entity.Category;
import com.blogafac.kocirfan.entity.dto.CategoryDTO;
import com.blogafac.kocirfan.repository.CategoryRepository;
import com.blogafac.kocirfan.services.IServices.ICategoryService;
import org.hibernate.hql.spi.id.IdTableHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO EntityToDto(Category category) {
        CategoryDTO categoryDTO= modelMapper.map(category, CategoryDTO.class);
        return categoryDTO;
    }

    @Override
    public Category DtoToEntity(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        return category;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = DtoToEntity(categoryDTO);
        categoryRepository.save(category);
        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<CategoryDTO> listDto = new ArrayList<>();
        Iterable<Category> categoryList = categoryRepository.findAll();
        for (Category category : categoryList) {
            CategoryDTO categoryDTO = EntityToDto(category);
            listDto.add(categoryDTO);
        }
        return listDto;
    }

    //sıfır olan
    @Override
    public List<CategoryDTO> getCategoryByParentId(CategoryDTO categoryDTO) {

        List<CategoryDTO> listparent = new ArrayList<>();
        Iterable<Category> categories;
        if(categoryDTO.getParentId() == 0){
            categories = categoryRepository.findByParentId(categoryDTO.getParentId());
        }else{
            categories = categoryRepository.findAll((Pageable) categoryDTO);

        }
        for(Category category : categories){
            CategoryDTO categoryDTO1 = EntityToDto(category);
            listparent.add(categoryDTO1);

        }
        return  listparent;
    }

    //child olan
//    @Override
//    public  List<CategoryDTO> getBySelectParentId(int parentId  ){
//        List<CategoryDTO> listchild = new ArrayList<>();
//        Iterable<Category> categoriess = categoryRepository.findBySelectParentId(parentId);
//        for(Category category : categoriess){
//            CategoryDTO categoryDTOS = EntityToDto(category);
//            listchild.add(categoryDTOS);
//        }
//        return listchild;
//
//    }

    @Override
    public ResponseEntity<CategoryDTO> getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        CategoryDTO categoryDTO = EntityToDto(category);
        return ResponseEntity.ok(categoryDTO);
    }

    @Override
    public ResponseEntity<CategoryDTO> updateCategory(Long id, CategoryDTO categoryDetails) {
        Category category = DtoToEntity(categoryDetails);
        Category category1 = categoryRepository.findById(id).orElseThrow(NullPointerException::new);
        category1.setId(category.getId());
        category1.setName(category.getName());
        category1.setParentId(category.getParentId());


        Category updateCategory = categoryRepository.save(category1);
        CategoryDTO categoryDTO = EntityToDto(updateCategory);
        return ResponseEntity.ok(categoryDTO);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(NullPointerException::new);
        categoryRepository.delete(category);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }




//    public List<CategoryDTO> getBySelectParentId(int parentId) {
//        Iterable<Category> categoriess;
//        List<CategoryDTO> listchild = new ArrayList<>();
//        if (parentId !=0 ) {
//            categoriess = categoryRepository.findBySelectparentId(parentId);
//            for (Category category : categoriess) {
//                CategoryDTO categoryDTOS = EntityToDto(category);
//                listchild.add(categoryDTOS);
//            }
//
//        }
//        return listchild;
//    }
}
