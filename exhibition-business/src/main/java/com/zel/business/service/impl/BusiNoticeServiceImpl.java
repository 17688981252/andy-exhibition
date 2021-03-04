package com.zel.business.service.impl;

import com.zel.business.domain.BusiReturn;
import com.zel.business.domain.BusiSend;
import com.zel.business.domain.dto.BusiNoticeOutDto;
import com.zel.business.domain.dto.BusiReturnMaterialDto;
import com.zel.business.domain.dto.BusiReturnNoticeOutDto;
import com.zel.business.mapper.BusiNoticeMapper;
import com.zel.business.service.IBusiNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusiNoticeServiceImpl implements IBusiNoticeService {

    @Autowired
    private BusiNoticeMapper noticeMapper;

    /**
     * 加载到货通知列列表
     */
    @Override
    public List<BusiNoticeOutDto> selectNoticeList(BusiSend send) {
        List<BusiNoticeOutDto> noticeList = noticeMapper.selectNoticeList(send);
        for (BusiNoticeOutDto notice : noticeList){
            if(notice.getReceiveId() == null){
                notice.setStatus("发货在途");
            }else{
                notice.setStatus("到货签收");
            }
        }
        return noticeList;
    }

    /**
     * 加载退还通知列表
     * @return 通知list
     * @param busiReturn 退还实体
     */
    @Override
    public List<BusiReturnNoticeOutDto> findReturnNoticeList(BusiReturn busiReturn) {
        List<BusiReturnNoticeOutDto> resultList = noticeMapper.findReturnNoticeList(busiReturn);
        for(BusiReturnNoticeOutDto list:resultList){
            if (list.getStatus().equals(2)) {
                list.setReturnStatus("退还在途");
            }else if(list.getStatus().equals(3)){
                    list.setReturnStatus("退还已收");
            }
        }
        return resultList;
    }

    /**
     * 查询退还物料明细
     * @param returnId 退还ID
     */
    @Override
    public List<BusiReturnMaterialDto> selectReturnMaterialDetail(Long returnId) {
        return noticeMapper.selectReturnMaterialDetail(returnId);
    }
}
