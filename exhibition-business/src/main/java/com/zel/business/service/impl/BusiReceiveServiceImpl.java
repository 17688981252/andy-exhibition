package com.zel.business.service.impl;

import com.zel.business.domain.BusiReceive;
import com.zel.business.domain.BusiSend;
import com.zel.business.domain.dto.BusiReceiveMaterialDto;
import com.zel.business.mapper.BusiReceiveMapper;
import com.zel.business.mapper.BusiSendMapper;
import com.zel.business.service.IBusiReceiveService;
import com.zel.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusiReceiveServiceImpl implements IBusiReceiveService {

    @Autowired
    private BusiReceiveMapper receiveMapper;

    @Autowired
    private BusiSendMapper sendMapper;

    /**
     * 查询收货列表
     * @param receive
     */
    @Override
    public List<BusiReceive> selectReceiveList(BusiReceive receive) {

        return receiveMapper.selectReceiveList(receive);
    }

    /**
     * 保存新增收货
     */
    @Override
    public int addSave(BusiReceive receive) {
        receive.setCreateBy(ShiroUtils.getUserId());
        return receiveMapper.addSave(receive);
    }

    /**
     * 查询收货物料明细
     * @param sendId 发货id
     */
    @Override
    public List<BusiReceiveMaterialDto> selectReceiveMaterialDetialList(Long sendId) {
        return receiveMapper.selectReceiveMaterialDetialList(sendId);
    }

    @Override
    public List<BusiSend> selectLogisticsInfo() {
        List<BusiSend> listInfo = receiveMapper.selectLogisticsInfo();
        for(BusiSend list : listInfo){

        }
        return null;
    }

    /**
     * 查询已发货信息
     */
    @Override
    public List<BusiSend> selectSendInfo(String logisticsNumber) {

        return receiveMapper.selectSendInfo(logisticsNumber);
    }



    /**
     * 未收货列表
     * @param send 发货信息
     */
    @Override
    public List<BusiSend> selectUnReceiveList(BusiSend send) {
        return sendMapper.selectUnReceiveList(send);
    }

    /**
     * 删除收货
     * @param id 收货ID
     */
    @Override
    public int deleteReceive(Long id) {
        return receiveMapper.deleteReceive(id);
    }


}
