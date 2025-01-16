package com.dev.quanlyhocsinh.repository;

import com.dev.quanlyhocsinh.model.PhongBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhongBanRepository extends JpaRepository<PhongBan, Long> {
}
