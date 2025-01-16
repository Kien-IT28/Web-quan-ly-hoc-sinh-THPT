package com.dev.quanlyhocsinh.config;

import com.dev.quanlyhocsinh.model.Account;
import com.dev.quanlyhocsinh.model.Role;
import com.dev.quanlyhocsinh.repository.AccountRepository;
import com.dev.quanlyhocsinh.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Kiểm tra nếu role ADMIN đã tồn tại, nếu không tạo mới
        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName("ADMIN");
                    newRole.setDescription("Vai trò quản trị viên");
                    return roleRepository.save(newRole);
                });

        // Kiểm tra nếu tài khoản admin đã tồn tại
        accountRepository.findByUsername("admin").ifPresentOrElse(
                account -> logger.info("Tài khoản admin đã tồn tại!"),
                () -> {
                    // Tạo tài khoản admin mới
                    Account adminAccount = new Account();
                    adminAccount.setUsername("admin");
                    adminAccount.setPassword(passwordEncoder.encode("#admin123"));
                    adminAccount.setEmail("admin@gmail.com");
                    adminAccount.setSDT("0123456789");

                    // Gán Role ADMIN cho tài khoản
                    adminAccount.getRoles().add(adminRole);

                    // Lưu tài khoản vào database
                    accountRepository.save(adminAccount);

                    logger.info("Tài khoản admin đã được tạo thành công!");
                }
        );
    }
}
