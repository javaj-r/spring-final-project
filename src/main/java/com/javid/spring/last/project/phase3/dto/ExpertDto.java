package com.javid.spring.last.project.phase3.dto;

import com.javid.spring.last.project.phase3.dto.base.UserDto;
import com.javid.spring.last.project.phase3.model.enums.UserStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

import static com.javid.spring.last.project.phase3.util.Validator.Message.IMAGE_MAX_KB;
import static com.javid.spring.last.project.phase3.util.Validator.Message.NULL_IMAGE;

/**
 * @author javid
 * Created on 5/9/2022
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class ExpertDto extends UserDto {

    @NotNull(message = NULL_IMAGE)
    @Size(max = 300 * 1024, message = IMAGE_MAX_KB)
    private Byte[] expertImage;

    private UserStatus expertStatus;

    @Digits(integer = 15, fraction = 2)
    private BigDecimal credit;
}