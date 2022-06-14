package com.blogafac.kocirfan.repository;

import com.blogafac.kocirfan.entity.OrderProduct;
import com.blogafac.kocirfan.entity.OrderProductPK;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository  extends CrudRepository<OrderProduct, OrderProductPK> {
}
