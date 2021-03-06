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
     * 展会进程(1:创展 2：勘展 3：发货  4：收货 5：布展 6：撤展 7：退还 8：签收)
     */
    private Integer status;

    /**
     * 是否有附件（0：没有， 1：有）
     */
    private Integer attached;

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
