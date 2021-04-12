package com.zel.business.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zel.common.annotation.Excel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 布展返回dto
 * @author andy
 */
@Data
public class BusiArrangeDto {


    /*布展ID*/
    private Long arrangeId;

    /*图片路径*/
    private String arrangeurl;

    /*文件名*/
    private String fileName;

    /**
     * 展会ID
     */
    private Long exhibitionId;

    /**
     * 展会名称
     */
    private String exhibitionName;


    /**
     * 展会状态
     * 1：保存 2：勘展 3：布展 4：撤展
     */
    @Excel(name = "展会状态", dictType = "busi_exhibition_status")
    private Integer status;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 办展地点
     */
    private String address;

    /**
     * 办展方
     */
    private String organizer;

    /**
     * 勘展图片
     */
    private List<Map> arrangeUrlList;

    private String remark;


}
