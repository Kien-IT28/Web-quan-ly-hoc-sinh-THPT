package com.dev.quanlyhocsinh.repository;

import com.dev.quanlyhocsinh.model.HocSinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HocSinhRepositoty extends JpaRepository<HocSinh, Long> {
    // Tìm học sinh theo tên lớp học
    List<HocSinh> findByLopHocTenLop(String tenLop);

    // Lấy danh sách học sinh theo tên lớp và năm học
    List<HocSinh> findByLopHocTenLopAndLopHocNamHoc(String tenLop, String namHoc);

    boolean existsByMhs(Long mhs);

    // Thêm phương thức đếm số học sinh theo lớp học
    long countByLopHocId(Long lopHocId);

}
