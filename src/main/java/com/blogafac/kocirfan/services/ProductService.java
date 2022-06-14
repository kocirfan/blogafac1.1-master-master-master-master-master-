package com.blogafac.kocirfan.services;

import com.blogafac.kocirfan.entity.*;
import com.blogafac.kocirfan.entity.dto.CategoryDTO;
import com.blogafac.kocirfan.entity.dto.PostDTO;
import com.blogafac.kocirfan.entity.dto.ProductDTO;
import com.blogafac.kocirfan.exception.ResourceNotFoundException;
import com.blogafac.kocirfan.repository.ProductRepository;
import com.blogafac.kocirfan.services.IServices.IProductService;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private ProductRepository productRepository;
    private ProductImageService productImageService;
    private CategoryService categoryService;

    public ProductService( CategoryService categoryService, ProductImageService productImageService) {

        this.categoryService = categoryService;
        this.productImageService = productImageService;
    }



    @Override
    public ProductDTO EntityToDto(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;
    }

    @Override
    public Product DtoToEntity(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        return product;
    }

    @Override
    public ProductDTO createProduct(@RequestBody  ProductDTO productDTO) {
        ResponseEntity<CategoryDTO> category = categoryService.getCategoryById(productDTO.getCategoryId());
        String  productImages = productImageService.getProductMainImage(productDTO.getId());

        Product product = DtoToEntity(productDTO);
       ProductDTO.builder()

               .parentId(productDTO.getParentId())
               .categoryId(category.getBody().getId())
               .id(productDTO.getId())
               .description(productDTO.getDescription())
               .image(productImages)
               .name(productDTO.getName())
               .category(new Category())
               .price(product.getPrice())
               .build();

//        product.setId(product.getId());
//        product.setCategory(product.getCategory());
//        product.setName(productDTO.getName());
//        product.setDescription(productDTO.getDescription());
//        product.setImage(productImages);
        productRepository.save(product);
        return productDTO;
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        List<ProductDTO> listDTO = new ArrayList<>();
        Iterable<Product> postsList = productRepository.findAll();
        for (Product product : postsList) {
            ProductDTO productDTO = EntityToDto(product);
            listDTO.add(productDTO);
        }
        return listDTO;
    }

//    @Override
//    public Product getProductById(Long id) {
//        Product product = productRepository.findById(id).orElseThrow(NullPointerException::new);
//        ProductDTO productDTO = EntityToDto(product);
//        return product;
//    }

    @Override
    public ResponseEntity<ProductDTO> updateProduct(Long id, ProductDTO productDetails) {
        Product product = DtoToEntity(productDetails);
        Product product1 = productRepository.findById(id).orElseThrow(NullPointerException::new);
        product1.setName(product.getName());
        product1.setDescription(product.getDescription());
        product1.setId(product.getId());
        product1.setCategory(product.getCategory());

        Product updateProduct = productRepository.save(product1);
        ProductDTO productDTO  = EntityToDto(updateProduct);
        return ResponseEntity.ok(productDTO);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(NullPointerException::new);
        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }



    //////////////////////////////////////////////
    @Override
    public Product getProduct(Long id) {
        return productRepository
                .findById(id).get();
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
