package com.zel.business.domain;

import java.util.Date;
import java.util.List;

/**
 * 发货物料明细 busi_send_material_detail
 * @author andy
 */
public class BusiSendMaterialDetial {


    /**
     * 单据ID
     */
    private String sendMaterialDetailId;

    /**
     * 发货ID
     */
    private Long sendId;

    /**
     * 发货数量
     */
    private Integer sendQuantity;

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

    public BusiMaterial getBusiMaterial() {
        return busiMaterial;
    }

    public void setBusiMaterial(BusiMaterial busiMaterial) {
        this.busiMaterial = busiMaterial;
    }

    public String getSendMaterialDetailId() {
        return sendMaterialDetailId;
    }

    public void setSendMaterialDetailId(String sendMaterialDetailId) {
        this.sendMaterialDetailId = sendMaterialDetailId;
    }

    public Long getSendId() {
        return sendId;
    }

    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }

    public Integer getSendQuantity() {
        return sendQuantity;
    }

    public void setSendQuantity(Integer sendQuantity) {
        this.sendQuantity = sendQuantity;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatBy() {
        return creatBy;
    }

    public void setCreatBy(String creatBy) {
        this.creatBy = creatBy;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
