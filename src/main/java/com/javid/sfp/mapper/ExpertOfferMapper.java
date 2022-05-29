package com.javid.sfp.mapper;

import com.javid.sfp.dto.ExpertOfferDto;
import com.javid.sfp.mapper.base.BaseMapper;
import com.javid.sfp.model.ExpertOffer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author javid
 * Created on 5/10/2022
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpertOfferMapper extends BaseMapper<ExpertOffer, ExpertOfferDto> {

    ExpertOfferMapper INSTANCE = Mappers.getMapper(ExpertOfferMapper.class);
}
