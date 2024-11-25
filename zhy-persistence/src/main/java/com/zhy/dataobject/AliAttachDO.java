package com.zhy.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobury
 * @since 2024-09-20
 */
@Getter
@Setter
@TableName("ali_attach")
public class AliAttachDO extends BaseDO {

    /**
     * attach id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * sys_attach_type.id
     */
    private Long typeId;

    /**
     * file name in OSS,format(YYYYMMDD + 8 random letters + file extension)
     */
    private String fileName;

    /**
     * file_size(bytes)
     */
    private Long fileSize;

    /**
     * original file name
     */
    private String originalFileName;

    /**
     * content type
     */
    private String contentType;

    /**
     * file extension(jpg,pdf,docx etc.)
     */
    private String fileExtension;

    private String md5;
}
