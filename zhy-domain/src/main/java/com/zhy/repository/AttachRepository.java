package com.zhy.repository;

import com.zhy.domain.entity.Attach;
import com.zhy.domain.entity.AttachType;
import com.zhy.types.Id;
import com.zhy.types.UserId;
import com.zhy.types.aliyun.OssFileName;
import com.zhy.types.file.FileContentType;
import com.zhy.types.file.FileExtension;
import com.zhy.types.file.FileName;
import com.zhy.types.file.FileSize;

public interface AttachRepository {

    AttachType findAttachType(Id typeId);

    Attach find(Id attachId);

    Attach find(Id typeId, OssFileName fileName);

    Attach save(Id typeId, OssFileName fileName, FileSize size, FileName originalFileName, FileContentType contentType, FileExtension extension, String md5, UserId userId);


}
