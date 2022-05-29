package com.javid.sfp.mapper;

import com.javid.sfp.dto.AdminDto;
import com.javid.sfp.mapper.base.BaseMapper;
import com.javid.sfp.model.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author javid
 * Created on 5/9/2022
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdminMapper extends BaseMapper<Admin, AdminDto> {

    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);
}
