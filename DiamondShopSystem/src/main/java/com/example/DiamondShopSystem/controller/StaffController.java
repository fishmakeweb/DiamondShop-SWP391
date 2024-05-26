package com.example.DiamondShopSystem.controller;


import com.example.DiamondShopSystem.service.StaffService;
import com.example.DiamondShopSystem.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/staffs")
@CrossOrigin(origins = "http://localhost:3000")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping
    public List<Staff> getAllStaffs() {
        return staffService.findAllStaffs();
    }

    @GetMapping("/{id}")
    public Staff getStaffById(@PathVariable Long id) {
        return staffService.findStaffById(id);
    }

    @PostMapping
    public Staff createStaff(@RequestBody Staff Staff) {
        return staffService.saveStaff(Staff);
    }

    @PutMapping("/{id}")
    public Staff updateStaff(@PathVariable Long id, @RequestBody Staff Staff) {
        return staffService.updateStaff(id, Staff);
    }

    @DeleteMapping("/{id}")
    public void deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
    }
}
