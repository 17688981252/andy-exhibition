package com.zel.business.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 撤展 busi_revoke
 *
 * @author andy
 */
@Data
public class BusiRevoke {



    /**
     * 撤展ID
     */
    private Long revokeId;

    /**
     * 展会ID
     */
    private Long exhibitionId;

    /**
     * 图片名称
     */
    private String fileName;

    /**
     * 撤展图片
     */
    private String revokeUrl;

    private List<String> revokeUrlList;

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

    public BusiRevoke() {
        super();
    }

    public BusiRevoke(Long exhibitionId, String fileName, String revokeUrl) {
        this.exhibitionId = exhibitionId;
        this.fileName = fileName;
        this.revokeUrl = revokeUrl;
    }

    @Override
    public String toString() {
        return "BusiRevoke" +
                "revokeId=" + revokeId +
                ", exhibitionId=" + exhibitionId +
                ", fileName='" + fileName + '\'' +
                ", revokeUrl='" + revokeUrl + '\'' +
                '}';
    }
}
