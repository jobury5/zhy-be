package com.zhy.application.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.zhy.application.AttachService;
import com.zhy.domain.entity.Attach;
import com.zhy.domain.entity.AttachType;
import com.zhy.domain.entity.OssObject;
import com.zhy.external.OssService;
import com.zhy.repository.AttachRepository;
import com.zhy.types.Id;
import com.zhy.types.Url;
import com.zhy.types.UserId;
import com.zhy.types.aliyun.OssFileName;
import com.zhy.types.aliyun.OssKey;
import com.zhy.types.aliyun.OssPath;
import com.zhy.types.file.FileName;
import com.zhy.util.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Date;

/**
 * @Author: jobury
 * @Date: 2024/9/21 20:25
 */

@Service
public class AttachServiceImpl implements AttachService {

    @Autowired
    private AttachRepository attachRepository;

    @Autowired
    private OssService ossService;

    @Override
    public Id uploadAttach(Id typeId, InputStream inputStream, FileName originalFileName, UserId userId) {
        Id newAttachId = null;
        AttachType attachType = attachRepository.findAttachType(typeId);
        if(attachType != null) {
            OssPath ossPathPrefix = attachType.getOssPath();
            // 格式化日期为 yyyyMM 格式
            Date currentDate = DateUtil.date();
            String year = DateUtil.format(currentDate, "yyyy");
            String month = DateUtil.format(currentDate, "MM");
            String date = DateUtil.format(currentDate, "yyyyMMdd");
            //生成8位随机数，只包含大小写字母和数字
            String newFileName = date + RandomGenerator.genRandomString(8);
            String newFileFullName = newFileName + "." + originalFileName.getFileExtension().getExtension();
            //生成完整的ossKey，path/yyyy/mm/yyyyMMdd{8 random string}.{fileExt}
            String key = StrUtil.concat(true, ossPathPrefix.getPath(),
                    "/", year, "/", month, "/", newFileFullName);
            OssKey ossKey = new OssKey(key);
            OssObject uploadObject = ossService.upload(ossKey, inputStream);
            //插入ali_attach表
            if (uploadObject != null) {
                Attach newAttach = attachRepository.save(typeId, new OssFileName(newFileFullName), uploadObject.getSize(), originalFileName, uploadObject.getContentType(), originalFileName.getFileExtension(), uploadObject.getMd5(), userId);
                if (newAttach != null) {
                    newAttachId = newAttach.getAttachId();
                }
            }
        }
        return newAttachId;
    }

    @Override
    public Url getUrlByAttachId(Id attachId) {
        Url url = null;
        Attach attach = attachRepository.find(attachId);
        if(attach != null){
            OssKey ossKey = attach.getOssKey();
            url = ossService.getSignedUrl(ossKey);
        }
        return url;
    }

    @Override
    public Url getUrlByTypeAndFileName(Id typeId, OssFileName fileName) {
        Url url = null;
        Attach attach = attachRepository.find(typeId, fileName);
        if(attach != null){
            OssKey ossKey = attach.getOssKey();
            url = ossService.getSignedUrl(ossKey);
        }
        return url;
    }


}
