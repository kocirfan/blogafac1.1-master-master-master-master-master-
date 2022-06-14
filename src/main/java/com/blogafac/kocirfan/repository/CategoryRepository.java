package com.blogafac.kocirfan.repository;

import com.blogafac.kocirfan.entity.Category;

import com.blogafac.kocirfan.entity.Post;
import com.blogafac.kocirfan.entity.dto.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByParentId(int parentId );

   // List<Category> findBySelectparentId(int  parentId);


}
