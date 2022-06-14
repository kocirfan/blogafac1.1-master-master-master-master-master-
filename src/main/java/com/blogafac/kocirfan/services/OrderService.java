package com.blogafac.kocirfan.services;

import com.blogafac.kocirfan.entity.Order;
import com.blogafac.kocirfan.repository.OrderRepository;
import com.blogafac.kocirfan.services.IServices.IOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrderRepository orderRepository;
    UserService userService;
    ProductService productService;

    public OrderService(OrderRepository orderRepository, UserService userService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public Iterable<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public Order create(Order order) {
        order.setDateCreated(LocalDate.now());
        return this.orderRepository.save(order);
    }

    @Override
    public void update(Order order) {
        this.orderRepository.save(order);
    }
//
//    @Override
//    public OrderDTO EntityToDto(Order order) {
//      OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
//      return orderDTO;
//    }
//
//    @Override
//    public Order DtoToEntity(OrderDTO orderDTO) {
//        Order order = modelMapper.map(orderDTO, Order.class);
//        return order;
//    }
//
//
//
//    @Override
//    public OrderDTO creatOrder(@RequestBody  OrderDTO orderDTO) {
//        Order order = DtoToEntity(orderDTO);
//       User user = userService.getUserById(orderDTO.getUserId());
//
//
//       orderDTO.builder()
//               .id( orderDTO.getId())
//               .createDate(orderDTO.getCreateDate())
//               .address(orderDTO.getAddress())
//               .userId(user.getId())
//               .piece(orderDTO.getPiece())
//               .total(orderDTO.getTotal())
//               .build();
//        //orderRepository.save(order);
//        // return orderDTO;
//
//        //Order order = DtoToEntity(orderDTO);
//        // order.setUser(user);
//        //order.setProduct(product);
//        //order.setCreateDate(order.getCreateDate());
//        // order.setPiece(order.getPiece());
//        // order.setTotal(orderDTO.getPiece() * product.getPrice());
//
//
//        orderRepository.save(order);
//        return  orderDTO;
//    }
//
//    @Override
//    public List<OrderDTO> getAllOrder() {
//        List<OrderDTO> listDto = new ArrayList<>();
//        Iterable<Order> orders = orderRepository.findAll();
//        for (Order order : orders){
//            OrderDTO orderDTO = EntityToDto(order);
//            listDto.add(orderDTO);
//
//        }
//        return listDto;
//
//    }
//
//    @Override
//    public ResponseEntity<OrderDTO> getOrderById(Long id) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<OrderDTO> updateOrder(Long id, OrderDTO orderDTO) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<Map<String, Boolean>> deleteOrder(Long id) {
//        return null;
//    }
//
}
