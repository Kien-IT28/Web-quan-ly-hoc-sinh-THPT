package com.dev.quanlyhocsinh.controller;

import com.dev.quanlyhocsinh.model.HocSinh;
import com.dev.quanlyhocsinh.model.LopHoc;
import com.dev.quanlyhocsinh.repository.HocSinhRepositoty;
import com.dev.quanlyhocsinh.service.HocSinhService;
import com.dev.quanlyhocsinh.service.LopHocService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/qlhocsinh")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HocSinhController {

    final HocSinhService hocSinhService;
    final LopHocService lopHocService;
    final HocSinhRepositoty hocSinhRepositoty;

    @GetMapping("/list")
    public String listHocSinh(Model model) {
        List<HocSinh> hocSinhs = hocSinhService.getAllHocSinh();
        System.out.println("HocSinhList: " + hocSinhs);
        model.addAttribute("hocSinhList", hocSinhs);
        return "qlhocsinh/hocsinh";
    }

    @GetMapping("/list/{tenLop}")
    public String getDanhSachHocSinhTheoLopVaNamHoc(
            @PathVariable("tenLop") String tenLop,
            @RequestParam("namHoc") String namHoc,
            Model model) {
        List<HocSinh> hocSinhs = hocSinhService.listHocSinhTheoLopVaNamHoc(tenLop, namHoc);
        model.addAttribute("hocSinhs", hocSinhs);
        model.addAttribute("tenLop", tenLop);
        model.addAttribute("namHoc", namHoc);
        return "qlhocsinh/lop-hocsinh"; // Tên file HTML hiển thị danh sách
    }

    @PostMapping("/list/add")
    public String addHocSinh(
            @RequestParam("hoTen") String hoTen,
            @RequestParam("ngaySinh") Date ngaySinh,
            @RequestParam("namHoc") String namHoc,
            @RequestParam("tenLop") String tenLop,
            @RequestParam("gioiTinh") String gioiTinh,
            @RequestParam("queQuan") String quenQuan,
            RedirectAttributes redirectAttributes) {

        // Tìm lớp học dựa trên tên lớp và năm học
        LopHoc lopHoc = lopHocService.timKiemLopHoc(tenLop, namHoc);

        // Tạo đối tượng HocSinh mới
        HocSinh hocSinh = new HocSinh();
        hocSinh.setTenHocSinh(hoTen);
        hocSinh.setNgaySinh(ngaySinh);
        hocSinh.setGioiTinh(gioiTinh);
        hocSinh.setQueQuan(quenQuan);
        hocSinh.setLopHoc(lopHoc);

        // Lưu học sinh vào cơ sở dữ liệu
        hocSinhService.saveHocSinh(hocSinh);

        // Thêm thông báo thành công và chuyển hướng lại trang danh sách học sinh theo lớp và năm học
        redirectAttributes.addFlashAttribute("successMessage", "Học sinh đã được thêm thành công!");
        return "redirect:/qlhocsinh/list/" + tenLop + "?namHoc=" + namHoc; // Chuyển hướng lại trang danh sách học sinh của lớp và năm học hiện tại
    }

    @PostMapping("/list/edit")
    public String editHocSinh(@ModelAttribute HocSinh hocSinh, RedirectAttributes redirectAttributes,
                              @RequestParam("tenLop") String tenLop,
                              @RequestParam("namHoc") String namHoc) {
        try {
            // Gọi service để cập nhật thông tin học sinh
            hocSinhService.edit(hocSinh);

            // Thêm thông báo thành công
            redirectAttributes.addFlashAttribute("successMessage", "Chỉnh sửa học sinh thành công!");
        } catch (IllegalArgumentException e) {
            // Thêm thông báo lỗi nếu có exception
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        // Điều hướng lại trang danh sách học sinh với tenLop và namHoc trong URL
        return "redirect:/qlhocsinh/list/" + tenLop + "?namHoc=" + namHoc;
    }



    // Xóa học sinh
    @GetMapping("/list/delete/{mhs}")
    public String deleteHocSinh(@PathVariable("mhs") Long mhs, RedirectAttributes redirectAttributes,
                                @RequestParam("tenLop") String tenLop,
                                @RequestParam("namHoc") String namHoc) {
        try {
            // Xóa học sinh theo mhs
            hocSinhService.deleteHocSinhById(mhs);
            redirectAttributes.addFlashAttribute("errorMessage", "Xóa học sinh thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi xóa học sinh!");
        }

        // Trả về trang hiện tại (dựa trên tenLop và namHoc)
        return "redirect:/qlhocsinh/list/" + tenLop + "?namHoc=" + namHoc;
    }

}
