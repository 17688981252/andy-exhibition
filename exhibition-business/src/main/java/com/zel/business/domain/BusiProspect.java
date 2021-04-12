package com.zel.business.domain;

import lombok.Data;

import java.util.Date;

/**
 * 勘展 busi_prospect
 *
 * @author andy
 */
@Data
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
     * 展会名称
     */
    private String exhibitionName;

    /**
     * 图片名称
     */
    private String fileName;

    /**
     * 勘展图片
     */
    private String prospectUrl;

    /**
     * 缩略图
     */
    private String thumbImage;

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
