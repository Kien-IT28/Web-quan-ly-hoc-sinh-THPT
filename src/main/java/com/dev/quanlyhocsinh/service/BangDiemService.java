package com.dev.quanlyhocsinh.service;

import com.dev.quanlyhocsinh.model.BangDiem;
import com.dev.quanlyhocsinh.model.ChuyenMon;
import com.dev.quanlyhocsinh.repository.BangDiemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BangDiemService {

    @Autowired
    private BangDiemRepository bangDiemRepository;

    public List<BangDiem> getAllBangDiem() {
        return bangDiemRepository.findAll();
    }

    // Get all grade sheets
    public List<BangDiem> findAll() {
        return bangDiemRepository.findAll();
    }

    // Lấy danh sách bảng điểm của học sinh theo mã học sinh (mhs)
    public List<BangDiem> ListBangDiemTheoID(Long id) {
        return bangDiemRepository.findByHocSinh_Mhs(id);
    }

    // Thêm bảng điểm mới
    public BangDiem addBangDiem(BangDiem bangDiem) {
        // Kiểm tra nếu tên môn học đã tồn tại
        boolean exists = bangDiemRepository.existsByMonHoc(bangDiem.getMonHoc());
//        if (exists) {
//            throw new IllegalArgumentException("Tên môn học đã tồn tại!");
//        }
        bangDiem.calculateDiemCN();  // Tính điểm cả năm
        return bangDiemRepository.save(bangDiem);
    }

    public BangDiem editBangDiem(BangDiem bangDiem) {
        // Lấy bảng điểm hiện tại từ ID
        BangDiem currentBangDiem = findById(bangDiem.getId());

        // Cập nhật điểm học kỳ I và II
        currentBangDiem.setDiemTBHKI(bangDiem.getDiemTBHKI());
        currentBangDiem.setDiemTBHKII(bangDiem.getDiemTBHKII());

        // Tính lại điểm cả năm (trung bình cộng)
        currentBangDiem.calculateDiemCN();

        // Lưu và trả về bảng điểm đã cập nhật
        return bangDiemRepository.save(currentBangDiem);
    }



    public void deleteById(int id) {
        bangDiemRepository.deleteById(id);
    }

    public BangDiem findById(int id) {
        return bangDiemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bảng điểm không tồn tại!"));
    }


}
