package com.dev.quanlyhocsinh.repository;

import com.dev.quanlyhocsinh.model.TrinhDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrinhDoRepository extends JpaRepository<TrinhDo, Long> {
}
