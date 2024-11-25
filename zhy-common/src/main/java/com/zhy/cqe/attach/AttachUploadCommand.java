package com.zhy.cqe.attach;

import com.zhy.cqe.common.BasicParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: jobury
 * @Date: 2024/9/21 21:25
 */

@Data
public class AttachUploadCommand extends BasicParam {

    @Schema(description = "附件")
    @NotNull(message = "file can not be null")
    private MultipartFile file;

    @Schema(description = "类型id", example = "1005")
    @NotNull(message = "attach type id can not be null")
    private Long typeId;


}
