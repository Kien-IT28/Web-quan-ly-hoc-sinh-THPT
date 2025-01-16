package com.dev.quanlyhocsinh.service;

import com.dev.quanlyhocsinh.model.BangCap;
import com.dev.quanlyhocsinh.repository.BangCapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BangCapService {

    @Autowired
    private BangCapRepository bangCapRepository;

    public List<BangCap> findAll() {
        return bangCapRepository.findAll();
    }

    public List<BangCap> getAllBangCaps(){
        return bangCapRepository.findAll();
    }

    public BangCap save(BangCap bangCap) {
        return bangCapRepository.save(bangCap);
    }

    public void deleteById(Long id) {
        bangCapRepository.deleteById(id);
    }
}
