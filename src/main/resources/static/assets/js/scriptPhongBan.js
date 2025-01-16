
    document.addEventListener('DOMContentLoaded', function() {
        const editModal = document.getElementById('editModal');
        editModal.addEventListener('show.bs.modal', function (event) {
            const button = event.relatedTarget;
            const id = button.getAttribute('data-id');
            const tenNV = button.getAttribute('data-tenNV');
            const CCCD = button.getAttribute('data-CCCD');
            const ngaySinh = button.getAttribute('data-ngaySinh');
            const hoKhau = button.getAttribute('data-hoKhau');
            const SDT = button.getAttribute('data-SDT');
            const gioiTinh = button.getAttribute('data-gioiTinh');
            const loaiNhanVien = button.getAttribute('data-loaiNhanVien');
            const phongBan = button.getAttribute('data-phongBan');
            const bangCap = button.getAttribute('data-bangCap');
            const chucVu = button.getAttribute('data-chucVu');
            const chuyenMon = button.getAttribute('data-chuyenMon');
            const trangThai = button.getAttribute('data-trangThai');
            const trinhDo = button.getAttribute('data-trinhDo');

            editModal.querySelector('#editId').value = id;
            editModal.querySelector('#inputTenNVEdit').value = tenNV;
            editModal.querySelector('#inputCCCDEdit').value = CCCD;
            editModal.querySelector('#inputNgaySinhEdit').value = ngaySinh;
            editModal.querySelector('#inputHoKhauEdit').value = hoKhau;
            editModal.querySelector('#inputSDTEdit').value = SDT;
            editModal.querySelector('#inputGioiTinhEdit').value = gioiTinh;
            editModal.querySelector('#inputLoaiNVEdit').value = loaiNhanVien;
            editModal.querySelector('#inputPhongBanEdit').value = phongBan;
            editModal.querySelector('#inputBangCapEdit').value = bangCap;
            editModal.querySelector('#inputChucVuEdit').value = chucVu;
            editModal.querySelector('#inputChuyenMonEdit').value = chuyenMon;
            editModal.querySelector('#inputTrangThaiEdit').value = trangThai;
            editModal.querySelector('#inputTrinhDoEdit').value = trinhDo;
        });
    });
