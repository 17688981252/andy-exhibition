package com.zel.business.domain.dto;
import com.zel.common.annotation.Excel;
import lombok.Data;
import java.util.Date;

/**
 * 撤展dto
 * @author andy
 */
@Data
public class BusiRevokeDto {

    /**
     * 退还ID
     */
    private Long revokeId;

    /**
     * 展会ID
     */
    private Long exhibitionId;

    /**
     * 展会名称
     */
    private String exhibitionName;


    /**
     * 展会状态
     * 1：保存 2：勘展 3：布展 4：撤展
     */
    @Excel(name = "展会状态", dictType = "busi_exhibition_status")
    private Integer status;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 办展地点
     */
    private String address;

    /**
     * 办展方
     */
    private String organizer;

    /**
     * 备注
     */
    private String remark;

}
