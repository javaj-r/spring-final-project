package com.javid.spring.last.project.phase3.dto;

import com.javid.spring.last.project.phase3.dto.base.UserDto;
import com.javid.spring.last.project.phase3.model.enums.UserStatus;
import com.javid.spring.last.project.phase3.util.AdvanceInfo;
import com.javid.spring.last.project.phase3.util.ValidFile;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static com.javid.spring.last.project.phase3.util.Validator.Message.IMAGE_SIZE_TYPE;
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

    private Byte[] expertImage;

    private UserStatus expertStatus;

    @Digits(integer = 15, fraction = 2, groups = AdvanceInfo.class)
    private BigDecimal credit;

    @NotNull(message = NULL_IMAGE)
    @ValidFile(max = 300 * 1024, fileType = {"image/jpeg", "image/jpg"},
            message = IMAGE_SIZE_TYPE, groups = AdvanceInfo.class)
    private MultipartFile image;
}
