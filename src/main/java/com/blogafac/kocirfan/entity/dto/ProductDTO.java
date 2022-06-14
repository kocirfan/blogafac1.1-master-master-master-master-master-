package com.blogafac.kocirfan.entity.dto;

import com.blogafac.kocirfan.entity.Category;
import com.blogafac.kocirfan.entity.ProductImage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.persistence.ManyToOne;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class ProductDTO {

    Long id;
    String name;
    String description;
    Long categoryId;

    @JsonIgnore
    Category category;

     String image;

    @JsonIgnore
    int parentId;
    //Long orderId;
    Double price;
}
