package com.dev.quanlyhocsinh.dto;


import lombok.Data;

@Data
public class ChucVuDTO {
    private Long id;
    private String tenChucVu;
    private String moTa;
    private int soLuongNhanVien;

    public ChucVuDTO(Long id, String moTa, String tenChucVu,int soLuongNhanVien ) {
        this.soLuongNhanVien = soLuongNhanVien;
        this.moTa = moTa;
        this.tenChucVu = tenChucVu;
        this.id = id;
    }
}
