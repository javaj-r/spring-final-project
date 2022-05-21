package com.javid.spring.last.project.phase3.dto;

import com.javid.spring.last.project.phase3.dto.base.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.javid.spring.last.project.phase3.util.Validator.Message.BLANK_NAME;
import static com.javid.spring.last.project.phase3.util.Validator.Message.NAME_SIZE;

/**
 * @author javid
 * Created on 5/9/2022
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class WorkgroupDto extends BaseDto {

    private Long id;

    @Size(min = 1, max = 200, message = NAME_SIZE)
    @NotBlank(message = BLANK_NAME)
    private String name;
}
