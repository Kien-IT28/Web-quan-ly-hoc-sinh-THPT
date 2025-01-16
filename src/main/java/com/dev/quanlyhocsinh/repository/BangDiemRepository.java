package com.dev.quanlyhocsinh.repository;

import com.dev.quanlyhocsinh.model.BangDiem;
import com.dev.quanlyhocsinh.model.ChuyenMon;
import com.dev.quanlyhocsinh.model.HocSinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BangDiemRepository extends JpaRepository<BangDiem, Integer> {
    boolean existsByMonHoc(String monHoc);

    // Tìm tất cả bảng điểm của học sinh theo mã học sinh (mhs)
    List<BangDiem> findByHocSinh_Mhs(Long mhs);

    // Kiểm tra nếu học sinh đã có bảng điểm cho môn học này
    boolean existsByHocSinhAndMonHoc(HocSinh hocSinh, ChuyenMon tenCm);
}
