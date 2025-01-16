package com.dev.quanlyhocsinh.repository;

import com.dev.quanlyhocsinh.model.LopHoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LopHocRepositoty extends JpaRepository<LopHoc, Long> {

    // Lop hoc
    LopHoc findByTenLop (String tenLop);
    Optional<LopHoc> findByTenLopAndNamHoc(String tenLop, String namHoc);

    // Phương thức đếm số học sinh trong lớp
    long countById(LopHoc lopHoc);
}
