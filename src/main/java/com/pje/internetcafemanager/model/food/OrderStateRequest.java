package com.pje.internetcafemanager.model.food;

import com.pje.internetcafemanager.enums.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderStateRequest {

    private Order order;
}
