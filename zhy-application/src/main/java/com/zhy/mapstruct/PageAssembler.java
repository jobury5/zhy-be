package com.zhy.mapstruct;

import com.zhy.domain.entity.common.ZhyPage;
import com.zhy.dto.ZhyPageDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PageAssembler {

    ZhyPageDTO toDTO(ZhyPage zhyPage);


}
