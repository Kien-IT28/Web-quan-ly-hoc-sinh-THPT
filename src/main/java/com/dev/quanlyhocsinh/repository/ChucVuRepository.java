package com.dev.quanlyhocsinh.repository;

import com.dev.quanlyhocsinh.model.ChucVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChucVuRepository extends JpaRepository<ChucVu, Long> {
}
