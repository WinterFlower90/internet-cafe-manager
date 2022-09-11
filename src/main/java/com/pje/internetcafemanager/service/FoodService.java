package com.pje.internetcafemanager.service;

import com.pje.internetcafemanager.entity.Computer;
import com.pje.internetcafemanager.entity.Food;
import com.pje.internetcafemanager.entity.OrderDetail;
import com.pje.internetcafemanager.exception.CMissingDataException;
import com.pje.internetcafemanager.model.ListResult;
import com.pje.internetcafemanager.model.food.FoodRequest;
import com.pje.internetcafemanager.model.food.OrderDetailItem;
import com.pje.internetcafemanager.model.food.OrderDetailRequest;
import com.pje.internetcafemanager.model.food.OrderStateRequest;
import com.pje.internetcafemanager.repository.ComputerRepository;
import com.pje.internetcafemanager.repository.FoodRepository;
import com.pje.internetcafemanager.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final ComputerRepository computerRepository;
    private final OrderDetailRepository orderDetailRepository;

    public void setFood(FoodRequest request) {
        Food food = new Food.FoodBuilder(request).build();
        foodRepository.save(food);
    }

    public void setOrderDetail(Computer computer, Food food, OrderDetailRequest request) {
        OrderDetail orderDetail = new OrderDetail.OrderDetailBuilder(computer, food, request).build();
        orderDetailRepository.save(orderDetail);
    }

    public ListResult<OrderDetailItem> getOrderDetails(LocalDate dateStart, LocalDate dateEnd) {
        LocalDateTime dateStartTime = LocalDateTime.of(
                dateStart.getYear(),
                dateStart.getMonthValue(),
                dateStart.getDayOfMonth(),
                0,0,0
        );
        LocalDateTime dateEndTime = LocalDateTime.of(
                dateEnd.getYear(),
                dateEnd.getMonthValue(),
                dateEnd.getDayOfMonth(),
                23,59,59
        );

        List<OrderDetail> orderDetails = orderDetailRepository.findAllByDateOrderGreaterThanEqualAndDateOrderLessThanEqualOrderByIdDesc(dateStartTime, dateEndTime);

        List<OrderDetailItem> result = new LinkedList<>();
        orderDetails.forEach(orderDetail -> {
            OrderDetailItem addItem = new OrderDetailItem.OrderDetailItemBuilder(orderDetail).build();
            result.add(addItem);
        });

        return ListConvertService.settingResult(result);
    }

    private void putOrderState(long orderDetailId, OrderStateRequest request) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId).orElseThrow(CMissingDataException::new);
        orderDetail.putOrderState(request);
        orderDetailRepository.save(orderDetail);
    }
}
