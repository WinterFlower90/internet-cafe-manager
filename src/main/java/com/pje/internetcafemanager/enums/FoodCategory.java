package com.pje.internetcafemanager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FoodCategory {
    RICE ("밥류"),
    NOODLE ("면류"),
    SNACK ("간식류"),
    BEVERAGE("음료")
    ;

    private final String name;
}
