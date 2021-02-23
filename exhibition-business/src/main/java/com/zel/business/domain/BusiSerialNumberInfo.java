package com.zel.business.domain;

import java.util.Date;

/**
 * busi_serial_number_info
 * @author andy
 */
public class BusiSerialNumberInfo {


    /**
     * id
     */
     private Long serialNumberInfoId;

    /**
     * 前缀
     */
     private String prefix;

    /**
     * 版本
     */
     private Integer ver;

    /**
     * 流水号
     */
     private Long serialNumber;
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

    public Long getSerialNumberInfoId() {
        return serialNumberInfoId;
    }

    public void setSerialNumberInfoId(Long serialNumberInfoId) {
        this.serialNumberInfoId = serialNumberInfoId;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Integer getVer() {
        return ver;
    }

    public void setVer(Integer ver) {
        this.ver = ver;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Long serialNumber) {
        this.serialNumber = serialNumber;
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
