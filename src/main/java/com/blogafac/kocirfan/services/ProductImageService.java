package com.blogafac.kocirfan.services;

import com.blogafac.kocirfan.entity.ProductImage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageService {

    public String getProductMainImage(Long id){
        // TODO
        return "https://productimages.hepsiburada.net/s/32/500/10352568139826.jpg";
    }
}
