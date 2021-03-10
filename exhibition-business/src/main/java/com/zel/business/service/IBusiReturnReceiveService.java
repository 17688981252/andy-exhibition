package com.zel.business.service;

import com.zel.business.domain.BusiExhibition;
import com.zel.business.domain.BusiReturn;
import com.zel.business.domain.BusiReturnReceive;
import com.zel.business.domain.dto.BusiReturnMaterialDto;

import java.util.List;

public interface IBusiReturnReceiveService {


    /**
     * 查看退还未签收列表
     * @param exhibitionId 展会ID
     * @return 退还list
     */
    List<BusiReturn> findUnReceiveList(Long exhibitionId);

    /**
     * 查询签收列表
     * @param returnReceive 退还签收实体
     * @return 签收list
     */
    List<BusiReturn> findReceivelist(BusiReturnReceive returnReceive);

    /**
     * 查询未签收展会信息
     * @return
     */
    List<BusiExhibition> selectExhibitionInfo();

    /**
     * 创建退还签收单号
     * @return
     */
    String createReturnReceiveNumber();

    /**
     * 查询退还物料
     * @param returnId 退还ID
     * @param materialName 物料名称
     * @param materialCode 物料代码
     * @return
     */
    List<BusiReturnMaterialDto> findReturnMaterial(Long returnId, String materialName, String materialCode);

    /**
     * 更新退还签收物料明细
     * @param busiReturn 退还实体
     */
    int updateReturnMaterialDetail(BusiReturn busiReturn);

    /**
     * 保存退还签收
     * @param returnReceive 退还签收实体
     */
    int saveReturnReceive(BusiReturnReceive returnReceive);

    /**
     * 查看退还签收状态
     * @param returnId 退还ID
     * @return
     */
    Object seleceReturnReceiveStatus(Long returnId);

    /**
     * 删除退还签收
     * @param ids 退还签收id
     */
    int deleteReturnReceive(Long[] ids);

    /**
     * 确认退还签收
     * @param returnReceiveId
     * @return
     */
    int confirmReturnReceive(Long returnReceiveId);
}
