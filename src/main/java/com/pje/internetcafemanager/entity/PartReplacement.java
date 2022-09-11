package com.pje.internetcafemanager.entity;

import com.pje.internetcafemanager.enums.ComputerPartType;
import com.pje.internetcafemanager.interfaces.CommonModelBuilder;
import com.pje.internetcafemanager.model.computer.PartReplacementRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PartReplacement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "computerId", nullable = false)
    private Computer computer; //좌석번호

    @Column(nullable = false)
    private LocalDateTime dateReplacement; //부품 교체 일자

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ComputerPartType computerPartType; //부품 타입

    @Column(nullable = false, length = 20)
    private String partModelName; //부품 모델명

    private PartReplacement(PartReplacementBuilder builder) {
        this.computer = builder.computer;
        this.dateReplacement = builder.dateReplacement;
        this.computerPartType = builder.computerPartType;
        this.partModelName = builder.partModelName;
    }

    public static class PartReplacementBuilder implements CommonModelBuilder<PartReplacement> {
        private final Computer computer;
        private final LocalDateTime dateReplacement;
        private final ComputerPartType computerPartType;
        private final String partModelName;

        public PartReplacementBuilder(Computer computer, PartReplacementRequest request) {
            this.computer = computer;
            this.dateReplacement = LocalDateTime.now();
            this.computerPartType = request.getComputerPartType();
            this.partModelName = request.getPartModelName();
        }

        @Override
        public PartReplacement build() {
            return new PartReplacement(this);
        }
    }
}
