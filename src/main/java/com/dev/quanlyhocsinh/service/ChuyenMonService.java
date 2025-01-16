package com.dev.quanlyhocsinh.service;

import com.dev.quanlyhocsinh.model.ChuyenMon;
import com.dev.quanlyhocsinh.repository.ChuyenMonRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChuyenMonService {

    final ChuyenMonRepository chuyenMonRepository;

    public List<ChuyenMon> findAll() {
        return chuyenMonRepository.findAll();
    }

    public List<ChuyenMon> getAllChuyenMons() {
        return chuyenMonRepository.findAll();
    }

    public void save(ChuyenMon chuyenMon) {
        // Kiểm tra nếu tên chuyên môn đã tồn tại
        ChuyenMon existingChuyenMon = chuyenMonRepository.findByTenCM(chuyenMon.getTenCM());
        if (existingChuyenMon != null) {
            throw new IllegalArgumentException("Tên môn học đã tồn tại!");
        }
        // Lưu chuyên môn mới
        chuyenMonRepository.save(chuyenMon);
    }

    public void deleteById(Long id) {
        chuyenMonRepository.deleteById(id);
    }
}
