package com.pje.internetcafemanager.model.computer;

import com.pje.internetcafemanager.enums.ComputerPartType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PartReplacementRequest {
    @ApiModelProperty(notes = "좌석 시퀀스", required = true)
    @NotNull
    private Long computerId;

    @ApiModelProperty(notes = "부품 타입", required = true)
    @Enumerated(value = EnumType.STRING)
    private ComputerPartType computerPartType;

    @ApiModelProperty(notes = "부품 모델명", required = true)
    @NotNull
    @Length(max = 20)
    private String partModelName;
}
