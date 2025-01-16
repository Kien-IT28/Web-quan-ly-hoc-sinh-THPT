package com.dev.quanlyhocsinh.service;


import com.dev.quanlyhocsinh.model.Account;
import com.dev.quanlyhocsinh.repository.AccountRepository;
import com.dev.quanlyhocsinh.repository.RoleRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountService implements UserDetailsService {

    final AccountRepository accountRepository;

    final RoleRepository roleRepository;

    public void save(@NotNull Account account) {
        account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
        accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var account = accountRepository.findByUsername(username)
                .orElseThrow( ()-> new UsernameNotFoundException("Tài khoản không tồn tại") );
        return org.springframework.security.core.userdetails.User
                .withUsername(account.getUsername())
                .password(account.getPassword())
                .authorities(account.getAuthorities())
                .accountExpired(!account.isAccountNonExpired())
                .accountLocked(!account.isAccountNonLocked())
                .credentialsExpired(!account.isCredentialsNonExpired())
                .disabled(!account.isEnabled())
                .build();
    }

    // Tìm kiếm tài khoản dựa trên tên đăng nhập.
    public Optional<Account> findByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username);
    }

    public void updateAccount(Account updatedAccount) {
        // Tìm kiếm tài khoản cần cập nhật từ repository
        Optional<Account> existingAccountOptional = accountRepository.findById(updatedAccount.getId());
        if (existingAccountOptional.isPresent()) {
            // Lấy ra tài khoản đã tồn tại trong database
            Account existingAccount = existingAccountOptional.get();

            // Cập nhật thông tin từ updatedAccount
            existingAccount.setUsername(updatedAccount.getUsername());
            existingAccount.setEmail(updatedAccount.getEmail());
            existingAccount.setSDT(updatedAccount.getSDT());

            // Kiểm tra nếu có cung cấp mật khẩu mới
            if (updatedAccount.getPassword() != null && !updatedAccount.getPassword().isEmpty()) {
                // Mã hóa mật khẩu mới và cập nhật vào tài khoản
                String encodedPassword = new BCryptPasswordEncoder().encode(updatedAccount.getPassword());
                existingAccount.setPassword(encodedPassword);
            }
            accountRepository.save(existingAccount);
        } else {
            throw new RuntimeException("Không tìm thấy tài khoản để cập nhật");
        }

    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account không tìm thấy id: " + id));
    }

    public long countAccounts(){
        return accountRepository.count();
    }
}
