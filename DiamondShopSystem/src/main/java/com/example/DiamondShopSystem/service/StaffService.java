package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Role;
import com.example.DiamondShopSystem.repository.RoleRepository;
import com.example.DiamondShopSystem.repository.StaffRepository;
import com.example.DiamondShopSystem.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private JWTUtils jwtUtils;

    public List<Staff> findAllStaffs(String token) {
        String username = jwtUtils.extractUsername(token);
        Staff staff = staffRepository.findByUsernameAndRoleRoleId(username,4L);
        if (staff == null) {
            throw new RuntimeException("This token is invalid");
        } else {
            return staffRepository.findAll();
        }
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
                    Role role = roleRepository.findByRoleName(newStaff.getRole().getRoleName());
                    staff.setRole(role);
                    return staffRepository.save(staff);
                }).orElseThrow(() -> new RuntimeException("Staff not found"));
    }



    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }
}
