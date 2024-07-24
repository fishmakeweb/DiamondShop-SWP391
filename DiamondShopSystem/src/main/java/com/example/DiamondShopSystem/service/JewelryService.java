package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.dto.AllDataDTO;
import com.example.DiamondShopSystem.model.*;
import com.example.DiamondShopSystem.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JewelryService {
    @Autowired
    private JewelryRepository jewelryRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DiamondRepository diamondRepository;


    @Autowired
    private ShapeRepository shapeRepository;


    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public AllDataDTO getAllData() {

        List<Category> categories = categoryRepository.findAll();
        List<Material> materials = materialRepository.findAll();
        List<Size> sizes = sizeRepository.findAll();
        List<Shape> shapes = shapeRepository.findAll();
        return new AllDataDTO(categories, materials, sizes, shapes);


    }

    public List<Jewelry> findAllJewelry() {
        return jewelryRepository.findAll();
    }

    public Jewelry findJewelryById(Long id) {
        Optional<Jewelry> jewelry = jewelryRepository.findById(id);
        return jewelry.orElse(null);
    }

    public boolean checkNameExists(String name) {
        return jewelryRepository.existsByName(name);
    }

    public Jewelry saveJewelry(Jewelry jewelry) {
        if (jewelry.getDiamond() != null) {
            Optional<Diamond> diamond = diamondRepository.findById(jewelry.getDiamond().getDiamondId());
            diamond.ifPresent(jewelry::setDiamond);
        }
        Jewelry savedJewelry = jewelryRepository.save(jewelry);
        return savedJewelry;
    }

    public Jewelry updateJewelry(Long id, Jewelry newJewelry) {
        Jewelry old = jewelryRepository.findByJewelryId(id);
        if (old.isSold()&&newJewelry.getQuantity()!=0) old.setSold(false);
        old.setName(newJewelry.getName());
        old.setImg(newJewelry.getImg());
        old.setPrice(newJewelry.getPrice());
        old.setDiamond(newJewelry.getDiamond());
        old.setMaterial(newJewelry.getMaterial());
        old.setShape(newJewelry.getShape());
        old.setSize(newJewelry.getSize());
        old.setQuantity(newJewelry.getQuantity());
        return jewelryRepository.save(old);
    }

    public void deleteJewelry(Long id) {

        OrderDetail od = orderDetailRepository.findByJewelryJewelryId(id);
        if (od != null) {
            throw new RuntimeException("Jewelry currently exist in order detail ");
        }
        Optional<Jewelry> jewelry = jewelryRepository.findById(id);
        if (jewelry.isPresent()) {
            jewelryRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Jewelry not found with id " + id);
        }
    }

    public List<Jewelry> findAllByCategoryId(Long categoryId) {
        return jewelryRepository.findAll().stream()
                .filter(jewelry -> jewelry.getCategory().getCategoryId().equals(categoryId))
                .collect(Collectors.toList());
    }

    public List<Jewelry> findAllByPriceRange(int minPrice, int maxPrice) {
        return jewelryRepository.findAll().stream()
                .filter(jewelry -> jewelry.getPrice() >= minPrice && jewelry.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public void setStatusJewelry(Long id, boolean status) {

        jewelryRepository.findById(id).ifPresent(jewelry -> {
            jewelry.setSold(status);
            jewelryRepository.save(jewelry);
        });

    }

    public List<Jewelry> findAllByJewelryCategory(Long categoryId) {
        return jewelryRepository.findJewelryByCategory(categoryId);
    }

}


