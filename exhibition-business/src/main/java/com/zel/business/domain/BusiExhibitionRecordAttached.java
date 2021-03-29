package com.zel.business.domain;

import lombok.Data;

/**
 * 展会记录附件 busi_exhibition_record_attached
 *
 * @author andy 20210310
 */
@Data
public class BusiExhibitionRecordAttached {

    /*附件ID*/
    private Long exhibitionRecordAttachedId;

    /*展会记录ID*/
    private Long exhibitionRecordId;

    /*图片路径*/
    private String pictureUrl;

    /*缩略图*/
    private String thumbImage;

    /*原文件名*/
    private String fileName;

}
