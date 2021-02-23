package com.zel.business.domain;

import com.zel.common.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 物料表 busi_material
 *
 * @author andy
 */
public class BusiMaterial {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
//    @Excel(name = "物料ID", cellType = Excel.ColumnType.NUMERIC)
    private Long materialId;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String materialName;

    /** 岗物料代码 */
    @Excel(name = "物料代码")
    private String materialCode;

    /** 物料简称*/
    @Excel(name = "物料简称")
    private String nickName;

    @Excel(name = "规格参数")
    private String specifications;

    @Excel(name = "类型", dictType = "busi_material_type",readConverterExp = "1=塑料,2=金属,3=其他")
    private Integer type;

    @Excel(name = "计量单位", dictType = "busi_material_unit",readConverterExp = "1=件,2=公斤,3=吨")
//    readConverterExp 导入时对应转换对象
    private Integer unit;

    @Excel(name = "标包数量")
    private Long packageQuantity;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 创建时间
     */
//    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss",type =Excel.Type.EXPORT)

    private Date createTime;

    /**
     * 更新人
     */
    private Long updateBy;

//    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss",type = Excel.Type.EXPORT)
    private Date updateTime;

    /** 删除标识
     *  1：正常  2：删除
     * */
    private Integer del;

    @Excel(name = "备注")
    private String remark;

   /* 发货数量*/
    private Integer sendQuantity;

    /*退还数量*/
    private Integer returnQuantity;

    public Integer getReturnQuantity() {
        return returnQuantity;
    }

    public void setReturnQuantity(Integer returnQuantity) {
        this.returnQuantity = returnQuantity;
    }

    public Integer getSendQuantity() {
        return sendQuantity;
    }

    public void setSendQuantity(Integer sendQuantity) {
        this.sendQuantity = sendQuantity;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Long getPackageQuantity() {
        return packageQuantity;
    }

    public void setPackageQuantity(Long packageQuantity) {
        this.packageQuantity = packageQuantity;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
