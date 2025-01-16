package com.dev.quanlyhocsinh.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên quyền được không được trống")
    @Column(name = "name", length = 50, nullable = false)
    @Size(max = 50, message = "Tên quyền ngắn hơn  50 kí tự")
    private String name;

    @Size(max = 250, message = "Mô tả tối đa 250 kí tự")
    @Column(name = "description", length = 250)
    private String description;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Account> accounts = new HashSet<>();

    public @NotBlank(message = "Tên quyền được không được trống") @Size(max = 50, message = "Tên quyền ngắn hơn  50 kí tự") String getName() {
        return name;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return
                false;
        Role role = (Role) o;
        return getId() != null && Objects.equals(getId(), role.getId()); }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
