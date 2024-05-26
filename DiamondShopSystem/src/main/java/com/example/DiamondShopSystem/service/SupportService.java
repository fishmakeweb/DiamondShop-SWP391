package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Support;
import com.example.DiamondShopSystem.repository.SupportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SupportService {
    @Autowired
    private SupportRepository supportRepository;

    public List<Support> findAllSupports() {
        return supportRepository.findAll();
    }

    public Support findSupportById(Long id) {
        Optional<Support> support = supportRepository.findById(id);
        return support.orElse(null);
    }

    public Support saveSupport(Support support) {
        return supportRepository.save(support);
    }

    public Support updateSupport(Long id, Support newSupport) {
        return supportRepository.findById(id)
                .map(existingSupport -> {
                    existingSupport.setDate(newSupport.getDate());
                    existingSupport.setMessage(newSupport.getMessage());
                    existingSupport.setCustomer(newSupport.getCustomer());
                    existingSupport.setStaff(newSupport.getStaff());
                    return supportRepository.save(existingSupport);
                }).orElseGet(() -> {
                    newSupport.setSupportId(id);
                    return supportRepository.save(newSupport);
                });
    }

    public void deleteSupport(Long id) {
        supportRepository.deleteById(id);
    }
}
