package com.javid.sfp.dto;

import com.javid.sfp.dto.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;
import java.math.BigDecimal;

import static com.javid.sfp.util.Validator.Message.*;

/**
 * @author javid
 * Created on 5/9/2022
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class WorkDto extends BaseDto {

    private Long id;

    @Size(min = 1, max = 200, message = NAME_SIZE)
    @NotBlank(message = BLANK_NAME)
    private String name;

    @Digits(integer = 15, fraction = 2)
    @DecimalMin(value = "0.0", message = NEGATIVE_PRICE)
    @NotNull(message = NULL_PRICE)
    private BigDecimal basePrice;

    @Size(max = 1000, message = DESCRIPTION_SIZE)
    private String description;

    @NotNull(message = NULL_WORKGROUP)
    private Long workgroupId;
}
