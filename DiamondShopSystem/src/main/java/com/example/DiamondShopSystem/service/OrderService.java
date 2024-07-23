package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.dto.OrderDTO;
import com.example.DiamondShopSystem.model.Order;
import com.example.DiamondShopSystem.model.OrderChatMessage;
import com.example.DiamondShopSystem.model.OrderStatus;
import com.example.DiamondShopSystem.model.PaymentRequest;
import com.example.DiamondShopSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private OrderChatMessageRepository orderChatMessageRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private PaymentRequestRepository paymentRequestRepository;
    @Autowired
    private StaffRepository staffRepository;

    public List<Order> findAllOrders() {
            return orderRepository.findAll();
    }

    public Order findOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Order saveOrderOnCheckOut(Order order){
        //Not completed
        return new Order();
    }

    public OrderDTO getCart(String token) {
//        OrderDTO dto = new OrderDTO();
        String username = jwtUtils.extractUsername(token);
        Optional<Order> activeOrder = orderRepository.findActiveOrderByUsername(username);
        return new OrderDTO(orderDetailRepository.findByOrderId(activeOrder.get().getOrderId()),activeOrder.get().getTotalPrice());
        // Return an empty list if there is no active order
    }

    public void setSuccessStatus(Order order) {
        OrderStatus successStatus = orderStatusRepository.findById(4L).get();
        order.setOrderStatus(successStatus);
        order.setOrderDate(new Date());
        orderRepository.save(order);
    }

    public void createNewCart(Order finishOrder) {
        Order order = new Order();
        order.setUsername(finishOrder.getUsername());
        order.setOrderStatus(orderStatusRepository.findById(1L).get());
        orderRepository.save(order);
    }

    public List<Order> getOrders(String token) {
        String username = jwtUtils.extractUsername(token);
        return orderRepository.findFinshiedOrderByUsername(username);
    }



    public void saveChatMessage(OrderChatMessage message) {
        // Here, you might add any business logic before saving the message
        orderChatMessageRepository.save(message);
    }

    public String checkOut(String token) {
        String username = jwtUtils.extractUsername(token);
        Optional<Order> activeOrder = orderRepository.findActiveOrderByUsername(username);
        Order order = activeOrder.get();
        int totalPrice = order.getTotalPrice();
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAmount(totalPrice);
        paymentRequest.setDescription("Hephaestus Order " + order.getOrderId());
        paymentRequest.setExpiredAt(Instant.now().plusSeconds(300).getEpochSecond());
        paymentRequest.setReturnUrl("https://hephaestus.store/Success?payToken="+jwtUtils.generateToken(order));
        paymentRequest.setCancelUrl("https://hephaestus.store/Cancelled");
        paymentRequest = paymentRequestRepository.save(paymentRequest);
        System.out.println(paymentRequest.toString());
        String createPaymentUrl = "https://payment.hephaestus.store/create-payment-link";
        RestTemplate restTemplate = new RestTemplate();
        String paymentUrl = restTemplate.postForObject(createPaymentUrl,paymentRequest,String.class);
        return paymentUrl;
    }

    public String successCheckOut(String token) {
        String username = jwtUtils.extractUsername(token);
        Optional<Order> activeOrder = orderRepository.findActiveOrderByUsername(username);
        if ((activeOrder.isPresent())) {
            Order order = activeOrder.get();
            if (!jwtUtils.isTokenExpired(token)) {
                setSuccessStatus(order);
                createNewCart(order);
                return "Thanh toán thành công";
            } else {
                return "Hết hạn đăng nhập";
            }
        } else {
            return "Đơn hàng không tồn tại!!";
        }
    }

    public List<Order> findAllByOrderStatusId(Long orderStatusId) {
        return orderRepository.findOrderByStatus(orderStatusId);
    }
}
