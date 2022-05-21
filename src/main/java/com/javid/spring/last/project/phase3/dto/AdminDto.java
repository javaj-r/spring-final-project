package com.javid.spring.last.project.phase3.dto;

import com.javid.spring.last.project.phase3.dto.base.UserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author javid
 * Created on 5/9/2022
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class AdminDto extends UserDto {
}
