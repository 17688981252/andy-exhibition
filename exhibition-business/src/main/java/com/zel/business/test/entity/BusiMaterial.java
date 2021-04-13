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
 * 物料信息表
 * </p>
 *
 * @author andy
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BusiMaterial implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 物料Id
     */
    @TableId(value = "material_id", type = IdType.AUTO)
    private Long materialId;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 物料代码
     */
    private String materialCode;

    /**
     * 物料简称
     */
    private String nickName;

    /**
     * 规格型号
     */
    private String specifications;

    /**
     * 类型 （从字典选）
     */
    private Integer type;

    /**
     * 计量单位 （从字典选）
     */
    private Integer unit;

    /**
     * 标包数量
     */
    private Long packageQuantity;

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
