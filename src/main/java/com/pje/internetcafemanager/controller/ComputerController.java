package com.pje.internetcafemanager.controller;

import com.pje.internetcafemanager.entity.Computer;
import com.pje.internetcafemanager.model.CommonResult;
import com.pje.internetcafemanager.model.ListResult;
import com.pje.internetcafemanager.model.computer.ComputerListItem;
import com.pje.internetcafemanager.model.computer.ComputerRequest;
import com.pje.internetcafemanager.model.computer.PartReplacementRequest;
import com.pje.internetcafemanager.service.ComputerService;
import com.pje.internetcafemanager.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "컴퓨터 및 컴퓨터 사용 내역 관리")
@RestController
@RequiredArgsConstructor
@RequestMapping("/V1/computer")
public class ComputerController {
    private final ComputerService computerService;

    @ApiOperation(value = "컴퓨터 등록하기")
    @PostMapping("/computer/new")
    public CommonResult setComputer(@RequestBody @Valid ComputerRequest request) {
        computerService.setComputer(request);
        return ResponseService.getSuccessResult();
    }

    @ApiOperation(value = "컴퓨터 부품 교체 내역 등록하기")
    @PostMapping("/replacement/new")
    public CommonResult setPartReplacement(@RequestBody @Valid PartReplacementRequest request) {
        Computer computer = computerService.getComputerData(request.getComputerId());
        computerService.setPartReplacement(computer, request);
        return ResponseService.getSuccessResult();
    }

    @ApiOperation(value = "컴퓨터 사용 내역 등록하기")
    @PostMapping("/computer-usage-detail/new")
    public CommonResult setComputerUsageDetail(Computer computer, Double usagePrice) {
        computerService.setComputerUsageDetail(computer, usagePrice);
        return ResponseService.getSuccessResult();
    }

    @ApiOperation(value = "컴퓨터 리스트 가져오기")
    @GetMapping("/computer/all")
    public ListResult<ComputerListItem> getComputers() {
        return ResponseService.getListResult(computerService.getComputers(), true);
    }


}
