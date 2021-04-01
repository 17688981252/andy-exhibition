package com.zel.business.service.impl;

import com.zel.business.domain.BusiExhibitionRecord;
import com.zel.business.domain.BusiReceive;
import com.zel.business.domain.BusiSend;
import com.zel.business.domain.BusiSerialNumberInfo;
import com.zel.business.domain.dto.BusiReceiveInDto;
import com.zel.business.domain.dto.BusiReceiveMaterialDto;
import com.zel.business.domain.dto.BusiReceiveSerialNumberInfo;
import com.zel.business.mapper.BusiReceiveMapper;
import com.zel.business.mapper.BusiSendMapper;
import com.zel.business.service.IBusiExhibitionService;
import com.zel.business.service.IBusiReceiveService;
import com.zel.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BusiReceiveServiceImpl implements IBusiReceiveService {

    @Autowired
    private BusiReceiveMapper receiveMapper;

    @Autowired
    private BusiSendMapper sendMapper;

    @Autowired
    private IBusiReceiveService receiveService;

    @Autowired
    private IBusiExhibitionService exhibitionService;

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
            BusiSerialNumberInfo receiveSerialNumberInfo = receiveMapper.selectReceiveSerialNumberInfo();
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
            int count2 = receiveMapper.addSave(receiveInDto);
            count++;
            receiveMapper.updateReceiveQuantity(id);
            sendMapper.updateSendStatus(id);
            receiveMapper.updateReceiveSerialNumber();
            // 记录
            if (count2>0) {
                Long receiveId = receiveInDto.getReceiveId();
                BusiReceive receive = receiveService.selectReceiveInfo(receiveId);
                BusiExhibitionRecord record = new BusiExhibitionRecord();
                record.setExhibitionId(receive.getExhibitionId());
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String receiveTime = df.format(receive.getReceiveTime());
                record.setEvent("展会:"+receive.getExhibitionName()+"、收货单号："+receive.getReceiveNumber()+"、物流名称："+receive.getLogisticsName()
                        +"、物流单号："+receive.getLogisticsNumber()+"、收货人："+receive.getReceiveName()+"、收货时间："+receiveTime);
                record.setStatus(4);
                exhibitionService.insertExhibitionRecord(record);
            }
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
     * @param receiveId
     * @return
     */
    @Override
    public BusiReceive selectReceiveInfo(Long receiveId) {

        return receiveMapper.selectReceiveInfo(receiveId);
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
