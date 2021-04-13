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
 * 勘展
 * </p>
 *
 * @author andy
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BusiProspect implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 勘展图片ID
     */
    @TableId(value = "prospect_id", type = IdType.AUTO)
    private Long prospectId;

    /**
     * 展会ID
     */
    private Long exhibitionId;

    /**
     * 勘展图片
     */
    private String prospectUrl;

    /**
     * 文件名
     */
    private String fileName;

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


}
