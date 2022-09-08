package com.pje.internetcafemanager.entity;

import com.pje.internetcafemanager.enums.FoodCategory;
import com.pje.internetcafemanager.interfaces.CommonModelBuilder;
import com.pje.internetcafemanager.model.food.FoodRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String foodName; //메뉴명

    @Column(nullable = true)
    private String foodUrl; //메뉴 사진 url 주소

    @Column(nullable = false)
    private Double foodPrice; //메뉴 가격

    @Column(nullable = false)
    private Boolean isOnSale; //판매중 = 기본값 true

    @Column(nullable = false)
    private Boolean isSoldOut; //품절 = 기본값 false

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 10)
    private FoodCategory foodCategory;

    @Column(nullable = false)
    private LocalDateTime dateCreate;

    @Column(nullable = false)
    private LocalDateTime dateUpdate;

    private Food(FoodBuilder builder) {
        this.foodName = builder.foodName;
        this.foodUrl = builder.foodUrl;
        this.foodPrice = builder.foodPrice;
        this.isOnSale = builder.isOnSale;
        this.isSoldOut = builder.isSoldOut;
        this.foodCategory = builder.foodCategory;
        this.dateCreate = builder.dateCreate;
        this.dateUpdate = builder.dateUpdate;
    }

    public static class FoodBuilder implements CommonModelBuilder<Food> {
        private final String foodName; //메뉴명
        private final String foodUrl; //메뉴 사진 url 주소
        private final Double foodPrice; //메뉴 가격
        private final Boolean isOnSale; //판매중 = 기본값 true
        private final Boolean isSoldOut; //품절 = 기본값 false
        private final FoodCategory foodCategory;
        private final LocalDateTime dateCreate;
        private final LocalDateTime dateUpdate;

        public FoodBuilder(FoodRequest request) {
            this.foodName = request.getFoodName();
            this.foodUrl = request.getFoodUrl();
            this.foodPrice = request.getFoodPrice();
            this.isOnSale = request.getIsOnSale();
            this.isSoldOut = request.getIsSoldOut();
            this.foodCategory = request.getFoodCategory();
            this.dateCreate = LocalDateTime.now();
            this.dateUpdate = LocalDateTime.now();

        }

        @Override
        public Food build() {
            return new Food(this);
        }
    }
}
