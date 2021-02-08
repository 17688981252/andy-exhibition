package com.zel.business.domain.dto;

import com.zel.business.domain.BusiExhibition;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * busi_receive
 */
public class BusiReceiveInDto {


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
     * 展会id
     */
    private Long exhibitionId;



    /**
     * 状态
     * （1：未收货  2：已收货）
     */
    private Integer status;



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
     * 收货信息
     */
    private Long[] ids;

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public Long getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Long receiveId) {
        this.receiveId = receiveId;
    }

    public String getReceiveNumber() {
        return receiveNumber;
    }

    public void setReceiveNumber(String receiveNumber) {
        this.receiveNumber = receiveNumber;
    }

    public Long getSendId() {
        return sendId;
    }

    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }

    public Long getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(Long exhibitionId) {
        this.exhibitionId = exhibitionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getReceiveBy() {
        return receiveBy;
    }

    public void setReceiveBy(Long receiveBy) {
        this.receiveBy = receiveBy;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
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
