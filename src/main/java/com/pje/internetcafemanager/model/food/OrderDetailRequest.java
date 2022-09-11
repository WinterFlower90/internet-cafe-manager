package com.pje.internetcafemanager.model.food;

import com.pje.internetcafemanager.enums.Order;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderDetailRequest {
    @ApiModelProperty(notes = "좌석 시퀀스", required = true)
    @NotNull
    private Long computerId;

    @ApiModelProperty(notes = "음식 시퀀스", required = true)
    @NotNull
    private Long foodId;

    @ApiModelProperty(notes = "주문 상태", required = true)
    @Enumerated(value = EnumType.STRING)
    private Order order; //주문 상태
}
