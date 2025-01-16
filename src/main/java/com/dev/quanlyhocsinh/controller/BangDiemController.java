package com.dev.quanlyhocsinh.controller;

import com.dev.quanlyhocsinh.model.BangDiem;
import com.dev.quanlyhocsinh.model.ChuyenMon;
import com.dev.quanlyhocsinh.model.HocSinh;
import com.dev.quanlyhocsinh.repository.BangDiemRepository;
import com.dev.quanlyhocsinh.service.BangDiemService;
import com.dev.quanlyhocsinh.service.ChuyenMonService;
import com.dev.quanlyhocsinh.service.HocSinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BangDiemController {

    @Autowired
    private BangDiemRepository bangDiemRepository;

    @Autowired
    private BangDiemService bangDiemService;

    @Autowired
    private ChuyenMonService chuyenMonService;

    @Autowired
    private HocSinhService hocSinhService;

    @GetMapping("/bangdiem/{id}")
    public String showBangDiem(
            @PathVariable("id") Long id,
            Model model) {
        // Lấy học sinh theo mã học sinh (id)
        HocSinh hocSinh = hocSinhService.findHocSinhById(id);
        if (hocSinh != null) {
            model.addAttribute("tenHocSinh", hocSinh.getTenHocSinh());  // Truyền tên học sinh
            model.addAttribute("mhs", hocSinh.getMhs());  // Truyền mã học sinh
        } else {
            model.addAttribute("tenHocSinh", "Không tìm thấy học sinh");
            model.addAttribute("mhs", id);  // Nếu không tìm thấy, vẫn hiển thị mã học sinh
        }

        // Lấy danh sách bảng điểm của học sinh theo mã học sinh (mhs)
        List<BangDiem> bangDiems = bangDiemService.ListBangDiemTheoID(id);

        // Lấy danh sách chuyên môn
        List<ChuyenMon> dsChuyenMon = chuyenMonService.getAllChuyenMons();

        // Truyền danh sách chuyên môn và bảng điểm vào mô hình
        model.addAttribute("dsChuyenMon", dsChuyenMon);
        model.addAttribute("bangDiems", bangDiems);

        return "qlhocsinh/diem";
    }

    @GetMapping("/all")
    public List<BangDiem> getAllBangDiem() {
        return bangDiemService.getAllBangDiem();
    }

    // In bang diem
    @GetMapping("/qlhocsinh/print-bangdiem")  // Đường dẫn trỏ đến trang in
    public String showPrintPage(Model model) {

        // Giả sử bạn đã có phương thức lấy bảng điểm từ service
        List<BangDiem> bangDiemList = bangDiemService.getAllBangDiem();

        // Đưa dữ liệu bảng điểm vào model
        model.addAttribute("bangDiemList", bangDiemList);

        // Trả về trang in bảng điểm (Thymeleaf template)
        return "/qlhocsinh/print-bangdiem";  // Trang này có thể hiển thị bảng điểm và chức năng in
    }


    @PostMapping("/bangdiem/add")
    public String addBangDiem(@RequestParam Long maHS, @ModelAttribute BangDiem bangDiem, RedirectAttributes redirectAttributes) {
        try {
            // Lấy học sinh theo mã học sinh
            HocSinh hocSinh = hocSinhService.findHocSinhById(maHS);
            if (hocSinh == null) {
                throw new IllegalArgumentException("Không tìm thấy học sinh với mã " + maHS);
            }

            // Gán học sinh vào bảng điểm
            bangDiem.setHocSinh(hocSinh);

            // Thêm bảng điểm vào cơ sở dữ liệu
            bangDiemService.addBangDiem(bangDiem);

            // Thêm thông báo thành công
            redirectAttributes.addFlashAttribute("successMessage", "Thêm bảng điểm thành công!");

            // Chuyển hướng về trang bảng điểm của học sinh tương ứng với mã học sinh (maHS)
            return "redirect:/bangdiem/" + maHS;
        } catch (Exception e) {
            // Thêm thông báo lỗi nếu có lỗi ngoài IllegalArgumentException
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra: " + e.getMessage());

            // Chuyển hướng về trang bảng điểm của học sinh với mã học sinh tương ứng
            return "redirect:/bangdiem/" + maHS; // Đảm bảo luôn quay lại trang bảng điểm của học sinh
        }
    }

    @PostMapping("/bangdiem/edit")
    public String editBangDiem(@ModelAttribute BangDiem bangDiem, RedirectAttributes redirectAttributes){
        try {
            bangDiemService.editBangDiem(bangDiem);
                redirectAttributes.addFlashAttribute("successMessage", "Chỉnh sửa bảng điểm thành công!");
                // Chuyển hướng về danh sách bảng điểm của học sinh
                return "redirect:/diem";
        } catch (IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/diem"; // Quay lại trang bảng điểm nếu có lỗi
        }
    }

    @GetMapping("/bangdiem/delete/{id}")
    public String deleteBangDiem(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            BangDiem bangDiem = bangDiemService.findById(id);
            if (bangDiem == null) {
                throw new IllegalArgumentException("Bảng điểm không tồn tại!");
            }

            bangDiemService.deleteById(id);
            redirectAttributes.addFlashAttribute("errorMessage", "Xóa bảng điểm thành công!");
            // Chuyển hướng về danh sách bảng điểm của học sinh
            return "redirect:/bangdiem/" + bangDiem.getHocSinh().getMhs();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra: " + e.getMessage());
            return "redirect:/diem"; // Chuyển hướng về trang chính nếu không có thông tin
        }
    }
}
