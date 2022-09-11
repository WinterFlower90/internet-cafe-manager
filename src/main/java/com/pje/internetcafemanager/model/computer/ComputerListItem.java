package com.pje.internetcafemanager.model.computer;

import com.pje.internetcafemanager.entity.Computer;
import com.pje.internetcafemanager.interfaces.CommonModelBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ComputerListItem {
    private Long computerId;
    private String computerFullName;

    private ComputerListItem(ComputerListItemBuilder builder) {
        this.computerId = builder.computerId;
        this.computerFullName = builder.computerFullName;
    }

    public static class ComputerListItemBuilder implements CommonModelBuilder<ComputerListItem> {
        private final Long computerId;
        private final String computerFullName;

        public ComputerListItemBuilder(Computer computer) {
            this.computerId = computer.getId();
            this.computerFullName = "[" + computer.getComputerSeat() + "] " + computer.getComputerIP();
        }

        @Override
        public ComputerListItem build() {
            return new ComputerListItem(this);
        }
    }
}
