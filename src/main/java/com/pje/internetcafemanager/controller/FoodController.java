package com.pje.internetcafemanager.controller;

import com.pje.internetcafemanager.entity.Computer;
import com.pje.internetcafemanager.entity.Food;
import com.pje.internetcafemanager.model.CommonResult;
import com.pje.internetcafemanager.model.ListResult;
import com.pje.internetcafemanager.model.food.FoodRequest;
import com.pje.internetcafemanager.model.food.OrderDetailItem;
import com.pje.internetcafemanager.model.food.OrderDetailRequest;
import com.pje.internetcafemanager.service.ComputerService;
import com.pje.internetcafemanager.service.FoodService;
import com.pje.internetcafemanager.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Api(tags = "음식 및 음식 주문 내역 관리")
@RestController
@RequiredArgsConstructor
@RequestMapping("/V1/food")
public class FoodController {
    private final FoodService foodService;
    private final ComputerService computerService;

    @ApiOperation(value = "음식 메뉴 등록하기")
    @PostMapping("/food/new")
    public CommonResult setFood(@RequestBody @Valid FoodRequest request) {
        foodService.setFood(request);
        return ResponseService.getSuccessResult();
    }

    @ApiOperation(value = "음식 주문내역 등록하기")
    @PostMapping("/order/new")
    public CommonResult setOrderDetail(@RequestBody @Valid OrderDetailRequest request) {
        Computer computer = foodService.getComputerData(request.getComputerId());
        Food food = foodService.gerFoodData(request.getFoodId());
        foodService.setOrderDetail(computer, food, request);
        return ResponseService.getSuccessResult();
    }

    @ApiOperation(value = "주문 내역 기간별 리스트 가져오기")
    @GetMapping("/order/search")
    public ListResult<OrderDetailItem> getOrderDetails(
            @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(value = "dateStart") LocalDate dateStart,
            @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(value = "dateEnd") LocalDate dateEnd
    ) {
        return ResponseService.getListResult(foodService.getOrderDetails(dateStart, dateEnd), true);
    }
}
