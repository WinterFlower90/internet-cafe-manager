package com.pje.internetcafemanager.entity;

import com.pje.internetcafemanager.interfaces.CommonModelBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ComputerUsageDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "computerId", nullable = false)
    private Computer computer; //좌석번호

    @Column(nullable = false)
    private LocalDateTime startUsage;

    @Column(nullable = true)
    private LocalDateTime endUsage;

    @Column(nullable = false)
    private Double usagePrice;

    private ComputerUsageDetail(ComputerUsageDetailBuilder builder) {
        this.computer = builder.computer;
        this.startUsage = builder.startUsage;
        this.usagePrice = builder.usagePrice;

    }

    public static class ComputerUsageDetailBuilder implements CommonModelBuilder<ComputerUsageDetail> {
        private final Computer computer;
        private final LocalDateTime startUsage;
        private final Double usagePrice;

        public ComputerUsageDetailBuilder(Computer computer, Double usagePrice) {
            this.computer = computer;
            this.startUsage = LocalDateTime.now();
            this.usagePrice = usagePrice;
        }

        @Override
        public ComputerUsageDetail build() {
            return new ComputerUsageDetail(this);
        }
    }



}
