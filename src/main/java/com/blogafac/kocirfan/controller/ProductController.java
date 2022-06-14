package com.blogafac.kocirfan.controller;

import com.blogafac.kocirfan.entity.dto.CategoryDTO;
import com.blogafac.kocirfan.entity.dto.PostDTO;
import com.blogafac.kocirfan.entity.dto.ProductDTO;
import com.blogafac.kocirfan.services.CategoryService;
import com.blogafac.kocirfan.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth/v1/product")
@CrossOrigin("http://127.0.0.1:5500/")
public class ProductController {

    @Autowired
    ProductService productService;


    //dto
    @PostMapping("/products")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO){
        
        productService.createProduct(productDTO);
        return productDTO;
    }

    //dto
    @GetMapping("/products")
    public List<ProductDTO> getAllProduct() {
        List<ProductDTO> productDTO = (List<ProductDTO>) productService.getAllProduct();
        return productDTO;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id){
        List<ProductDTO> productDTO = (List<ProductDTO>) productService.getAllProduct();
        return ResponseEntity.ok(productDTO.get(0));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDetails){
        productService.updateProduct(id, productDetails);
        return ResponseEntity.ok(productDetails);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("silindi", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
