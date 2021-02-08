package com.zel.business.domain;

import java.util.Date;

/**
 * 勘展 busi_prospect
 *
 * @author andy
 */
public class BusiProspect {

    private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * 勘展ID
     */
    private Long prospectId;

    /**
     * 展会ID
     */
    private Long exhibitionId;

    /**
     * 图片名称
     */
    private String fileName;

    /**
     * 勘展图片
     */
    private String prospectUrl;

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

    public Long getProspectId() {
        return prospectId;
    }

    public void setProspectId(Long prospectId) {
        this.prospectId = prospectId;
    }

    public Long getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(Long exhibitionId) {
        this.exhibitionId = exhibitionId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getProspectUrl() {
        return prospectUrl;
    }

    public void setProspectUrl(String prospectUrl) {
        this.prospectUrl = prospectUrl;
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

    public BusiProspect() {
        super();
    }

    public BusiProspect(Long exhibitionId, String fileName, String prospectUrl) {
        this.exhibitionId = exhibitionId;
        this.fileName = fileName;
        this.prospectUrl = prospectUrl;
    }

    @Override
    public String toString() {
        return "BusiProspect{" +
                "prospectId=" + prospectId +
                ", exhibitionId=" + exhibitionId +
                ", fileName='" + fileName + '\'' +
                ", prospectUrl='" + prospectUrl + '\'' +
                '}';
    }
}
