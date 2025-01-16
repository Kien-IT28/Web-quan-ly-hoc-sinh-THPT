package com.dev.quanlyhocsinh.service;

import com.dev.quanlyhocsinh.model.Account;
import com.dev.quanlyhocsinh.model.Role;
import com.dev.quanlyhocsinh.repository.AccountRepository;
import com.dev.quanlyhocsinh.repository.RoleRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QLGiaoVienService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccount(){
        return accountRepository.findAll();
    }

    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }

    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public void addAccount(@NotNull Account account) {
        // Mã hóa mật khẩu
        account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));

        // Tìm Role TEACHER trong database
        Role teacherRole = roleRepository.findByName("TEACHER")
                .orElseThrow(() -> new RuntimeException("Role TEACHER không tồn tại"));

        // Gán Role cho tài khoản
        account.getRoles().add(teacherRole);

        // Lưu tài khoản
        accountRepository.save(account);
    }

    public void updateAccount(Account account) {
        accountRepository.save(account); // Save updated account
    }
    public void saveAccTeacher(@NotNull Account account) {
        account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
        accountRepository.save(account);
    }
}
