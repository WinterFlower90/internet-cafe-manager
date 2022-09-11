package com.pje.internetcafemanager.service;

import com.pje.internetcafemanager.entity.Computer;
import com.pje.internetcafemanager.entity.ComputerUsageDetail;
import com.pje.internetcafemanager.entity.Food;
import com.pje.internetcafemanager.entity.PartReplacement;
import com.pje.internetcafemanager.exception.CMissingDataException;
import com.pje.internetcafemanager.model.ListResult;
import com.pje.internetcafemanager.model.computer.ComputerListItem;
import com.pje.internetcafemanager.model.computer.ComputerRequest;
import com.pje.internetcafemanager.model.computer.PartReplacementRequest;
import com.pje.internetcafemanager.model.food.FoodRequest;
import com.pje.internetcafemanager.repository.ComputerRepository;
import com.pje.internetcafemanager.repository.ComputerUsageDetailRepository;
import com.pje.internetcafemanager.repository.PartReplacementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComputerService {
    private final ComputerRepository computerRepository;
    private final PartReplacementRepository partReplacementRepository;
    private final ComputerUsageDetailRepository computerUsageDetailRepository;

    public Computer getComputerData(long id) {
        return computerRepository.findById(id).orElseThrow(CMissingDataException::new);
    }

    public PartReplacement getPartReplacementData(long id) {
        return partReplacementRepository.findById(id).orElseThrow(CMissingDataException::new);
    }

    public ComputerUsageDetail getComputerUsageDetailData(long id) {
        return computerUsageDetailRepository.findById(id).orElseThrow(CMissingDataException::new);
    }

    public void setComputer(ComputerRequest request) {
        Computer computer = new Computer.ComputerBuilder(request).build();
        computerRepository.save(computer);
    }

    public void setPartReplacement(Computer computer, PartReplacementRequest request) {
        PartReplacement partReplacement = new PartReplacement.PartReplacementBuilder(computer, request).build();
        partReplacementRepository.save(partReplacement);
    }

    public void setComputerUsageDetail(Computer computer, Double usagePrice) {
        ComputerUsageDetail computerUsageDetail = new ComputerUsageDetail.ComputerUsageDetailBuilder(computer, usagePrice).build();
        computerUsageDetailRepository.save(computerUsageDetail);
    }

    public ListResult<ComputerListItem> getComputers() {
        List<Computer> computers = computerRepository.findAll();
        List<ComputerListItem> result = new LinkedList<>();

        computers.forEach(computer -> {
            ComputerListItem addItem = new ComputerListItem.ComputerListItemBuilder(computer).build();
            result.add(addItem);
        });
        return ListConvertService.settingResult(result);
    }

    public void getUsageTime(ComputerUsageDetail computerUsageDetail) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date1 = computerUsageDetail.getStartUsage();
        LocalDateTime date2 = computerUsageDetail.getEndUsage();
        int betweenTimes = (int) Duration.between(date1, date2).toMinutes();
    }

}
