package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Role;
import com.example.DiamondShopSystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    public Role findRoleById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.orElse(null);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Long id, Role newRole) {
        return roleRepository.findById(id)
                .map(existingRole -> {
                    existingRole.setRoleName(newRole.getRoleName());
                    return roleRepository.save(existingRole);
                }).orElseGet(() -> {
                    newRole.setRoleId(id);
                    return roleRepository.save(newRole);
                });
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
