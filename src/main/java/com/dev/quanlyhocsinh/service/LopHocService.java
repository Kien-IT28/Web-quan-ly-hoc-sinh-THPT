package com.dev.quanlyhocsinh.service;

import com.dev.quanlyhocsinh.model.LopHoc;
import com.dev.quanlyhocsinh.repository.HocSinhRepositoty;
import com.dev.quanlyhocsinh.repository.LopHocRepositoty;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class LopHocService {

    final LopHocRepositoty lopHocRepositoty;

    final HocSinhRepositoty hocSinhRepositoty;

    // Get all LopHoc
    public List<LopHoc> findAll() {
        return lopHocRepositoty.findAll();
    }

    // Thêm LopHoc
    public void add(LopHoc lopHoc) {
        // Kiểm tra nếu lớp học và năm học đã tồn tại
        Optional<LopHoc> existingLopHoc = lopHocRepositoty.findByTenLopAndNamHoc(lopHoc.getTenLop(), lopHoc.getNamHoc());
        if (existingLopHoc.isPresent()) {
            throw new IllegalArgumentException("Lớp học đã tồn tại trong năm học này!");
        }

        // Kiểm tra điều kiện sĩ số
        if (lopHoc.getSiSo() <= 0 || lopHoc.getSiSo() > 40) {
            throw new IllegalArgumentException("Sĩ số phải lớn hơn 0 và nhỏ hơn hoặc bằng 40!");
        }

        // Lưu lớp học mới
        lopHocRepositoty.save(lopHoc);
    }

    public void edit(LopHoc lopHoc) {
        // Tìm lớp học hiện tại từ cơ sở dữ liệu
        LopHoc existingLopHoc = lopHocRepositoty.findById(lopHoc.getId())
                .orElseThrow(() -> new IllegalArgumentException("Lớp học không tồn tại!"));

        // Kiểm tra trùng tên lớp và năm học
        Optional<LopHoc> lopHocConflict = lopHocRepositoty.findByTenLopAndNamHoc(lopHoc.getTenLop(), lopHoc.getNamHoc());
        if (lopHocConflict.isPresent() && !lopHocConflict.get().getId().equals(lopHoc.getId())) {
            throw new IllegalArgumentException("Tên lớp và năm học đã tồn tại với lớp khác!");
        }

        // Kiểm tra điều kiện sĩ số
        if (lopHoc.getSiSo() <= 0 || lopHoc.getSiSo() > 40) {
            throw new IllegalArgumentException("Sĩ số phải lớn hơn 0 và nhỏ hơn hoặc bằng 40!");
        }

        // Kiểm tra tính hợp lệ của tên giáo viên chủ nhiệm
        if (lopHoc.getTenGVCN().isBlank()) {
            throw new IllegalArgumentException("Tên giáo viên chủ nhiệm không được để trống!");
        }

        // Cập nhật các trường thông tin
        existingLopHoc.setTenLop(lopHoc.getTenLop());
        existingLopHoc.setNamHoc(lopHoc.getNamHoc());
        existingLopHoc.setSiSo(lopHoc.getSiSo());
        existingLopHoc.setTenGVCN(lopHoc.getTenGVCN());

        // Lưu thay đổi
        lopHocRepositoty.save(existingLopHoc);
    }

    // Delete LopHoc by ID
    public void deleteLopHoc(Long id) {
        LopHoc lopHoc = lopHocRepositoty.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lớp học không tồn tại!"));

        // Kiểm tra nếu lớp học còn học sinh
        long countHocSinh = hocSinhRepositoty.countByLopHocId(id);
        if (countHocSinh > 0) {
            throw new IllegalStateException("Không thể xóa lớp học có học sinh!");
        }

        lopHocRepositoty.delete(lopHoc);
    }




    public LopHoc getLopHocById(Long lopHocId) {
        return lopHocRepositoty.findById(lopHocId)
                .orElseThrow(() -> new IllegalArgumentException("Lớp học không tồn tại"));
    }

    public LopHoc timKiemLopHoc(String tenLop, String namHoc){
        LopHoc lopHoc = null;
        // Kiểm tra nếu lớp học và năm học đã tồn tại
        Optional<LopHoc> existingLopHoc = lopHocRepositoty.findByTenLopAndNamHoc(tenLop, namHoc);
        if (existingLopHoc.isPresent()) {
            lopHoc = existingLopHoc.get();
        }
        return lopHoc;
    }
    // Phương thức lưu lớp học
    public LopHoc saveLopHoc(LopHoc lopHoc) {
        return lopHocRepositoty.save(lopHoc);  // Lưu lớp học vào cơ sở dữ liệu
    }
}