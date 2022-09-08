package com.pje.internetcafemanager.repository;

import com.pje.internetcafemanager.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
