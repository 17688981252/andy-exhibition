package com.zel.business.mapper;

import com.zel.business.domain.BusiExhibition;
import com.zel.business.domain.BusiReturn;
import com.zel.business.domain.BusiReturnReceive;
import com.zel.business.domain.BusiSerialNumberInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusiReturnReceiveMapper {

    /**
     * 查看退还未签收列表
     * @param exhibitionId 展会ID
     * @return 退还list
     */
    List<BusiReturn> findUnReceiveList(@Param(value = "exhibitionId") Long exhibitionId);

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
     * 查询退还接收流水号
     * @return
     */
    BusiSerialNumberInfo selectSerialNumberInFo();

    int updateReturnMaterialDetail(BusiReturn busiReturn);

    /**
     * 保存退还签收
     * @param returnReceive 退还签收实体
     */
    int saveReturnReceive(BusiReturnReceive returnReceive);

    /**
     * 更新退还签收流水号
     */
    int updateReturnReceiveSerialNumber();

    /**
     * 查询退还签收状态
     * @param returnId 退还ID
     */
    Object seleceReturnReceiveStatus(@Param(value = "returnId") Long returnId);

    /**
     * 删除退还签收
     * @param ids 退还签收id
     */
    int deleteReturnReceive(@Param(value = "ids") Long[] ids);

    /**
     * 确认退还签收
     * @param returnReceiveId
     */
    int updateReturnReceiveStatus(@Param(value = "returnReceiveId") Long returnReceiveId);

    /**
     * 更新退还状态
     * @param returnReceiveId 退还签收ID
     */
    int updateReturnStatus(@Param(value = "returnReceiveId") Long returnReceiveId);

    /**
     * 更新展会状态
     * @param returnReceiveId 退还签收ID
     */
    int updateExhibitionStatus(@Param(value = "returnReceiveId") Long returnReceiveId);
}
