package com.dev.quanlyhocsinh.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChungChi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Tên chuyên môn không được trống")
    String tenChungChi;
}
