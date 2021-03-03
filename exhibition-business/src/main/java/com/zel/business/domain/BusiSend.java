package com.zel.business.domain;

import com.zel.system.domain.SysDept;
import com.zel.system.domain.SysUser;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 发货信息 busi_send
 * @author andy
 */
@Data
public class BusiSend {

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
     * （1：保存  2：已发货 3：已收货）
     */
    private Integer status;

    /**
     * 发货人
     */
    private Long sendBy;

    /**
     * 发货时间
     */
    private Date sendTime;

    /**
     * 收货人
     */
    private Long receiveBy;

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
    private Long updateBy;

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

    /*收货部门*/
    private String deptName;

    /*收货人*/
    private String receiveName;

    /*发货人*/
    private String sendName;

    /*创建人*/
    private String createName;

    /*开始时间*/
    private String startTime;

   /* 结束时间*/
    private String endTime;

   /* 收货单号*/
    private String receiveNumber;

    /*物料ID及数量*/
    private List<Map> listMap;

    /*发货数量*/
    private Integer sendQuantity;

    /*收货数量*/
    private Integer receiveQuantity;

    /*物料ID*/
    private Long materialId;
}
