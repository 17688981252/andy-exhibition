package com.zel.business.domain.dto;

import com.zel.business.domain.BusiExhibition;
import lombok.Data;

import java.util.Date;
@Data
public class BusiReturnNoticeOutDto {

    /**
     * 退还ID
     */
    private Long returnId;

    /**
     *退还单号
     */
    private String returnNumber;

    /**
     * 展会id
     */
    private Long exhibitionId;

    /**
     * 收货ID
     */
    private Long receiveId;

    /**
     *收货单号
     */
    private String receiveNumber;

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
     */
    private Integer status;

    /**
     * 物流状态（通知）
     */
    private String returnStatus;

    /**
     * 发货人
     */
    private String returnBy;

    /**
     * 发货时间
     */
    private Date returnTime;

    /**
     * 收货人
     */
    private Long receiveBy;

    /**
     * 收货时间
     */
    private Date receiveTime;

    /**
     * 收货地址
     */
    private String receiveAddress;

    /**
     * 收货部门ID
     */
    private Long  deptId;

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

    /*办展方*/
    private String organizer;

    /*办展地址*/
    private String address;



    /*收货部门*/
    private String deptName;

    /*收货人*/
    private String receiveName;

    /*发货人*/
    private String returnName;

    /*创建人*/
    private String createName;

    /*签收单号*/
    private String returnReceiveNumber;





}
