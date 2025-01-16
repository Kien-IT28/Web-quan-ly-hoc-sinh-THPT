package com.dev.quanlyhocsinh.service;

import com.dev.quanlyhocsinh.model.TrinhDo;
import com.dev.quanlyhocsinh.exception.ResourceNotFoundException;
import com.dev.quanlyhocsinh.repository.TrinhDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrinhDoService {

    @Autowired
    private TrinhDoRepository trinhDoRepository;

    public List<TrinhDo> getAllTrinhDos() {
        return trinhDoRepository.findAll();
    }

    public TrinhDo getTrinhDoById(Long id) {
        return trinhDoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TrinhDo not found with id: " + id));
    }

    public TrinhDo createTrinhDo(TrinhDo trinhDo) {
        return trinhDoRepository.save(trinhDo);
    }

    public TrinhDo updateTrinhDo(Long id, TrinhDo trinhDoDetails) {
        TrinhDo trinhDo = getTrinhDoById(id);
        trinhDo.setTenTD(trinhDoDetails.getTenTD());
        trinhDo.setMoTa(trinhDoDetails.getMoTa());
        return trinhDoRepository.save(trinhDo);
    }

    public void deleteTrinhDo(Long id) {
        TrinhDo trinhDo = getTrinhDoById(id);
        trinhDoRepository.delete(trinhDo);
    }
}
