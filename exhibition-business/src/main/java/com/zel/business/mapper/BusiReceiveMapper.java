package com.zel.business.mapper;

import com.zel.business.domain.BusiReceive;
import com.zel.business.domain.BusiSend;
import com.zel.business.domain.dto.BusiReceiveMaterialDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusiReceiveMapper {

    /**
     * 查询收货列表
     * @param receive
     */
    List<BusiReceive> selectReceiveList(BusiReceive receive);

    /**
     * 查询收货物料明细
     * @param sendId 发货id
     */
    List<BusiReceiveMaterialDto> selectReceiveMaterialDetialList(@Param(value = "sendId") Long sendId);

    /**
     * 查询已发货信息
     */
    List<BusiSend> selectSendInfo(@Param(value = "logisticsNumber") String logisticsNumber);

    List<BusiSend> selectLogisticsInfo();


    /**
     * 保存新增收货
     */
    int addSave(BusiReceive receive);

    int deleteReceive(@Param(value = "id") Long id);
}
