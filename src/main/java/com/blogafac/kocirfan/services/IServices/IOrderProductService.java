package com.blogafac.kocirfan.services.IServices;

import com.blogafac.kocirfan.entity.Category;
import com.blogafac.kocirfan.entity.OrderProduct;
import com.blogafac.kocirfan.entity.dto.CategoryDTO;
import com.blogafac.kocirfan.entity.dto.OrderProductDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface IOrderProductService {

    //varlıktan dto ya
    public OrderProductDto EntityToDto(OrderProduct orderProduct);

    // Dto dan varlık
    public OrderProduct DtoToEntity(OrderProductDto orderProductDto);
    OrderProduct create(@NotNull(message = "The products for order cannot be null.") @Valid OrderProduct orderProduct);
}

