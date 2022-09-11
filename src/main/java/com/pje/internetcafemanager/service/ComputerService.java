package com.pje.internetcafemanager.service;

import com.pje.internetcafemanager.repository.ComputerRepository;
import com.pje.internetcafemanager.repository.ComputerUsageDetailRepository;
import com.pje.internetcafemanager.repository.PartReplacementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComputerService {
    private final ComputerRepository computerRepository;
    private final PartReplacementRepository partReplacementRepository;
    private final ComputerUsageDetailRepository computerUsageDetailRepository;


}
