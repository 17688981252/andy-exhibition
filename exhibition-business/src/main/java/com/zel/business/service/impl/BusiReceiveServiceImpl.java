package com.zel.business.service.impl;

import com.zel.business.domain.BusiReceive;
import com.zel.business.domain.BusiSend;
import com.zel.business.domain.dto.BusiReceiveInDto;
import com.zel.business.domain.dto.BusiReceiveMaterialDto;
import com.zel.business.domain.dto.BusiReceiveSerialNumberInfo;
import com.zel.business.mapper.BusiReceiveMapper;
import com.zel.business.mapper.BusiSendMapper;
import com.zel.business.service.IBusiReceiveService;
import com.zel.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public int addSave(BusiReceiveInDto receiveInDto) {
        int count = 0;
        for(Long id : receiveInDto.getIds()){
            receiveInDto.setSendId(id);
            String receiveNumber = "";
            BusiReceiveSerialNumberInfo receiveSerialNumberInfo = receiveMapper.selectReceiveSerialNumberInfo();
            String pre = receiveSerialNumberInfo.getPrefix();
            String ver = receiveSerialNumberInfo.getVer().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String date = sdf.format(new Date());  // 格式化日期 年月日 ：20210126
            Long num = receiveSerialNumberInfo.getSerialNumber();
            String number = num.toString();
            if (number.length()<3) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 3 - number.length(); i++) {
                    sb.append('0');
                }
                number = sb.append(number).toString();
            }
            receiveNumber = pre + ver + date + number;
            receiveInDto.setReceiveNumber(receiveNumber);
            receiveInDto.setCreateBy((ShiroUtils.getUserId()));
            receiveInDto.setReceiveBy(ShiroUtils.getSysUser().getUserId());
            receiveMapper.addSave(receiveInDto);
            count++;
            sendMapper.updateSendStatus(id);
            Long newNumber = num +1;
            receiveMapper.updateReceiveSerialNumber(newNumber);
        }
        return count;

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
     * @param ids 收货ID
     */
    @Override
    public int deleteReceive(Long[] ids) {
        return receiveMapper.deleteReceive(ids);
    }


}
