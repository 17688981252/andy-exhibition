package com.zel.business.domain;

import lombok.Data;

import java.util.Date;

/**
 * 展会记录 busi_eshibition_record
 *
 * @author andy 20210310
 */
@Data
public class BusiExhibitionRecord {

    /**
     * 展会记录ID
     */
    private Long exhibitionRecordId;

    /**
     * 展会ID
     */
    private Long exhibitionId;

    /**
     * 事件
     */
    private String event;

    /**
     * 附件
     */
    private String attach;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;
}
