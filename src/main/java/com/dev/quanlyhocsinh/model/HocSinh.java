package com.dev.quanlyhocsinh.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hocsinh")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HocSinh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    Long mhs; // Mã học sinh (duy nhất)

    @Column(name = "tenHocSinh", nullable = false, length = 50)
    String tenHocSinh; // Tên học sinh

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    Date ngaySinh; // Ngày sinh

    String gioiTinh;

    @Column(name = "queQuan", length = 100)
    String queQuan; // Quê quán

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lop_hoc_id", foreignKey = @ForeignKey(name = "fk_hocsinh_lophoc"))
    LopHoc lopHoc; // Quan hệ với lớp học

    @OneToMany(mappedBy = "hocSinh", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BangDiem> bangDiems = new ArrayList<>();
}
