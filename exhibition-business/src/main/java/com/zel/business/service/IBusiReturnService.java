package com.zel.business.service;

import com.zel.business.domain.BusiExhibition;
import com.zel.business.domain.BusiMaterial;
import com.zel.business.domain.BusiReturn;

import java.util.List;

public interface IBusiReturnService
{
    /**
     * 查询退还列表
     * @param returnEntity 退还实体
     * @return 列表
     */
    List<BusiReturn> selectReturnList(BusiReturn returnEntity);

    /**
     * 查询退还展会信息
     * @return 退还列表
     */
    List<BusiExhibition> selectReturnExhibitionInfo();

    /**
     * 创建退还流水号
     * @return 一个3位的String类型流水号
     */
    String createReturnNumber();

    /**
     * 保存退还信息
     * @param returnEntity 退还实体
     * @return 保存成功数
     */
    int saveReturn(BusiReturn returnEntity);

    /**
     * 删除退还信息
     * @param ids 退还id
     * @return 删除数
     */
    int removeReturn(Long[] ids);


    /**
     * 查询退还物料明细
     * @param returnId 退还ID
     * @return 退还物料列表
     */
    List<BusiMaterial> selectRerurnMaterialDetial(Long returnId);

    int returnMaterial(Long[] ids);
}
