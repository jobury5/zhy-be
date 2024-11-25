package com.zhy.repository.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhy.dataobject.AliAttachDO;
import com.zhy.dataobject.SysAttachTypeDO;
import com.zhy.domain.entity.Attach;
import com.zhy.domain.entity.AttachType;
import com.zhy.mapper.AliAttachMapper;
import com.zhy.mapper.SysAttachTypeMapper;
import com.zhy.mapstruct.AttachConverter;
import com.zhy.mapstruct.AttachTypeConverter;
import com.zhy.repository.AttachRepository;
import com.zhy.types.Id;
import com.zhy.types.UserId;
import com.zhy.types.aliyun.OssFileName;
import com.zhy.types.file.FileContentType;
import com.zhy.types.file.FileExtension;
import com.zhy.types.file.FileName;
import com.zhy.types.file.FileSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author: jobury
 * @Date: 2024/9/21 10:36
 */
@Repository
public class AttachRepositoryImpl implements AttachRepository {

    @Autowired
    private AliAttachMapper aliAttachMapper;

    @Autowired
    private SysAttachTypeMapper sysAttachTypeMapper;

    @Autowired
    private AttachConverter attachConverter;

    @Autowired
    private AttachTypeConverter attachTypeConverter;

    @Override
    public AttachType findAttachType(Id typeId) {
        SysAttachTypeDO sysAttachTypeDO = sysAttachTypeMapper.selectById(typeId.getId());
        return attachTypeConverter.toEntity(sysAttachTypeDO);
    }

    @Override
    public Attach find(Id attachId) {
        AliAttachDO aliAttachDO = aliAttachMapper.selectById(attachId.getId());
        Attach attach = attachConverter.toEntity(aliAttachDO);
        AttachType attachType = findAttachType(new Id(aliAttachDO.getTypeId()));
        attach.setAttachType(attachType);
        return attach;
    }

    @Override
    public Attach find(Id typeId, OssFileName fileName) {
        LambdaQueryWrapper<AliAttachDO> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(AliAttachDO::getTypeId, typeId.getId());
        queryWrapper.eq(AliAttachDO::getFileName, fileName.getFileName());
        AliAttachDO aliAttachDO = aliAttachMapper.selectOne(queryWrapper);
        Attach attach = attachConverter.toEntity(aliAttachDO);
        AttachType attachType = findAttachType(new Id(aliAttachDO.getTypeId()));
        attach.setAttachType(attachType);
        return attach;
    }

    @Override
    public Attach save(Id typeId, OssFileName fileName, FileSize size, FileName originalFileName, FileContentType contentType, FileExtension extension, String md5, UserId userId) {
        AliAttachDO aliAttachDO = new AliAttachDO();
        aliAttachDO.setTypeId(typeId.getId());
        aliAttachDO.setFileName(fileName.getFileName());
        aliAttachDO.setFileSize(size.getSize());
        aliAttachDO.setOriginalFileName(originalFileName.getName());
        aliAttachDO.setContentType(contentType.getContentType());
        aliAttachDO.setFileExtension(extension.getExtension());
        aliAttachDO.setMd5(md5);
        aliAttachDO.setCreateBy(userId.getUserId());
        aliAttachDO.setGmtCreate(LocalDateTimeUtil.now());
        aliAttachMapper.insert(aliAttachDO);
        return attachConverter.toEntity(aliAttachDO);
    }


}
