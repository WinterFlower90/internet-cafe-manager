package com.pje.internetcafemanager.model.computer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ComputerRequest {
    @ApiModelProperty(notes = "pc 좌석번호", required = true)
    @NotNull
    private Integer computerSeat; //pc 좌석번호

    @ApiModelProperty(notes = "pc IP 주소", required = true)
    @NotNull
    @Length(max = 20)
    private String computerIP; //pc IP 주소

    @ApiModelProperty(notes = "유저 ID", required = false)
    private String userId; //유저 ID


}
