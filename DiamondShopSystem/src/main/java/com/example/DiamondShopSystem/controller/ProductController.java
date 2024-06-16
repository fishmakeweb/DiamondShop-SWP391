package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.dto.JewelryDTO;
import com.example.DiamondShopSystem.model.Product;
import com.example.DiamondShopSystem.model.ProductDetails;
import com.example.DiamondShopSystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.findAllProducts();
    }


//    @GetMapping("/products/jewelry")
//    public List<JewelryDTO> getAllJewelryDTOs() {
//        return productService.getAllJewelryDTOs();
//    }
    @GetMapping("/products/jewelry/{page}")
    public Page<JewelryDTO> getJewelryPage(@PathVariable int page) {
    return productService.getAllJewelryDTOs(page);
    }
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @GetMapping("/products/details/{id}")
    public ResponseEntity<ProductDetails> getProductDetails(@PathVariable Long id) {
        ProductDetails details = productService.getProductDetails(id);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @PostMapping("/secure/products")
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/secure/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/secure/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }


}
