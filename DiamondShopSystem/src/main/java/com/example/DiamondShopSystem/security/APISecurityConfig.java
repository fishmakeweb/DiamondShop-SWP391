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
                        .requestMatchers(HttpMethod.GET, "/api/customers").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/customers/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/customers").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/customers/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/customers/**").permitAll()

                        // api staffs
                        .requestMatchers(HttpMethod.GET, "/api/staffs").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/staffs/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/staffs").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/staffs/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/staffs/**").permitAll()

                        // api articles
                        .requestMatchers(HttpMethod.GET, "/api/articles").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/articles/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/articles").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/articles/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/articles/**").permitAll()

                        // api carats
                        .requestMatchers(HttpMethod.GET, "/api/carats").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/carats/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/carats").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/carats/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/carats/**").permitAll()

                        // api carts
                        .requestMatchers(HttpMethod.GET, "/api/carts").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/carts/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/carts").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/carts/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/carts/**").permitAll()

                        // api cart-items
                        .requestMatchers(HttpMethod.GET, "/api/cartitems").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/cartitems/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/cartitems").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/cartitems/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/cartitems/**").permitAll()

                        // api categories, claritys, colors, cuts, fluorescences, gemstones, gias, materials, measurements, polishes, shapes, sizes, symmetrys
                        .requestMatchers(HttpMethod.GET, "/api/categories").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/categories").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/categories/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/categories/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/claritys").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/claritys/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/claritys").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/claritys/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/claritys/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/colors").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/colors/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/colors").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/colors/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/colors/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/cuts").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/cuts/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/cuts").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/cuts/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/cuts/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/fluorescences").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/fluorescences/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/fluorescences").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/fluorescences/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/fluorescences/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/gemstones").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/gemstones/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/gemstones").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/gemstones/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/gemstones/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/gias").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/gias/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/gias").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/gias/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/gias/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/materials").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/materials/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/materials").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/materials/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/materials/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/measurements").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/measurements/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/measurements").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/measurements/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/measurements/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/polishes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/polishes/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/polishes").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/polishes/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/polishes/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/shapes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/shapes/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/shapes").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/shapes/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/shapes/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/sizes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/sizes/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/sizes").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/sizes/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/sizes/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/symmetrys").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/symmetrys/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/symmetrys").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/symmetrys/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/symmetrys/**").permitAll()

                        // api diamonds, jewelrys, products
                        .requestMatchers(HttpMethod.GET, "/api/diamonds").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/diamonds/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/diamonds").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/diamonds/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/diamonds/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/jewelry").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/jewelry/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/jewelry").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/jewelry/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/jewelry/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/jewelry/categories/{categoryId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/jewelry/gemstones/{gemstoneId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/jewelry/price/range").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/products").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/products").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/products/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/products/**").permitAll()

                        // api deliveries
                        .requestMatchers(HttpMethod.GET, "/api/deliveries").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/deliveries/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/deliveries").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/deliveries/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/deliveries/**").permitAll()

                        // api orders and orderdetails
                        .requestMatchers(HttpMethod.GET,"/api/orders").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/orders/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/orders").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/api/orders/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/api/orders/**").permitAll()

                        .requestMatchers(HttpMethod.GET,"/api/orderdetails").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/orderdetails/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/orderdetails").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/api/orderdetails/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/api/orderdetails/**").permitAll()

                        // api roles
                        .requestMatchers(HttpMethod.GET,"/api/roles").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/roles/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/roles").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/api/roles/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE,"/api/roles/**").permitAll()

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
