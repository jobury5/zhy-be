package com.zhy.application;

import com.zhy.types.Id;
import com.zhy.types.Url;
import com.zhy.types.UserId;
import com.zhy.types.aliyun.OssFileName;
import com.zhy.types.file.FileName;

import java.io.InputStream;

public interface AttachService {

    Id uploadAttach(Id typeId, InputStream inputStream, FileName originalFileName, UserId userId);

    Url getUrlByAttachId(Id attachId);

    Url getUrlByTypeAndFileName(Id typeId, OssFileName fileName);

}
