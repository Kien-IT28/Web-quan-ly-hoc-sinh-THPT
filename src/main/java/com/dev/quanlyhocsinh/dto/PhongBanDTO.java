package com.dev.quanlyhocsinh.dto;

import lombok.Data;

@Data
public class PhongBanDTO {
    private Long id;
    private String tenPB;
    private String moTa;
    private int soLuongNhanVien;

    public PhongBanDTO(Long id, String tenPB, String moTa, int soLuongNhanVien) {
        this.id = id;
        this.tenPB = tenPB;
        this.moTa = moTa;
        this.soLuongNhanVien = soLuongNhanVien;
    }
}
