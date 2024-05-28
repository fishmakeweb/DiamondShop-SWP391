package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.repository.StaffRepository;
import com.example.DiamondShopSystem.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public List<Staff> findAllStaffs() {
        return staffRepository.findAll();
    }

    public Staff findStaffById(Long id) {
        Optional<Staff> staff = staffRepository.findById(id);
        return staff.orElse(null);
    }

    public Staff saveStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public Staff updateStaff(Long id, Staff newStaff) {
        return staffRepository.findById(id)
                .map(staff -> {
                    staff.setFullName(newStaff.getFullName());
                    staff.setEmail(newStaff.getEmail());
                    staff.setUsername(newStaff.getUsername());
                    staff.setPassword(newStaff.getPassword());
                    staff.setRole(newStaff.getRole());
                    return staffRepository.save(staff);
                }).orElseGet(() -> {
                    newStaff.setStaffId(id);
                    return staffRepository.save(newStaff);
                });
    }

    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }
}
