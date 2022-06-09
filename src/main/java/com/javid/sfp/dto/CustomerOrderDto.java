package com.javid.sfp.dto;

import com.javid.sfp.dto.base.BaseDto;
import com.javid.sfp.model.enums.OrderStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

import static com.javid.sfp.util.validators.Constant.Message.*;

/**
 * @author javid
 * Created on 5/9/2022
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class CustomerOrderDto extends BaseDto {

    private Long id;

    @Digits(integer = 15, fraction = 2)
    @DecimalMin(value = "0.0", message = NEGATIVE_PRICE)
    private BigDecimal proposedPrice;

    @Size(max = 10000, message = DESCRIPTION_SIZE)
    private String description;

    private OrderStatus orderStatus;

    private Date workDate;

    private Time workTime;

    @Size(max = 500, message = ADDRESS_SIZE)
    private String address;

    @Size(max = 10000, message = COMMENT_SIZE)
    private String customerComment;

    @Min(value = -10, message = SCORE_MIN)
    @Max(value = 10, message = SCORE_MAX)
    private Byte workScore;

    @NotNull
    private Long customerId;

    @NotNull
    private Long workId;

    private Long selectedExpertId;
}
