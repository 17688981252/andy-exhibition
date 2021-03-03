package com.zel.business.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zel.common.annotation.Excel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 展会信息 busi_eshibition
 *
 * @author andy
 */
@Data
public class BusiExhibition {

    private static final long serialVersionUID = 1L;

    /**
     * 展会ID
     */
//    @Excel(name = "展会ID", cellType = Excel.ColumnType.NUMERIC)
    private Long exhibitionId;

    /**
     * 展会名称
     */
    @Excel(name = "展会名称")
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
    @Excel(name = "开始时间", dateFormat = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 结束时间
     */
    @Excel(name = "结束时间", dateFormat = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyy-MM-dd")
    private Date endTime;

    /**
     * 办展地点
     */
    @Excel(name = "办展地点")
    private String address;

    /**
     * 办展方
     */
    @Excel(name = "办展方")
    private String organizer;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新人
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 删除标识
     * 1：正常  2：删除
     */
    private Integer del;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;

    /**
     * 勘展图片
     */
    private List<Map> prospectUrlList;

    /**
     * 布展图片
     */
    private List<Map> arrangeUrlList;

    /**
     * 撤展图片
     */
    private List<Map> revokeUrlList;


    /** 是否为发货展会标识 默认不是 */
    private boolean flag = false;





}
