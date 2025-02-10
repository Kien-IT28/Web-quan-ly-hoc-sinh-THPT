package com.dev.quanlyhocsinh.config;


import com.dev.quanlyhocsinh.service.AccountService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AccountService accountService;

    @Bean
    public UserDetailsService userDetailsService() {
        return accountService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var auth = new DaoAuthenticationProvider(); // Tạo nhà cung cấp xác thực.
        auth.setUserDetailsService(userDetailsService()); // Thiết lập dịch vụ chi tiết người dùng.
        auth.setPasswordEncoder(passwordEncoder()); // Thiết lập cơ chế mã hóa mật khẩu.
        return auth; // Trả về nhà cung cấp xác thực.
    }

    @Bean
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/assets/**","/css/**", "/js/**","/vendor/**","/oauth/**", "/error","/Anh/**",
                                "/images/**", "/index", "/gioithieu", "/tuyensinh", "/thongbao", "/forgot-password", "/reset-password").permitAll()
                        .requestMatchers("/account/phanquyen").hasAnyAuthority("ADMIN") // Chỉ cho phép ADMIN truy cập.
                        .requestMatchers("/phongban", "/chucvu", "/chuyenmon", "/congtac", "/trinhdo", "/nhanvien", "/nhomnhanvien", "/chitietnhomnhanvien",
                                "/thongke", "/hocsinh","/account-teacher")
                        .hasAnyAuthority("MANAGE","ADMIN") // Chỉ cho phép ADMIN truy cập.
                        .requestMatchers("/api/**").permitAll() // API mở cho mọi người dùng.
                        .anyRequest().authenticated() // Bất kỳ yêu cầu nào khác cần xác thực.
                )

                .formLogin(formLogin -> formLogin
                        .loginPage("/index")
                        .loginProcessingUrl("/login") // URL xử lý đăng nhập.
                        .defaultSuccessUrl("/home", true) // Trang sau đăng nhập thành công.
                        .failureUrl("/login?error") // Trang đăng nhập thất bại.
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?timeout=true") // Chuyển hướng đến trang đăng nhập khi hết hạn.
                        .deleteCookies("JSESSIONID", "hpu2") // Xóa cả cookie Remember Me.
                        .invalidateHttpSession(true) // Hủy phiên làm việc.
                        .clearAuthentication(true) // Xóa xác thực.
                        .permitAll()
                )
                .rememberMe(rememberMe -> rememberMe
                        .key("hpu2")
                        .rememberMeCookieName("hpu2") // Tên cookie cho Remember Me.
                        .tokenValiditySeconds(2 * 60) // Thời gian nhớ đăng nhập là 2 phút.
                        .userDetailsService(userDetailsService()) // Sử dụng UserDetailsService để kiểm tra thông tin người dùng.
                )
                .sessionManagement(session -> session
                        .invalidSessionUrl("/login?sessionExpired=true") // Chuyển hướng khi phiên làm việc hết hạn.
                )

                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage("/403") // Trang báo lỗi khi truy cập không được phép.
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .maximumSessions(1) // Giới hạn số phiên đăng nhập.
                        .expiredUrl("/login") // Trang khi phiên hết hạn.
                )
                .httpBasic(httpBasic -> httpBasic
                        .realmName("hpu2") // Tên miền cho xác thực cơ bản.
                )
                .build(); // Xây dựng và trả về chuỗi lọc bảo mật.
    }

}
