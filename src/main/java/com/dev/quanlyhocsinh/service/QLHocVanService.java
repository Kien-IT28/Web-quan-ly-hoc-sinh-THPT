package com.dev.quanlyhocsinh.service;

import com.dev.quanlyhocsinh.model.ChungChi;
import com.dev.quanlyhocsinh.repository.ChungChiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QLHocVanService {

    @Autowired
    private ChungChiRepository chungChiRepository;

    public List<ChungChi> findAll(){
        return chungChiRepository.findAll();
    }

    public ChungChi findById(Long id) {
        return chungChiRepository.findById(id).orElse(null); // Trả về null nếu không tìm thấy
    }

    public List<ChungChi> getAllChungChis(){
        return chungChiRepository.findAll();
    }

    public void save(ChungChi chungChi) {
        // Kiểm tra xem tên chứng chỉ đã tồn tại chưa
        if (chungChiRepository.existsByTenChungChi(chungChi.getTenChungChi())) {
            throw new IllegalArgumentException("Chứng chỉ này đã tồn tại."); // Hoặc bạn có thể tạo một lỗi tuỳ chỉnh
        }
        chungChiRepository.save(chungChi);  // Lưu chứng chỉ nếu chưa tồn tại
    }

    public void deleteById(Long id){
        chungChiRepository.deleteById(id);
    }
}
