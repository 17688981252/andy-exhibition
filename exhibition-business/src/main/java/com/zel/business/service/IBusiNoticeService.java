package com.zel.business.service;

import com.zel.business.domain.BusiReturn;
import com.zel.business.domain.BusiSend;
import com.zel.business.domain.dto.BusiNoticeOutDto;
import com.zel.business.domain.dto.BusiReturnMaterialDto;
import com.zel.business.domain.dto.BusiReturnNoticeOutDto;
import com.zel.business.service.impl.BusiNoticeServiceImpl;

import java.util.List;

public interface IBusiNoticeService {


    /**
     * 加载到货通知列列表
     */
    List<BusiNoticeOutDto> selectNoticeList(BusiSend send);

    /**
     * 加载退还通知列表
     * @return 通知list
     * @param busiReturn 退还实体
     */
    List<BusiReturnNoticeOutDto> findReturnNoticeList(BusiReturn busiReturn);

    /**
     * 查询退还物料明细
     * @param returnId 退还ID
     */
    List<BusiReturnMaterialDto> selectReturnMaterialDetail(Long returnId);
}
