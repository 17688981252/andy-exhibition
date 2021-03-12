package com.zel.business.service.impl;

import com.zel.business.domain.*;
import com.zel.business.domain.dto.BusiReturnMaterialDto;
import com.zel.business.mapper.BusiReturnReceiveMapper;
import com.zel.business.service.IBusiExhibitionService;
import com.zel.business.service.IBusiReturnReceiveService;
import com.zel.business.service.IBusiReturnService;
import com.zel.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BusiReturnReceiveImpl implements IBusiReturnReceiveService {

    @Autowired
    private BusiReturnReceiveMapper returnReceiveMapper;

    @Autowired
    private IBusiReturnService returnService;

    @Autowired
    private IBusiReturnReceiveService returnReceiveService;

    @Autowired
    private IBusiExhibitionService exhibitionService;

    /**
     * 查看退还未签收列表
     * @param exhibitionId 展会ID
     * @return 退还list
     */
    @Override
    public List<BusiReturn> findUnReceiveList(Long exhibitionId) {
        return returnReceiveMapper.findUnReceiveList(exhibitionId);
    }

    /**
     * 查询签收列表
     * @param returnReceive 退还实体
     * @return 签收list
     */
    @Override
    public List<BusiReturn> findReceivelist(BusiReturnReceive returnReceive) {
        return returnReceiveMapper.findReceivelist(returnReceive);
    }

    /**
     * 查询未签收展会信息
     * @return
     */
    @Override
    public List<BusiExhibition> selectExhibitionInfo() {
        return returnReceiveMapper.selectExhibitionInfo();
    }

    /**
     * 创建退还签收单号
     * @return
     */
    @Override
    public String createReturnReceiveNumber() {
        BusiSerialNumberInfo serialNumberInfo = returnReceiveMapper.selectSerialNumberInFo();
        String returnReceiveNmber = "";
        String pre = serialNumberInfo.getPrefix();
        String ver = serialNumberInfo.getVer().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        String serialNumber = serialNumberInfo.getSerialNumber().toString();
        if (serialNumber.length()<3) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3 - serialNumber.length(); i++) {
                sb.append('0');
            }
            serialNumber = sb.append(serialNumber).toString();
        }
        returnReceiveNmber = pre + ver + date + serialNumber;
        return returnReceiveNmber;
    }

    /**
     * 查询退还物料
     * @param returnId 退还ID
     * @return
     */
    @Override
    public List<BusiReturnMaterialDto> findReturnMaterial(Long returnId, String materialName, String materialCode) {
        return returnService.selectReturnMaterialDetail(returnId,materialName,materialCode);
    }

    /**
     * 更新退还签收物料明细
     * @param busiReturn 退还实体
     */
    @Override
    public int updateReturnMaterialDetail(BusiReturn busiReturn) {
        int count = 0;
        busiReturn.setUpdateBy(ShiroUtils.getUserId());
        for (Map map: busiReturn.getListMap()){
            busiReturn.setMaterialId(Long.parseLong(map.get("materialId").toString()));
            busiReturn.setReturnReceiveQuantity(Integer.parseInt(map.get("returnReceiveQuantity").toString()));
            returnReceiveMapper.updateReturnMaterialDetail(busiReturn);
            count++;
        }
        return count;
    }

    /**
     * 保存退还签收
     * @param returnReceive 退还签收实体
     */
    @Override
    public int saveReturnReceive(BusiReturnReceive returnReceive) {
        int count = 0;
        returnReceive.setCreateBy(ShiroUtils.getUserId());
        for (Map map: returnReceive.getListMap()){
            returnReceive.setReturnId(Long.parseLong(map.get("returnId").toString()));
            returnReceive.setExhibitionId(Long.parseLong(map.get("exhibitionId").toString()));
            returnReceive.setLogisticsName(map.get("logisticsName").toString());
            returnReceive.setLogisticsNumber(map.get("logisticsNumber").toString());
            int count2 = returnReceiveMapper.saveReturnReceive(returnReceive);
            if (count2>0) {
                returnReceiveMapper.updateReturnReceiveSerialNumber();
            }
            String serialNumber = returnReceiveService.createReturnReceiveNumber();
            returnReceive.setReturnReceiveNumber(serialNumber);
            count++;
        }

        return count;
    }

    /**
     * 查看退还签收状态
     * @param returnId 退还ID
     * @return
     */
    @Override
    public Object seleceReturnReceiveStatus(Long returnId) {
        return returnReceiveMapper.seleceReturnReceiveStatus(returnId);
    }

    /**
     * 删除退还签收
     * @param ids 退还签收id
     */
    @Override
    public int deleteReturnReceive(Long[] ids) {
        return returnReceiveMapper.deleteReturnReceive(ids);
    }

    /**
     * 确认退还签收
     * @param returnReceiveId
     * @return
     */
    @Override
    public int confirmReturnReceive(Long returnReceiveId) {
        int count = returnReceiveMapper.updateReturnReceiveStatus(returnReceiveId);
        if (count>0) {
            returnReceiveMapper.updateReturnStatus(returnReceiveId);
            int count1 = returnReceiveMapper.updateExhibitionStatus(returnReceiveId);
            if (count1>0) {
                BusiReturnReceive returnReceive = returnReceiveMapper.selectReturnReceiveInfo(returnReceiveId);
                BusiExhibitionRecord record = new BusiExhibitionRecord();
                record.setExhibitionId(returnReceive.getExhibitionId());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(returnReceive.getReceiveTime());
                record.setEvent("签收展会："+ returnReceive.getExhibitionName()+" 物料，"+"签收单号："+returnReceive.getReturnReceiveNumber()+"、物流名称："+returnReceive.getLogisticsName()
                + "、物流单号："+returnReceive.getLogisticsNumber()+"签收人："+returnReceive.getReceiveName()+"、签收时间："+date);
                record.setStatus(8);
                exhibitionService.insertExhibitionRecord(record);
            }
        }
        return count;
    }
}
