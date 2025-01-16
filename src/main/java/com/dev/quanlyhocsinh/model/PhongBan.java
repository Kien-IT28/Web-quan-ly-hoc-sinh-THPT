package com.dev.quanlyhocsinh.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Entity
@Setter
@Getter
//@RequiredArgsConstructor
//@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhongBan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Tên phòng ban không được trống")
    String tenPB;

    String moTa;

    Date ngayTao;
    Date ngaySua;
}
