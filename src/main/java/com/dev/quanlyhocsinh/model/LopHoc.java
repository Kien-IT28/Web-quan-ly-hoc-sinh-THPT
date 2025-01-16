package com.dev.quanlyhocsinh.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "lophoc")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class LopHoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Min(value = 0, message = "Sĩ số không được nhỏ hơn 0!")
    @Max(value = 40, message = "Sĩ số không được vượt quá 40 học sinh!")
    Integer siSo;

    @NotBlank(message = "Tên lớp không được để trống!")
    @Size(max = 10, message = "Tên lớp không được dài hơn 10 ký tự!")
    String tenLop;

    @NotBlank(message = "Tên GVCN không được để trống!")
    @Size(max = 50, message = "Tên giáo viên chủ nhiệm không được dài hơn 50 ký tự!")
    String tenGVCN;

    @NotBlank(message = "Năm học không được để trống!")
    @Size(max = 9, message = "Năm học phải có định dạng như '2023-2024'!")
    String namHoc;

    @OneToMany(mappedBy = "lopHoc", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "lopHoc")
    Set<HocSinh> hocSinhs;
}

