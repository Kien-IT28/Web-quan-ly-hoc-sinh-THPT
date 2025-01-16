document.addEventListener("DOMContentLoaded", function() {
    const menuItems = document.querySelectorAll(".sidebar-nav > li");

    menuItems.forEach(item => {
        item.addEventListener("click", function(event) {
            // Ngăn chặn hành động mặc định nếu cần
            event.preventDefault();

            // Kiểm tra và ẩn tất cả các menu con khác
            const allSubmenus = document.querySelectorAll(".submenu");
            allSubmenus.forEach(submenu => submenu.classList.remove("active"));

            // Thêm lớp active cho menu con của mục được nhấn
            const submenu = this.querySelector(".submenu");
            if (submenu) {
                submenu.classList.toggle("active");
            }
        });
    });
});
