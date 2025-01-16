package com.dev.quanlyhocsinh.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "bangdiem")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BangDiem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "monHoc", nullable = false, length = 20)
    String monHoc; // Môn học

    @Column(name = "diem_TB_HKI", nullable = false)
    float diemTBHKI;

    @Column(name = "diem_TB_HKII", nullable = false)
    float diemTBHKII;

    @Column(name = "diem_CN", nullable = false)
    float diemCN; // Điểm trung bình cả năm

    // Phương thức tính điểm cả năm
    public void calculateDiemCN() {
        this.diemCN = (this.diemTBHKI + this.diemTBHKII) / 2;
    }

    @ManyToOne
    @JoinColumn(name = "hoc_sinh_id")
    private HocSinh hocSinh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chuyen_mon_id")
    private ChuyenMon chuyenMon; // Kiểu ChuyenMon chứ không phải String
}
