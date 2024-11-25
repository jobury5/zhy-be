package com.zhy.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhy.dataobject.BaseDO;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobury
 * @since 2024-08-21
 */
@Getter
@Setter
@TableName("crew_cert_req")
public class CrewCertReqDO extends BaseDO {

    /**
     * Crew cert id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * Crew id, link to crew_profile table
     */
    private Long crewId;

    /**
     * Certificate id, link to sys_cert table
     */
    private Long certId;

    /**
     * Certificate NO
     */
    private String certNumber;

    /**
     * Authority, referencing dict data (sys_dict_data)
     */
    private String authority;

    /**
     * Attachment file in personal information (point to crew_attach)
     */
    private Long attachId;

    /**
     * 1-apply,2-processing,3-pass,4-reject,5-cancel
     */
    private Integer status;
}
