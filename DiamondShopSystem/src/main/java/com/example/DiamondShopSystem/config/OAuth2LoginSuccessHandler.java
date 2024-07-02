package com.example.DiamondShopSystem.config;

import com.example.DiamondShopSystem.model.CustomOAuth2User;
import com.example.DiamondShopSystem.model.Customer;
import com.example.DiamondShopSystem.model.Order;
import com.example.DiamondShopSystem.model.OrderStatus;
import com.example.DiamondShopSystem.repository.OrderRepository;
import com.example.DiamondShopSystem.service.CustomerService;
import com.example.DiamondShopSystem.service.JWTUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final CustomerService customerService;
    private final JWTUtils jwtUtils;
    private final OrderRepository orderRepository;

    public OAuth2LoginSuccessHandler(CustomerService customerService, JWTUtils jwtUtils,OrderRepository orderRepository) {
        this.customerService = customerService;
        this.jwtUtils = jwtUtils;
        this.orderRepository = orderRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(oidcUser);

        Customer customer = customerService.processOAuthPostLogin(customOAuth2User.getEmail(), customOAuth2User.getName());



        String token = jwtUtils.generateToken(customOAuth2User.getEmail());
        String redirectionUrl = UriComponentsBuilder.fromUriString("http://localhost:3000/profile")
                .queryParam("token", token)
                .build().toUriString();
        response.sendRedirect(redirectionUrl);
    }

    private void createOrderForCustomer(Customer customer) {
        Order order = new Order();
        OrderStatus defaultOrderStatus = new OrderStatus();
        order.setUsername(customer.getUsername());
        defaultOrderStatus.setStatusId(1L); // Assuming 1L is the default status ID
        order.setOrderStatus(defaultOrderStatus);
        orderRepository.save(order);
    }
}
