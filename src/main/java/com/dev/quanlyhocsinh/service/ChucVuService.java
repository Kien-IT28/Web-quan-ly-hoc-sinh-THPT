package com.dev.quanlyhocsinh.service;

import com.dev.quanlyhocsinh.dto.ChucVuDTO;
import com.dev.quanlyhocsinh.model.ChucVu;
import com.dev.quanlyhocsinh.repository.ChucVuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChucVuService {

    @Autowired
    private ChucVuRepository chucVuRepository;

    public List<ChucVu> getAllChucVu() {
        return chucVuRepository.findAll();
    }

    public Optional<ChucVu> getChucVuById(Long id) {
        return chucVuRepository.findById(id);
    }

    public ChucVu saveChucVu(ChucVu chucVu) {
        return chucVuRepository.save(chucVu);
    }

    public void deleteChucVu(Long id) {
        chucVuRepository.deleteById(id);
    }
}
