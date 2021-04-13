package com.zel.business.test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 展会信息
 * </p>
 *
 * @author andy
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BusiExhibition implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 展会ID
     */
    @TableId(value = "exhibition_id", type = IdType.AUTO)
    private Long exhibitionId;

    /**
     * 展会名称
     */
    private String exhibitionName;

    /**
     * 展会状态(1：保存 2：勘展 3：布展 4：撤展)
     */
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

    /**
     * 删除标识 （1：正常   2：删除）
     */
    private Integer del;

    /**
     * 备注
     */
    private String remark;


}
