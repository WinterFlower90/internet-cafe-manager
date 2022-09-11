package com.pje.internetcafemanager.repository;

import com.pje.internetcafemanager.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findAllByDateOrderGreaterThanEqualAndDateOrderLessThanEqualOrderByIdDesc(LocalDateTime dateStart, LocalDateTime dateEnd);
}
