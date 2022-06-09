package com.javid.sfp.dto.base;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

/**
 * @author javid
 * Created on 5/9/2022
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Accessors(chain = true)
public abstract class BaseDto {

    private Timestamp createTime;
    private Timestamp updateTime;
}
