package com.dev.quanlyhocsinh.service;

import com.dev.quanlyhocsinh.model.BangDiem;
import com.dev.quanlyhocsinh.model.HocSinh;
import com.dev.quanlyhocsinh.model.LopHoc;
import com.dev.quanlyhocsinh.repository.BangDiemRepository;
import com.dev.quanlyhocsinh.repository.HocSinhRepositoty;
import com.dev.quanlyhocsinh.repository.LopHocRepositoty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HocSinhService {

    final HocSinhRepositoty hocSinhRepository;
    final LopHocRepositoty lopHocRepository;
    final BangDiemRepository bangDiemRepository;

    public List<HocSinh> getAllHocSinh() {
        List<HocSinh> hocSinhs = hocSinhRepository.findAll();
        System.out.println("Danh sách học sinh: " + hocSinhs);
        return hocSinhs;
    }

    public List<HocSinh> listHocSinhTheoLopVaNamHoc(String tenLop, String namHoc) {
        return hocSinhRepository.findByLopHocTenLopAndLopHocNamHoc(tenLop, namHoc);
    }

    public HocSinh saveHocSinh(HocSinh hocSinh){
        return this.hocSinhRepository.save(hocSinh);
    }

    public HocSinh findHocSinhById(Long id) {
        return hocSinhRepository.findById(id).orElse(null);
    }

    public void edit(HocSinh hocSinh) {
        // Kiểm tra xem học sinh có đối tượng đã tồn tại hay không, không thay đổi các trường không cần thiết.
        Optional<HocSinh> existingHocSinh = hocSinhRepository.findById(hocSinh.getMhs());

        if (existingHocSinh.isPresent()) {
            HocSinh existing = existingHocSinh.get();

            // Cập nhật chỉ những trường cần thiết
            if (hocSinh.getTenHocSinh() != null) {
                existing.setTenHocSinh(hocSinh.getTenHocSinh());
            }
            if (hocSinh.getNgaySinh() != null) {
                existing.setNgaySinh(hocSinh.getNgaySinh());
            }
            if (hocSinh.getGioiTinh() != null) {
                existing.setGioiTinh(hocSinh.getGioiTinh());
            }
            if (hocSinh.getQueQuan() != null) {
                existing.setQueQuan(hocSinh.getQueQuan());
            }

            // Lưu lại đối tượng học sinh đã cập nhật
            hocSinhRepository.save(existing);
        }
    }



    // Phương thức đếm tổng số học sinh
    public long countHocSinhs() {
        return hocSinhRepository.count();
    }

    // Kiểm tra mã học sinh có tồn tại trong cơ sở dữ liệu không
    public boolean existsById(Long mhs) {
        return hocSinhRepository.existsById(mhs); // Trả về true nếu tồn tại
    }
    public void deleteHocSinhById(Long mhs) {
        hocSinhRepository.deleteById(mhs);
    }
}
