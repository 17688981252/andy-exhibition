package com.zel.business.service;

import com.zel.business.domain.BusiExhibition;
import com.zel.business.domain.BusiMaterial;
import com.zel.business.domain.BusiReturn;
import com.zel.business.domain.dto.BusiReturnMaterialDto;

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
    BusiExhibition selectReturnExhibitionInfo(Long exhibitionid);

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

    /**
     * 查询未退还展会列表
     * @return
     */
    List<BusiExhibition> selectUnreturnList();

    /**
     * 查询收货物料明细
     * @param exhibitionId 展会ID
     * @param materialName 物料名称
     * @param materialCode 物料代码
     * @return 物料列表
     */
    List<BusiReturnMaterialDto> selectReceiveMaterialDetail(Long exhibitionId,String materialName, String materialCode);

    /**
     * 更新收货物料明细
     * @param busiReturn 收货实体
     * @return 更新数量
     */
    int updateReturnMaterialDetail(BusiReturn busiReturn);

    /**
     * 确认退还
     * @param returnId 退还ID
     */
    int confirmReturn(Long returnId);

    /**
     * 查看退还状态
     * @param returnId 退还ID
     */
    Object selectReturnStatus(Long returnId);

    /**
     * 查看未退还展会信息
     * @return 展会LIST
     */
    List<BusiExhibition> selectUnReturnExhibitionInfo();

    /**
     * 查询展会ID
     * @param returnId
     * @return
     */
    Object selectExhibitionId(Long returnId);

    /**
     * 查询收货物料明细
     * @param returnId 展会ID
     * @param materialName 物料名称
     * @param materialCode 物料代码
     * @return 物料列表
     */
    List<BusiReturnMaterialDto> selectReturnMaterialDetail(Long returnId, String materialName, String materialCode);
}
