package com.javid.spring.last.project.phase3.dto;

import com.javid.spring.last.project.phase3.dto.base.UserDto;
import com.javid.spring.last.project.phase3.util.AdvanceInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;

/**
 * @author javid
 * Created on 5/9/2022
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerDto extends UserDto {

    @Digits(integer = 15, fraction = 2, groups = AdvanceInfo.class)
    private BigDecimal credit;
}
