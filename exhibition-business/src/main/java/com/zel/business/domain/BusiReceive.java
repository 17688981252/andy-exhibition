package com.zel.business.domain;

import lombok.Data;

import java.util.Date;

/**
 * busi_receive
 */
@Data
public class BusiReceive {


    /**
     * 收货ID
     */
    private Long receiveId;

    /**
     *收货单号
     */
    private String receiveNumber;

    /**
     * 发货ID
     */
    private Long sendId;

    /**
     *发货单号
     */
    private String sendNumber;

    /**
     * 展会id
     */
    private Long exhibitionId;

    /**
     * 物流名称
     */
    private String logisticsName;

    /**
     * 物流单号
     */
    private String logisticsNumber;

    /**
     * 状态
     * （1：未收货  2：已收货）
     */
    private Integer status;

    /**
     * 发货人
     */
    private String sendBy;

    /**
     * 发货时间
     */
    private Date sendTime;

    /**
     * 收货人
     */
    private Long receiveBy;

    /**
     * 收货时间
     */
    private Date receiveTime;

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
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标识
     * 1：正常  2：删除
     */
    private Integer del;

    /**
     * 备注
     */
    private String remark;

    /**
     * 展会信息
     */
    private BusiExhibition busiExhibitions;

    /*展会名称*/
    private String exhibitionName;

    /*办展地点*/
    private String address;

    /*办展方*/
    private String organizer;

    /*发货人*/
    private String sendName;

    /*创建人*/
    private String createName;

    private String receiveName;


}
