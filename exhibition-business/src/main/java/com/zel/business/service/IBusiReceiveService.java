package com.zel.business.service;

import com.zel.business.domain.BusiReceive;
import com.zel.business.domain.BusiSend;
import com.zel.business.domain.dto.BusiReceiveInDto;
import com.zel.business.domain.dto.BusiReceiveMaterialDto;

import java.util.List;

public interface IBusiReceiveService {

    /**
     * 查询收货列表
     * @param receive
     */
    List<BusiReceive> selectReceiveList(BusiReceive receive);

    /**
     * 保存新增收货
     */
    int addSave(BusiReceiveInDto receiveInDto);

    /**
     * 查询收货物料明细
     * @param sendId 发货id
     */
    List<BusiReceiveMaterialDto> selectReceiveMaterialDetialList(Long sendId);

    List<BusiSend> selectLogisticsInfo();


    /**
     * 查询已发货信息
     */
    List<BusiSend> selectSendInfo(String logisticsNumber);

    /**
     * 未收货列表
     * @param send 发货信息
     */
    List<BusiSend> selectUnReceiveList(BusiSend send);

    /**
     * 删除收货
     * @param ids 收货ID
     */
    int deleteReceive(Long[] ids);
}
