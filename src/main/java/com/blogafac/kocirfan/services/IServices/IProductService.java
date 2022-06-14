package com.blogafac.kocirfan.services.IServices;

import com.blogafac.kocirfan.entity.Post;
import com.blogafac.kocirfan.entity.Product;
import com.blogafac.kocirfan.entity.dto.CategoryDTO;
import com.blogafac.kocirfan.entity.dto.PostDTO;
import com.blogafac.kocirfan.entity.dto.ProductDTO;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public interface IProductService {


    //varlıktan dto ya
    public ProductDTO EntityToDto(Product product);

    // Dto dan varlık
    public Product DtoToEntity(ProductDTO productDTO);

    public ProductDTO createProduct(ProductDTO productDTO);


    public List<ProductDTO> getAllProduct();

    //public Product getProductById(Long id);

    public ResponseEntity<ProductDTO> updateProduct(Long id, ProductDTO productDTO);

    public ResponseEntity<Map<String, Boolean>> deleteProduct(Long id);

   // public  List<ProductDTO> findByUserId(String user,Long userId);

    @NotNull Iterable<Product> getAllProducts();

    Product getProduct(Long id);

    Product save(Product product);

}
