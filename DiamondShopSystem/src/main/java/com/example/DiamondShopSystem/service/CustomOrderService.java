package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.dto.CustomOrderUpdateDTO;
import com.example.DiamondShopSystem.model.*;
import com.example.DiamondShopSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomOrderService {

    @Autowired
    private CustomOrderRepository customOrderRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private CustomJewelryRepository customJewelryRepository;
    @Autowired
    private PaymentRequestRepository paymentRequestRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private CustomOrderChatMessageRepository  customOrderChatMessageRepository;
    @Autowired
    private DiamondRepository diamondRepository;

    public void saveChatMessage(CustomOrderChatMessage message) {
        // Here, you might add any business logic before saving the message
        customOrderChatMessageRepository.save(message);
    }

    public List<CustomOrder> getAllOrders() {
            return customOrderRepository.findAll();
    }

    public Optional<CustomOrder> getOrderById(Long id) {
        return customOrderRepository.findById(id);
    }

    public CustomOrder createOrder(CustomOrder customOrder) {
        return customOrderRepository.save(customOrder);
    }



    public CustomOrder updateCustomOrderAtr(Long id, CustomOrderUpdateDTO updateDTO) {
            return customOrderRepository.findById(id)
                    .map(customOrder -> {
                        customOrder.setFullpaid(updateDTO.getFullPaid());
                        customOrder.setDescription(updateDTO.getDescription());
                        customOrder.setFinishDate(updateDTO.getFinishDate());
                        return customOrderRepository.save(customOrder);
                    })
                    .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }

    public void deleteCustomOrder(Long id) {
        List<CustomOrderChatMessage> customOrderMessages = customOrderChatMessageRepository.findByCustomOrderCustomOrderId(id);
        customOrderMessages.forEach(customOrderChatMessage -> customOrderChatMessageRepository.delete(customOrderChatMessage));
        Optional<CustomOrder> customOrder = getOrderById(id);
        Long customJewelryId = customOrder.get().getCustomJewelry().getCustomJewelryId();
        customOrderRepository.deleteById(id);
        customJewelryRepository.deleteById(customJewelryId);
    }

    public void createCustomOrder(CustomJewelry customJewelry, String token) {
        // Save the CustomJewelry entity first
        CustomJewelry savedJewelry = customJewelryRepository.save(customJewelry);

        // Extract username from token
        String username = jwtUtils.extractUsername(token);

        // Create and save the CustomOrder entity with the saved CustomJewelry
        CustomOrder customOrder = new CustomOrder();
        customOrder.setUsername(username);
        customOrder.setCustomJewelry(savedJewelry);
        customOrder.setPrepaid(Math.round(savedJewelry.getPrice() * 0.3f));
        customOrder.setFullpaid(savedJewelry.getPrice());
        customOrder.setOrderStatus(orderStatusRepository.findById(2L).orElseThrow(() -> new RuntimeException("Order status not found")));
        customOrder.setDescription("CHƯA THANH TOÁN");
        customOrder.setStartDate(new Date());
        customOrder.setFinishDate(null);

        customOrderRepository.save(customOrder);
    }

    public List<CustomOrder> getCustomOrders(String token) {
        String username = jwtUtils.extractUsername(token);
        return customOrderRepository.findByUsername(username);
    }

    public String checkOutCustomOrder(String token, Long customOrderId) {
        String username = jwtUtils.extractUsername(token);
        CustomOrder customOrder = customOrderRepository.findByUserNameAndCustomOrderId(username, customOrderId);
        if (customOrder == null) {
            throw new IllegalArgumentException("Không tìm thấy đơn hàng.");
        }

        if (customOrder.getCustomJewelry().getDiamond().isSold()) {
            throw new IllegalStateException("Kim cương đã bị bán.");

        }
        int totalPrice = customOrder.getPrepaid();
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAmount(totalPrice);
        paymentRequest.setDescription("Mã đơn hàng " + customOrder.getCustomOrderId());
        paymentRequest.setExpiredAt(Instant.now().plusSeconds(300).getEpochSecond());

//        https://hephaestus.store/Success?payToken=
//        paymentRequest.setReturnUrl("https://hephaestus.store/SuccessForCustomOrder?payToken=" + jwtUtils.generateCustomOrderToken(customOrder));
        paymentRequest.setReturnUrl("https://hephaestus.store/SuccessForCustomOrder?payToken=" + jwtUtils.generateCustomOrderToken(customOrder));

//        https://hephaestus.store/Cancelled
        paymentRequest.setCancelUrl("https://hephaestus.store/Cancelled");
        paymentRequest = paymentRequestRepository.save(paymentRequest);
        System.out.println(paymentRequest.toString());

//        https://payment.hephaestus.store/create-payment-link
        String createPaymentUrl = "https://payment.hephaestus.store/create-payment-link";
        RestTemplate restTemplate = new RestTemplate();
        String paymentUrl = restTemplate.postForObject(createPaymentUrl, paymentRequest, String.class);
//        System.out.println(paymentUrl);
        return paymentUrl;
    }

    public String successCheckOutForCustomOrder(String token) {
        Long customOrderId = Long.valueOf(jwtUtils.extractCustomOrderId(token));
        CustomOrder customOrder = customOrderRepository.findById(customOrderId).get();
        if (customOrder != null) {
            if (!jwtUtils.isTokenExpired(token)) {
                if (customOrder.getCustomJewelry().getDiamond()!=null) customOrder.getCustomJewelry().getDiamond().setSold(true);
                setSuccessStatusForCustomOrder(customOrder);
                return "Thanh toán thành công";
            } else {
                return "Hết hạn đăng nhập";
            }
        } else {
            return "Đơn hàng không tồn tại!!";
        }
    }

    public void setSuccessStatusForCustomOrder(CustomOrder customOrder) {
        OrderStatus successStatus = orderStatusRepository.findById(3L).get();
        customOrder.setOrderStatus(successStatus);
        customOrder.setDescription("THANH TOÁN TRƯỚC THÀNH CÔNG");
        customOrderRepository.save(customOrder);
    }

    public List<CustomOrder> findAllByOrderStatusId(Long orderStatusId) {
        return customOrderRepository.findCustomOrderByStatus(orderStatusId);
    }

    public CustomOrder handleCancelCustomOrder(Long id) {
        Optional<CustomOrder> customOrderOptional = getOrderById(id);

        if (customOrderOptional.isPresent()) {
            CustomOrder customOrder = customOrderOptional.get();
            customOrder.setDescription("YÊU CẦU HỦY ĐƠN");
            customOrderRepository.save(customOrder);
            return customOrder;
        } else {
            throw new RuntimeException("Mã đơn hàng không tồn tại " + id);
        }
    }

}
