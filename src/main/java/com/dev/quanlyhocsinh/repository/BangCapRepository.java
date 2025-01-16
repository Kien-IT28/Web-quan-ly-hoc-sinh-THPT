package com.dev.quanlyhocsinh.repository;

import com.dev.quanlyhocsinh.model.BangCap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BangCapRepository extends JpaRepository<BangCap, Long> {
}
