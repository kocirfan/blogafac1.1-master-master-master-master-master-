package com.blogafac.kocirfan.services.IServices;

import com.blogafac.kocirfan.entity.Comment;
import com.blogafac.kocirfan.entity.Order;
import com.blogafac.kocirfan.entity.dto.CommentDTO;
// import com.blogafac.kocirfan.entity.dto.OrderDTO;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public interface IOrderService {

//    public OrderDTO EntityToDto(Order order);

    // Dto dan varlık
//    public Order DtoToEntity(OrderDTO orderDTO);
//
//    public List<OrderDTO> getAllOrder();
//    public OrderDTO creatOrder(OrderDTO orderDTO);
//    public ResponseEntity<OrderDTO> getOrderById(Long id);
//    public ResponseEntity<OrderDTO> updateOrder(Long id, OrderDTO orderDTO);
//    public ResponseEntity<Map<String, Boolean>> deleteOrder(Long id);

    @NotNull Iterable<Order> getAllOrders();

    Order create(@NotNull(message = "The order cannot be null.") @Valid Order order);

    void update(@NotNull(message = "The order cannot be null.") @Valid Order order);
}
