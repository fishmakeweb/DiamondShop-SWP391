package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.dto.JewelryDTO;
import com.example.DiamondShopSystem.dto.NewReleaseDTO;
import com.example.DiamondShopSystem.model.Diamond;
import com.example.DiamondShopSystem.model.Jewelry;
import com.example.DiamondShopSystem.model.Product;
import com.example.DiamondShopSystem.model.ProductDetails;
import com.example.DiamondShopSystem.repository.DiamondRepository;
import com.example.DiamondShopSystem.repository.JewelryRepository;
import com.example.DiamondShopSystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final int size = 8;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JewelryRepository jewelryRepository;

    @Autowired
    private DiamondRepository diamondRepository;
    public Product createProductForJewelry(Jewelry jewelry) {
        Product product = new Product();
        product.setJewelry(jewelry);
        return productRepository.save(product);
    }

    public void deleteProductByJewelryId(Long jewelryId) {
        Optional<Product> product = productRepository.findByJewelryJewelryId(jewelryId);
        product.ifPresent(productRepository::delete);
    }

    public Product createProductForDiamond(Diamond diamond) {
        Product product = new Product();
        product.setDiamond(diamond);
        return productRepository.save(product);
    }
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product newProduct) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setDiamond(newProduct.getDiamond());
                    existingProduct.setJewelry(newProduct.getJewelry());
                    return productRepository.save(existingProduct);
                }).orElseGet(() -> {
                    newProduct.setProductId(id);
                    return productRepository.save(newProduct);
                });
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    //Láy product details r truyền vào ProductDetails DTO để trả về
    public ProductDetails getProductDetails(Long productId) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (!productOpt.isPresent()) {
            throw new RuntimeException("Product not found with id: " + productId);
        }

        Product product = productOpt.get();
        ProductDetails details = new ProductDetails();
        details.setProductId(product.getProductId());

        if (product.getDiamond() != null) {
            details.setDiamondId(product.getDiamond().getDiamondId());

            details.setDiamondMeasurement(product.getDiamond().getMeasurement().toString());
            details.setDiamondCarat(product.getDiamond().getCarat().toString());
            details.setDiamondColor(product.getDiamond().getColor().getColorDescription());
            details.setDiamondCut(product.getDiamond().getCut().getCutDescription());
            details.setDiamondClarity(product.getDiamond().getClarity().getClarityDescription());
            details.setDiamondGiaId(product.getDiamond().getGia().toString());
            details.setDiamondPrice(product.getDiamond().getPrice());
            details.setDiamondImage(product.getDiamond().getImg());
        }

        if (product.getJewelry() != null) {
            details.setJewelryId(product.getJewelry().getJewelryId());
            details.setJewelryName(product.getJewelry().getName());
            details.setJewelryImage(product.getJewelry().getImg());
            details.setJewelryPrice(product.getJewelry().getPrice());
            details.setJewelryMaterial(product.getJewelry().getMaterial().getMaterialName());
            details.setJewelryCategory(product.getJewelry().getCategory().getCategoryName());
            details.setJewelrySize(product.getJewelry().getSize().toString());
        }
        return details;
    }

//    public List<JewelryDTO> getAllJewelryDTOs() {
//        return productRepository.findAllJewelryDTOs();
//    }

    public List<JewelryDTO> getJewelryDTOs(int page) {
        List<JewelryDTO> jewelryDTOs = productRepository.findJewelryDTOs();
        return jewelryDTOs.subList((page - 1) * size, jewelryDTOs.size()>page*8? page*8 : jewelryDTOs.size());
    }

    public List<NewReleaseDTO> getNewReleaseDTOs(int page) {
        List<NewReleaseDTO> newReleaseDTOs = productRepository.findNewReleaseDTOs();
        return newReleaseDTOs.subList((page - 1) * size, newReleaseDTOs.size()>page*8? page*8 : newReleaseDTOs.size());
    }



}
