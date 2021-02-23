package com.zel.business.domain;

import lombok.Data;

import java.util.Date;

/**
 * 发货物料明细 busi_return_material_detail
 * @author andy
 */
@Data
public class BusiReturnMaterialDetial {


    /**
     * 单据ID
     */
    private String returnMaterialDetailId;

    /**
     * 退货ID
     */
    private Long returnId;

    /**
     * 退货数量
     */
    private Integer returnQuantity;

    /**
     * 物料ID
     */
    private Long materialId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    private String creatBy;

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /*物料*/
    private BusiMaterial busiMaterial;

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
