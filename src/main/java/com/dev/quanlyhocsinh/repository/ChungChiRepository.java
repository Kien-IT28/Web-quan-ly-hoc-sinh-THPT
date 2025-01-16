package com.dev.quanlyhocsinh.repository;

import com.dev.quanlyhocsinh.model.ChungChi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChungChiRepository extends JpaRepository<ChungChi, Long> {
    // Kiểm tra xem tên chứng chỉ có tồn tại trong cơ sở dữ liệu không
    boolean existsByTenChungChi(String tenChungChi);
}
