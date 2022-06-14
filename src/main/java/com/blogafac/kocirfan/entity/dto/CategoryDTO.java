package com.blogafac.kocirfan.entity.dto;

import com.blogafac.kocirfan.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class CategoryDTO {
    Long id;
    String name;
    int parentId;



}
