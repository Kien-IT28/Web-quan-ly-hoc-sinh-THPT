package com.dev.quanlyhocsinh.repository;


import com.dev.quanlyhocsinh.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    Optional<Account> findByUsername(String username);

    Account getByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsBySDT(String sdt);

    Account findByEmail(String mail);
}
