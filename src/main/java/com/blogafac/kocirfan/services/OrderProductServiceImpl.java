package com.blogafac.kocirfan.services;

import com.blogafac.kocirfan.entity.OrderProduct;
import com.blogafac.kocirfan.entity.Product;
import com.blogafac.kocirfan.entity.dto.OrderProductDto;
import com.blogafac.kocirfan.entity.dto.ProductDTO;
import com.blogafac.kocirfan.repository.OrderProductRepository;
import com.blogafac.kocirfan.services.IServices.IOrderProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderProductServiceImpl implements IOrderProductService {

    @Autowired
    ModelMapper modelMapper;
    private OrderProductRepository orderProductRepository;

    public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public OrderProductDto EntityToDto(OrderProduct orderProduct) {
        OrderProductDto orderproductDTO = modelMapper.map(orderProduct, OrderProductDto.class);
        return orderproductDTO;
    }

    @Override
    public OrderProduct DtoToEntity(OrderProductDto orderProductDto) {
        OrderProduct orderproduct = modelMapper.map(orderProductDto, OrderProduct.class);
        return orderproduct;
    }

    @Override
    public OrderProduct create(OrderProduct orderProduct) {
        return this.orderProductRepository.save(orderProduct);
    }
}
