package com.zhy.mapstruct;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhy.domain.entity.common.ZhyPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PageConverter {

    @Mapping(target = "pageNo", source = "current")
    @Mapping(target = "pageSize", source = "size")
    @Mapping(target = "totalPage", source = "pages")
    @Mapping(target = "totalSize", source = "total")
    ZhyPage toEntity(Page page);


}
