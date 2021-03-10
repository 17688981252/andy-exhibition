package com.zel.business.domain.dto;

import com.zel.common.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BusiReturnMaterialDto {
    /**
     * 物料序号
     */
    @Excel(name = "物料序号", cellType = Excel.ColumnType.NUMERIC)
    private Long materialId;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String materialName;

    /** 岗物料代码 */
    @Excel(name = "物料代码")
    private String materialCode;

    /** 物料简称*/
    @Excel(name = "物料简称")
    private String nickName;

    @Excel(name = "规格参数")
    private String specifications;

    @Excel(name = "类型", dictType = "busi_material_type",readConverterExp = "1=塑料,2=金属,3=其他")
    private Integer type;

    @Excel(name = "计量单位", dictType = "busi_material_unit",readConverterExp = "1=件,2=公斤,3=吨")
    private Integer unit;

    @Excel(name = "标包数量")
    private BigDecimal packageQuantity;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
//    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss",type =Excel.Type.EXPORT)

    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    //    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss",type = Excel.Type.EXPORT)
    private Date updateTime;

    /** 删除标识
     *  1：正常  2：删除
     * */
    private Integer del;

    @Excel(name = "备注")
    private String remark;

    /* 发货数量*/
    private Integer sendQuantity;

    /*收货数量*/
    private Integer receiveQuantity;

    /*退还数量*/
    private Integer returnQuantity;

    /*退还数量*/
    private Integer returnReceiveQuantity;

}
