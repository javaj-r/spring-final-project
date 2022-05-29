package com.javid.sfp.mapper;

import com.javid.sfp.dto.ExpertDto;
import com.javid.sfp.mapper.base.BaseMapper;
import com.javid.sfp.model.Expert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author javid
 * Created on 5/10/2022
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpertMapper extends BaseMapper<Expert, ExpertDto> {

    ExpertMapper INSTANCE = Mappers.getMapper(ExpertMapper.class);
}
