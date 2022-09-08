package com.pje.internetcafemanager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ComputerPartType {
    CPU ("CPU"),
    GRAPHIC_CARD ("그래픽카드"),
    RAM ("램")
    ;

    private final String name;
}
