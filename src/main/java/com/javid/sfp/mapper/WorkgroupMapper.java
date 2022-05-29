package com.javid.sfp.mapper;

import com.javid.sfp.dto.WorkgroupDto;
import com.javid.sfp.mapper.base.BaseMapper;
import com.javid.sfp.model.Workgroup;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author javid
 * Created on 5/10/2022
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WorkgroupMapper extends BaseMapper<Workgroup, WorkgroupDto> {

    WorkgroupMapper INSTANCE = Mappers.getMapper(WorkgroupMapper.class);
}
