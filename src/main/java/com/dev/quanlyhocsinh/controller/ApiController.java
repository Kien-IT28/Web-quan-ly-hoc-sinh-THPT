package com.dev.quanlyhocsinh.controller;


import com.dev.quanlyhocsinh.dto.ChucVuDTO;
import com.dev.quanlyhocsinh.dto.PhongBanDTO;
import com.dev.quanlyhocsinh.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    //test
    @Autowired
    private PhongBanService phongBanService;

    @Autowired
    private ChucVuService chucVuService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private HocSinhService hocSinhService;

//    @GetMapping("/chucvu")
//    public List<ChucVuDTO> getAllChucVu(){
//        return chucVuService.findAllChucVu();
//    }

    @GetMapping("/totalaccount")
    public ResponseEntity<Long> getAccoutnsTotal() {
        long totalAccount = accountService.countAccounts();
        return ResponseEntity.ok(totalAccount);
    }


    @GetMapping("/totalhocsinh")
    public ResponseEntity<Long> getHocSinhCount() {
        long totalHocSinh = hocSinhService.countHocSinhs();
        return ResponseEntity.ok(totalHocSinh);
    }

}
