package com.javid.sfp.mapper;

import com.javid.sfp.dto.WorkDto;
import com.javid.sfp.mapper.base.BaseMapper;
import com.javid.sfp.model.Work;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author javid
 * Created on 5/10/2022
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WorkMapper extends BaseMapper<Work, WorkDto> {

    WorkMapper INSTANCE = Mappers.getMapper(WorkMapper.class);
}
