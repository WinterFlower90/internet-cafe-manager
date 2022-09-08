package com.pje.internetcafemanager.model.food;

import com.pje.internetcafemanager.enums.FoodCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FoodRequest {
    @ApiModelProperty(notes = "음식 메뉴명", required = true)
    @NotNull
    @Length(max = 20)
    private String foodName; //메뉴명

    @ApiModelProperty(notes = "음식 메뉴 url 주소", required = false)
    private String foodUrl; //메뉴 사진 url 주소

    @ApiModelProperty(notes = "음식 메뉴 가격", required = true)
    @NotNull
    private Double foodPrice; //메뉴 가격

    @ApiModelProperty(notes = "음식 판매 여부", required = true)
    @NotNull
    private Boolean isOnSale; //판매중 = 기본값 true

    @ApiModelProperty(notes = "음식 품절 여부", required = true)
    @NotNull
    private Boolean isSoldOut; //품절 = 기본값 false

    @ApiModelProperty(notes = "음식 메뉴 카테고리", required = true)
    @Enumerated(value = EnumType.STRING)
    private FoodCategory foodCategory;
}
