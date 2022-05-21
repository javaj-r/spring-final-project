package com.javid.spring.last.project.phase3.mapper;

import com.javid.spring.last.project.phase3.dto.AdminDto;
import com.javid.spring.last.project.phase3.mapper.base.BaseMapper;
import com.javid.spring.last.project.phase3.model.Admin;
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
