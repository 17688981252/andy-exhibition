package com.zel.business.domain;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * 布展 busi_arrange
 *
 * @author andy
 */
@Data
public class BusiArrange {

//    private static final long serialVersionUID = 1L;
//
//    public static long getSerialVersionUID() {
//        return serialVersionUID;
//    }

    /**
     * 布展ID
     */
    private Long arrangeId;

    /**
     * 展会ID
     */
    private Long exhibitionId;

    /**
     * 图片名称
     */
    private String fileName;

    /**
     * 布展图片
     */
    private String arrangeUrl;

    /**
     * 缩略图
     */
    private String thumbImage;

    private List<String> arrangeUrlList;

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

    public BusiArrange() {
        super();
    }

    public BusiArrange(Long exhibitionId, String fileName, String arrangeUrl) {
        this.exhibitionId = exhibitionId;
        this.fileName = fileName;
        this.arrangeUrl = arrangeUrl;
    }

    @Override
    public String toString() {
        return "BusiProspect{" +
                "arrangeId=" + arrangeId +
                ", exhibitionId=" + exhibitionId +
                ", fileName='" + fileName + '\'' +
                ", arrangeUrl='" + arrangeUrl + '\'' +
                '}';
    }
}
