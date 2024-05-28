package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Product;
import com.example.DiamondShopSystem.model.ProductDetails;
import com.example.DiamondShopSystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

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
            details.setDiamondShape(product.getDiamond().getShape().getShapeDescription());
            details.setDiamondMeasurement(product.getDiamond().getMeasurement().toString());
            details.setDiamondCarat(product.getDiamond().getCarat().toString());
            details.setDiamondColor(product.getDiamond().getColor().getColorDescription());
            details.setDiamondCut(product.getDiamond().getCut().getCutDescription());
            details.setDiamondClarity(product.getDiamond().getClarity().getClarityDescription());
            details.setDiamondPolish(product.getDiamond().getPolish().getPolishDescription());
            details.setDiamondSymmetry(product.getDiamond().getSymmetry().getSymmetryDescription());
            details.setDiamondFluorescence(product.getDiamond().getFluorescence().getFluorescenceDescription());
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
            details.setJewelryGemstone(product.getJewelry().getGemstone().getGemstoneName());
            details.setJewelrySize(product.getJewelry().getSize().toString());
        }
        return details;
    }
}
