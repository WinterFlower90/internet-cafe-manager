package com.pje.internetcafemanager.entity;

import com.pje.internetcafemanager.enums.Order;
import com.pje.internetcafemanager.interfaces.CommonModelBuilder;
import com.pje.internetcafemanager.model.food.OrderDetailRequest;
import com.pje.internetcafemanager.model.food.OrderStateRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "computerId", nullable = false)
    private Computer computer; //좌석번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foodId", nullable = false)
    private Food food; //음식번호

    @Column(nullable = false)
    private LocalDateTime dateOrder; //주문 일자

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Order order; //주문 상태

    @Column(nullable = false)
    private LocalDateTime dateUpdate;

    public void putOrderState(OrderStateRequest request) {
        this.order = request.getOrder();
        this.dateUpdate = LocalDateTime.now();
    }

    private OrderDetail (OrderDetailBuilder builder) {
        this.computer = builder.computer;
        this.food = builder.food;
        this.dateOrder = builder.dateOrder;
        this.order = builder.order;
        this.dateUpdate = builder.dateUpdate;
    }

    public static class OrderDetailBuilder implements CommonModelBuilder<OrderDetail> {
        private final Computer computer;
        private final Food food;
        private final LocalDateTime dateOrder;
        private final Order order;
        private final LocalDateTime dateUpdate;

        public OrderDetailBuilder(Computer computer, Food food, OrderDetailRequest request) {
            this.computer = computer;
            this.food = food;
            this.dateOrder = LocalDateTime.now();
            this.order = request.getOrder();
            this.dateUpdate = LocalDateTime.now();
        }

        @Override
        public OrderDetail build() {
            return new OrderDetail(this);
        }
    }
}
