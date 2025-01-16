package com.dev.quanlyhocsinh.service;

import com.dev.quanlyhocsinh.model.Role;
import com.dev.quanlyhocsinh.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Transactional
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role không tìm thấy với id: " + id));
    }

    public Role save(Role role) {
        if (roleRepository.findByName(role.getName()).isPresent()) {
            throw new IllegalArgumentException("Role " + role.getName() +" đã tồn tại!");
        }
        return roleRepository.save(role);
    }

    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }
}
