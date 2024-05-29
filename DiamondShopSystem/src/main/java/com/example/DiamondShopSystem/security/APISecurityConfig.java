package com.example.DiamondShopSystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class APISecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        // Create an instance of JdbcUserDetailsManager with the provided data source
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Define the query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT username, password, 1 as enabled FROM (" +
                        "SELECT username, password FROM staff " +
                        "UNION " +
                        "SELECT username, password FROM customer" +
                        ") as users WHERE username=?");

        // Define the query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT username, role_name FROM (" +
                        "SELECT s.username, r.role_name FROM staff s INNER JOIN role r ON s.role_id = r.role_id " +
                        "UNION " +
                        "SELECT c.username, 'ROLE_CUSTOMER' as role_name FROM customer c" +
                        ") as roles WHERE username=?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(configurer -> configurer
                        // api customers
                        .requestMatchers(HttpMethod.GET, "/api/customers").hasAnyRole("SALESTAFF", "DELIVERYSTAFF", "MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/customers/**").hasAnyRole("SALESTAFF", "DELIVERYSTAFF", "MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/customers").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/customers/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/customers/**").hasRole("ADMIN")

                        // api staffs
                        .requestMatchers(HttpMethod.GET, "/api/staffs").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/staffs/**").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/staffs").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/staffs/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/staffs/**").hasRole("ADMIN")

                        // api articles
                        .requestMatchers(HttpMethod.GET, "/api/articles").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/articles/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/articles").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/articles/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/articles/**").hasRole("MANAGER")

                        // api carats
                        .requestMatchers(HttpMethod.GET, "/api/carats").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/carats/**").hasAnyRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/carats").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/carats/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/carats/**").hasRole("MANAGER")

                        // api carts
                        .requestMatchers(HttpMethod.GET, "/api/carts").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/api/carts/**").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/api/carts").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.PUT, "/api/carts/**").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.DELETE, "/api/carts/**").hasRole("CUSTOMER")

                        // api cart-items
                        .requestMatchers(HttpMethod.GET, "/api/cartitems").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/api/cartitems/**").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/api/cartitems").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.PUT, "/api/cartitems/**").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.DELETE, "/api/cartitems/**").hasRole("CUSTOMER")

                        // api categories, claritys, colors, cuts, fluorescences, gemstones, gias, materials, measurements, polishes, shapes, sizes, symmetrys
                        .requestMatchers(HttpMethod.GET, "/api/categories").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/categories").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/categories/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/categories/**").hasRole("MANAGER")

                        .requestMatchers(HttpMethod.GET, "/api/claritys").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/claritys/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/claritys").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/claritys/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/claritys/**").hasRole("MANAGER")

                        .requestMatchers(HttpMethod.GET, "/api/colors").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/colors/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/colors").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/colors/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/colors/**").hasRole("MANAGER")

                        .requestMatchers(HttpMethod.GET, "/api/cuts").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/cuts/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/cuts").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/cuts/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/cuts/**").hasRole("MANAGER")

                        .requestMatchers(HttpMethod.GET, "/api/fluorescences").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/fluorescences/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/fluorescences").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/fluorescences/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/fluorescences/**").hasRole("MANAGER")

                        .requestMatchers(HttpMethod.GET, "/api/gemstones").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/gemstones/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/gemstones").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/gemstones/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/gemstones/**").hasRole("MANAGER")

                        .requestMatchers(HttpMethod.GET, "/api/gias").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/gias/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/gias").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/gias/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/gias/**").hasRole("MANAGER")

                        .requestMatchers(HttpMethod.GET, "/api/materials").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/materials/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/materials").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/materials/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/materials/**").hasRole("MANAGER")

                        .requestMatchers(HttpMethod.GET, "/api/measurements").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/measurements/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/measurements").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/measurements/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/measurements/**").hasRole("MANAGER")

                        .requestMatchers(HttpMethod.GET, "/api/polishes").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/polishes/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/polishes").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/polishes/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/polishes/**").hasRole("MANAGER")

                        .requestMatchers(HttpMethod.GET, "/api/shapes").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/shapes/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/shapes").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/shapes/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/shapes/**").hasRole("MANAGER")

                        .requestMatchers(HttpMethod.GET, "/api/sizes").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/sizes/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/sizes").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/sizes/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/sizes/**").hasRole("MANAGER")

                        .requestMatchers(HttpMethod.GET, "/api/symmetrys").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/symmetrys/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/symmetrys").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/symmetrys/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/symmetrys/**").hasRole("MANAGER")

                        // api diamonds, jewelrys, products
                        .requestMatchers(HttpMethod.GET, "/api/diamonds").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/diamonds/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/diamonds").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/diamonds/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/diamonds/**").hasRole("MANAGER")

                        .requestMatchers(HttpMethod.GET, "/api/jewelry").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/jewelry/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/jewelry").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/jewelry/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/jewelry/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/jewelry/categories/{categoryId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/jewelry/gemstones/{gemstoneId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/jewelry/price/range").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/products").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/products").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/products/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasRole("MANAGER")

                        // api deliveries
                        .requestMatchers(HttpMethod.GET, "/api/deliveries").hasAnyRole("SALESTAFF", "DELIVERYSTAFF")
                        .requestMatchers(HttpMethod.GET, "/api/deliveries/**").hasAnyRole("SALESTAFF", "DELIVERYSTAFF")
                        .requestMatchers(HttpMethod.POST, "/api/deliveries").hasRole("SALESTAFF")
                        .requestMatchers(HttpMethod.PUT, "/api/deliveries/**").hasRole("DELIVERYSTAFF")
                        .requestMatchers(HttpMethod.DELETE, "/api/deliveries/**").hasRole("SALESTAFF")

                        // api orders and orderdetails
                        .requestMatchers(HttpMethod.GET,"/api/orders").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.GET,"/api/orders/**").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.POST,"/api/orders").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.PUT,"/api/orders/**").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.DELETE,"/api/orders/**").hasRole("CUSTOMER")

                        .requestMatchers(HttpMethod.GET,"/api/orderdetails").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.GET,"/api/orderdetails/**").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.POST,"/api/orderdetails").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.PUT,"/api/orderdetails/**").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.DELETE,"/api/orderdetails/**").hasRole("CUSTOMER")

                        // api roles
                        .requestMatchers(HttpMethod.GET,"/api/roles").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/roles/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/roles").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/roles/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/roles/**").hasRole("ADMIN")

                        // api supports
                        .requestMatchers(HttpMethod.GET, "/api/supports").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/supports/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/supports").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/supports/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/api/deliveries/**").permitAll()



                )
                .httpBasic(Customizer.withDefaults()) // Use HTTP Basic authentication
                .csrf(csrf -> csrf.disable()); // Disable CSRF for stateless REST APIs

        return http.build();
    }
}
