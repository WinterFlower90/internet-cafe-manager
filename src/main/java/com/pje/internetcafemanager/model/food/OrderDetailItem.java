package com.pje.internetcafemanager.model.food;

import com.pje.internetcafemanager.entity.OrderDetail;
import com.pje.internetcafemanager.interfaces.CommonModelBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDetailItem {
    private Long orderDetailId;

    private Long computerId;

    private Long foodId;

    private String orderInfo; //오더상태 : [카테고리] 음식이름

    private LocalDateTime dateOrder;

    private OrderDetailItem(OrderDetailItemBuilder builder) {
        this.orderDetailId = builder.orderDetailId;
        this.computerId = builder.computerId;
        this.foodId = builder.foodId;
        this.orderInfo = builder.orderInfo;
        this.dateOrder = builder.dateOrder;
    }

    public static class OrderDetailItemBuilder implements CommonModelBuilder<OrderDetailItem> {
        private final Long orderDetailId;
        private final Long computerId;
        private final Long foodId;
        private final String orderInfo; //오더상태 : [카테고리] 음식이름
        private final LocalDateTime dateOrder;

        public OrderDetailItemBuilder(OrderDetail detail) {
            this.orderDetailId = detail.getId();
            this.computerId = detail.getComputer().getId();
            this.foodId = detail.getFood().getId();
            this.orderInfo = detail.getOrder().getMessage() + " : [" + detail.getFood().getFoodCategory().getName() + "] " + detail.getFood().getFoodName();
            this.dateOrder = detail.getDateOrder();
        }

        @Override
        public OrderDetailItem build() {
            return new OrderDetailItem(this);
        }
    }
}
