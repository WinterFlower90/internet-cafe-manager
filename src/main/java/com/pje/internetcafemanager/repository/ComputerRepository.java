package com.pje.internetcafemanager.repository;

import com.pje.internetcafemanager.entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerRepository extends JpaRepository<Computer, Long> {
}
