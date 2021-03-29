package com.zel.business.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BusiExhibitionRecordDto {

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
     * 展会进程
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

    /*附件ID*/
    private Long exhibitionRecordAttachedId;

    /*图片路径*/
    private String pictureUrl;

    /*缩略图*/
    private String thumbImage;

    /*原文件名*/
    private String fileName;
}
