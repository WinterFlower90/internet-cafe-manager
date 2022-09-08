package com.pje.internetcafemanager.entity;

import com.pje.internetcafemanager.interfaces.CommonModelBuilder;
import com.pje.internetcafemanager.model.computer.ComputerRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer computerSeat; //pc 좌석번호

    @Column(nullable = false, length = 20)
    private String computerIP; //pc IP 주소

    @Column(nullable = false)
    private Boolean isLogIn; //유저 로그인중? -> false
    @Column(nullable = false)
    private Boolean isLogOut; //유저 로그아웃중? -> 기본값 true

    @Column(nullable = true, length = 20)
    private String userId; //유저 ID

    @Column(nullable = false)
    private Boolean isMember; //회원? 비회원? -> 기본값 false. 로그인 하면 true.

    @Column(nullable = false)
    private LocalDateTime dateCreate;

    @Column(nullable = false)
    private LocalDateTime dateUpdate;

    private Computer(ComputerBuilder builder) {
        this.computerSeat = builder.computerSeat;
        this.computerIP = builder.computerIP;
        this.isLogIn = builder.isLogIn;
        this.isLogOut = builder.isLogOut;
        this.isMember = builder.isMember;
        this.dateCreate = builder.dateCreate;
        this.dateUpdate = builder.dateUpdate;
    }

    public static class ComputerBuilder implements CommonModelBuilder<Computer> {
        private final Integer computerSeat;
        private final String computerIP;
        private final Boolean isLogIn;
        private final Boolean isLogOut;
        private final Boolean isMember;
        private final LocalDateTime dateCreate;
        private final LocalDateTime dateUpdate;

        public ComputerBuilder(ComputerRequest request) {
            this.computerSeat = request.getComputerSeat();
            this.computerIP = request.getComputerIP();
            this.isLogIn = false;
            this.isLogOut = true;
            this.isMember = false;
            this.dateCreate = LocalDateTime.now();
            this.dateUpdate = LocalDateTime.now();
        }

        @Override
        public Computer build() {
            return new Computer(this);
        }
    }
}
