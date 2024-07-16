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

    public AllDataDTO getAllData(String token) {
        String username = jwtUtils.extractUsername(token);
        Optional<Staff> staff = staffRepository.findByUsername(username);
        if (staff == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        } else {
            List<Category> categories = categoryRepository.findAll();
            List<Material> materials = materialRepository.findAll();
            List<Size> sizes = sizeRepository.findAll();
            List<Shape> shapes = shapeRepository.findAll();
            return new AllDataDTO(categories, materials, sizes, shapes);
        }

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

    public Jewelry saveJewelry(Jewelry jewelry, String token) {
        String username = jwtUtils.extractUsername(token);
        Staff staff = staffRepository.findByUsernameAndRoleRoleId(username, 4L);
        if (staff == null) {
            throw new RuntimeException("this token is invalid");
        }
        if (jewelry.getDiamond() != null) {
            Optional<Diamond> diamond = diamondRepository.findById(jewelry.getDiamond().getDiamondId());
            diamond.ifPresent(jewelry::setDiamond);
        }
        Jewelry savedJewelry = jewelryRepository.save(jewelry);
        return savedJewelry;
    }

    public Jewelry updateJewelry(Long id, Jewelry newJewelry, String token) {
        String username = jwtUtils.extractUsername(token);
        Staff staff = staffRepository.findByUsernameAndRoleRoleId(username, 4L);
        if (staff == null) {
            throw new RuntimeException("this token is invalid");
        }
        return jewelryRepository.findById(id)
                .map(existingJewelry -> {
                    existingJewelry.setName(newJewelry.getName());
                    existingJewelry.setImg(newJewelry.getImg());
                    existingJewelry.setPrice(newJewelry.getPrice());
                    existingJewelry.setDiamond(newJewelry.getDiamond());
                    existingJewelry.setMaterial(newJewelry.getMaterial());
                    existingJewelry.setShape(newJewelry.getShape());
                    existingJewelry.setCategory(newJewelry.getCategory());
                    existingJewelry.setSize(newJewelry.getSize());
                    return jewelryRepository.save(existingJewelry);
                }).orElseThrow(() -> new RuntimeException("Jewelry not found"));
    }

    public void deleteJewelry(Long id, String token) {
        String username = jwtUtils.extractUsername(token);
        Staff staff = staffRepository.findByUsernameAndRoleRoleId(username, 4L);
        if (staff == null) {
            throw new RuntimeException("this token is invalid");
        }
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

    public List<Jewelry> findAllByPriceRange(float minPrice, float maxPrice) {
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


}


