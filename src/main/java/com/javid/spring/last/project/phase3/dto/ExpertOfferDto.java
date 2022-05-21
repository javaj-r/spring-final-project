package com.javid.spring.last.project.phase3.dto;

import com.javid.spring.last.project.phase3.dto.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Time;

import static com.javid.spring.last.project.phase3.util.Validator.Message.*;

/**
 * @author javid
 * Created on 5/9/2022
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class ExpertOfferDto extends BaseDto {

    @NotNull(message = NULL_EXPERT)
    private Long expertId;

    @NotNull(message = NULL_ORDER)
    private Long orderId;

    @Digits(integer = 15, fraction = 2)
    @DecimalMin(value = "0.0", message = NEGATIVE_PRICE)
    private BigDecimal offeredPrice;

    @Min(value = 0, message = NEGATIVE_WORK_DURATION)
    private Long workDurationHour;

    private Time startTime;
}
