package com.javid.spring.last.project.phase3.mapper;

import com.javid.spring.last.project.phase3.dto.ExpertDto;
import com.javid.spring.last.project.phase3.mapper.base.BaseMapper;
import com.javid.spring.last.project.phase3.model.Expert;
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
