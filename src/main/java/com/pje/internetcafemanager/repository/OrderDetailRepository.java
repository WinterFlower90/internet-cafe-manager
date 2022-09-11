package com.pje.internetcafemanager.repository;

import com.pje.internetcafemanager.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
