package com.blogafac.kocirfan.controller;

import com.blogafac.kocirfan.entity.Order;
import com.blogafac.kocirfan.entity.OrderProduct;
import com.blogafac.kocirfan.entity.OrderStatus;
import com.blogafac.kocirfan.entity.dto.CategoryDTO;

import com.blogafac.kocirfan.entity.dto.OrderProductDto;
import com.blogafac.kocirfan.exception.ResourceNotFoundException;
import com.blogafac.kocirfan.services.IServices.IOrderProductService;

import com.blogafac.kocirfan.services.IServices.IOrderService;
import com.blogafac.kocirfan.services.IServices.IProductService;
import com.blogafac.kocirfan.services.OrderService;
import com.blogafac.kocirfan.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.*;

@RestController
@RequestMapping("/auth/v1/order")
@CrossOrigin("http://127.0.0.1:5500/")
public class OrderController {

    IProductService productService;
    IOrderService orderService;
    IOrderProductService orderProductService;

    public OrderController(ProductService productService, OrderService orderService, IOrderProductService orderProductService) {
        this.productService = productService;
        this.orderService = orderService;
        this.orderProductService = orderProductService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public  Iterable<Order> list() {
        return this.orderService.getAllOrders();
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderForm form) {
        List<OrderProductDto> formDtos = form.getProductOrders();
        validateProductsExistence(formDtos);
        Order order = new Order();
        order.setStatus(OrderStatus.PAID.name());
        order = this.orderService.create(order);

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductDto dto : formDtos) {
            orderProducts.add(orderProductService.create(new OrderProduct(order, productService.getProduct(dto
                    .getProduct()
                    .getId()), dto.getQuantity())));
        }

        order.setOrderProducts(orderProducts);

        this.orderService.update(order);

        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/orders/{id}")
                .buildAndExpand(order.getId())
                .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }

    private void validateProductsExistence(List<OrderProductDto> orderProducts) {
        List<OrderProductDto> list = orderProducts
                .stream()
                .filter(op -> Objects.isNull(productService.getProduct(op
                        .getProduct()
                        .getId())))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            new ResourceNotFoundException("Product not found");
        }
    }

    public static class OrderForm {

        private List<OrderProductDto> productOrders;

        public List<OrderProductDto> getProductOrders() {
            return productOrders;
        }

        public void setProductOrders(List<OrderProductDto> productOrders) {
            this.productOrders = productOrders;
        }
    }
}

//
//    @PostMapping("/orders")
//    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO){
//        orderService.creatOrder(orderDTO);
//        return orderDTO;
//    }
//
//    @GetMapping("/orders")
//    public List<OrderDTO> getAllOrder() {
//        List<OrderDTO> orderDTO = (List<OrderDTO>) orderService.getAllOrder();
//        return orderDTO;
//    }
//
//
//    @GetMapping("/orders/{id}")
//    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id){
//        List<OrderDTO> orderDTO = (List<OrderDTO>) orderService.getOrderById(id);
//        return ResponseEntity.ok(orderDTO.get(0));
//    }
//
//
//    @PutMapping("/orders/{id}")
//    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDetails){
//        orderService.updateOrder(id, orderDetails);
//        return ResponseEntity.ok(orderDetails);
//    }
//
//
//
//    @DeleteMapping("/orders/{id}")
//    public ResponseEntity<Map<String, Boolean>> deleteOrder(@PathVariable Long id){
//        orderService.deleteOrder(id);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("silindi", Boolean.TRUE);
//        return ResponseEntity.ok(response);
//    }

//}
//    @GetMapping("/orders)
//    public List<CategoryDTO> getCategoryByParentId( CategoryDTO categoryDTO){
//        List<CategoryDTO> categoryDTO1 = (List<CategoryDTO>) categoryService.getCategoryByParentId(categoryDTO);
//        return categoryDTO1;
//    }
