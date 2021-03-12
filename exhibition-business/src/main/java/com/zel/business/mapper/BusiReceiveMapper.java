package com.zel.business.mapper;

import com.zel.business.domain.BusiReceive;
import com.zel.business.domain.BusiSend;
import com.zel.business.domain.dto.BusiReceiveInDto;
import com.zel.business.domain.dto.BusiReceiveMaterialDto;
import com.zel.business.domain.dto.BusiReceiveSerialNumberInfo;
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
     * @param receiveId
     */
    BusiReceive selectReceiveInfo(@Param(value = "receiveId") Long receiveId);

    List<BusiSend> selectLogisticsInfo();


    /**
     * 保存新增收货
     */
    int addSave(BusiReceiveInDto receiveInDto);

    /**
     * 查询收货流水号
     */
    BusiReceiveSerialNumberInfo selectReceiveSerialNumberInfo();

    /**
     * 更新收货流水号
     * @param newNumber
     */
    void updateReceiveSerialNumber(@Param(value = "newNumber") long newNumber);

    /**
     * 删除收货
     * @param ids 收货ID
     */
    int deleteReceive(@Param(value = "ids") Long[] ids);
}
