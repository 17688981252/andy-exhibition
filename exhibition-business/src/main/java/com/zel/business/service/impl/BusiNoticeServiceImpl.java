package com.zel.business.service.impl;

import com.zel.business.domain.BusiSend;
import com.zel.business.domain.dto.BusiNoticeOutDto;
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
}
