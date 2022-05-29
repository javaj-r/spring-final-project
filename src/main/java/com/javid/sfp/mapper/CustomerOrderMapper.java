package com.javid.sfp.mapper;

import com.javid.sfp.dto.CustomerOrderDto;
import com.javid.sfp.mapper.base.BaseMapper;
import com.javid.sfp.model.CustomerOrder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author javid
 * Created on 5/10/2022
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerOrderMapper extends BaseMapper<CustomerOrder, CustomerOrderDto> {

    CustomerOrderMapper INSTANCE = Mappers.getMapper(CustomerOrderMapper.class);
}
