package com.zel.business.domain;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 退还信息 busi_return
 *
 * @author andy
 */
@Data
public class BusiReturn {

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
     * 物流名称
     */
    private String logisticsName;

    /**
     * 物流单号
     */
    private String logisticsNumber;

    /**
     * 状态
     * （1：保存  2：已退货 3：已收货）
     */
    private Integer status;

    /**
     * 退还人
     */
    private Long returnBy;

    /**
     * 退还时间
     */
    private Date returnTime;

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

    /**
     * 展会名称
     */
    private String exhibitionName;

    /**
     * 展会地址
     */
    private String address;

    /**
     * 办展方
     */
    private String organizer;

    /**
     *收货部门
     */
    private String deptName;

    /**
     * 收货人
     */
    private String receiveName;

    /**
     * 退还人
     **/
    private String returnName;

    /*创建人*/
    private String createName;

    /*开始时间*/
    private String startTime;

   /* 结束时间*/
    private String endTime;

   /* 收货单号*/
    private String receiveNumber;

    /*传入参数*/
    private List<Map> listMap;


}
