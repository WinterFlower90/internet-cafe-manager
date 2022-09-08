package com.pje.internetcafemanager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Order {
    REGISTER("주문이 접수되었습니다."),
    COOKING("주문하신 음식이 조리중입니다."),
    COOKED("주문하신 음식이 완성되었습니다.");

    private final String message;
}
