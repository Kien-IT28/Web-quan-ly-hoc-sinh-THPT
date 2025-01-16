package com.dev.quanlyhocsinh.repository;

import com.dev.quanlyhocsinh.model.ChuyenMon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChuyenMonRepository extends JpaRepository<ChuyenMon, Long> {
    // Tìm kiếm Chuyên môn theo tên
    ChuyenMon findByTenCM(String tenCM);
}
