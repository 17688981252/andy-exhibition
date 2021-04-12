package com.zel.business.service.impl;

import com.zel.business.domain.*;
import com.zel.business.domain.dto.BusiReturnMaterialDto;
import com.zel.business.mapper.BusiMaterialMapper;
import com.zel.business.mapper.BusiReturnMapper;
import com.zel.business.mapper.BusiSendMapper;
import com.zel.business.service.IBusiExhibitionService;
import com.zel.business.service.IBusiReturnService;
import com.zel.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BusiReturnServiceImpl implements IBusiReturnService {

    @Autowired
    private BusiReturnMapper returnMapper;

    @Autowired
    private BusiMaterialMapper materialMapper;

    @Autowired
    private BusiSendMapper sendMapper;

    @Autowired
    private IBusiExhibitionService exhibitionService;

    /**
     * 查询退还列表
     * @param returnEntity 退还实体
     * @return 退还列表
     */
    @Override
    public List<BusiReturn> selectReturnList(BusiReturn returnEntity) {
        return returnMapper.selectReturnList(returnEntity);
    }

    /**
     * 查询退还展会信息
     * @return 退还列表
     */
    @Override
    public BusiExhibition selectReturnExhibitionInfo(Long exhibitionId) {
        return returnMapper.selectReturnExhibitionInfo(exhibitionId);
    }

    /**
     * 创建退还流水号
     * @return 一个3位的String类型流水号
     */
    @Override
    public String createReturnNumber() {
        BusiSerialNumberInfo serialNumberInfo = returnMapper.selectSerialNumberInFo();
        String returnNmber = "";
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
        returnNmber = pre + ver + date + serialNumber;
        return returnNmber;
    }

    /**
     * 保存退还信息
     * @param returnEntity 退还实体
     * @return 保存成功数
     */
    @Override
    public int saveReturn(BusiReturn returnEntity) {
        //插入退还信息，返回退还ID
        returnEntity.setCreateBy(ShiroUtils.getUserId());
        returnMapper.insertReturn(returnEntity);
        Long returnId = returnEntity.getReturnId();

        //查询收货物料 并插入退还物料明细表
        Long exhibitionId = returnEntity.getExhibitionId();
        Long createBy = ShiroUtils.getUserId();
        List<BusiSend> receiveList = sendMapper.selectReceiveMaterialDetial(exhibitionId);
        int count = returnMapper.insertReturnMaterialDetails(returnId,receiveList,createBy);
        if (count>0) {
            returnMapper.updateReturnSerialNumber();
        }
        return count;
    }

    /**
     * 删除退还信息
     * @param ids 退还id
     * @return 删除数
     */
    @Override
    public int removeReturn(Long[] ids) {
        int count = 0;
        for (Long id : ids) {
            returnMapper.removeReturnById(id);
            count++;
            String number = returnMapper.selectReturnNumberById(id);
            exhibitionService.updateExhibitionRecordEvent(number);
        }
        return count;
    }

    /**
     * 查询退还物料明细
     * @param returnId 退还ID
     * @return 退还物料列表
     */
    @Override
    public List<BusiMaterial> selectRerurnMaterialDetial(Long returnId) {

        return materialMapper.selectRerurnMaterialDetial(returnId);
    }


    @Override
    public int returnMaterial(Long[] ids) {
        Long returnBy = ShiroUtils.getUserId();
        return returnMapper.updateReturnStatus(ids,returnBy);
    }

    /**
     * 查询未退还展会列表
     * @return
     */
    @Override
    public List<BusiExhibition> selectUnreturnList() {
        return returnMapper.selectUnreturnList();
    }

    /**
     * 查询收货物料明细
     * @param exhibitionId 展会ID
     * @param materialName 物料名称
     * @param materialCode 物料代码
     * @return 物料列表
     */
    @Override
    public List<BusiReturnMaterialDto> selectReceiveMaterialDetail(Long exhibitionId, String materialName, String materialCode) {
        return returnMapper.selectReceiveMaterialDetail(exhibitionId, materialName, materialCode);
    }

    /**
     * 更新收货物料明细
     * @param busiReturn 收货实体
     * @return 更新数量
     */
    @Override
    public int updateReturnMaterialDetail(BusiReturn busiReturn) {
        int count = 0;
        busiReturn.setUpdateBy(ShiroUtils.getUserId());
        for (Map map: busiReturn.getListMap()){
            busiReturn.setMaterialId(Long.parseLong(map.get("materialId").toString()));
            busiReturn.setReturnQuantity(Integer.parseInt(map.get("returnQuantity").toString()));
            returnMapper.updateReturnMaterialDetail(busiReturn);
            count++;
        }
        return count;
    }

    /**
     * 确认退还
     * @param returnId 退还ID
     */
    @Override
    public int confirmReturn(Long returnId) {
        BusiReturn busiReturnEntity = new BusiReturn();
        busiReturnEntity.setReturnId(returnId);
        busiReturnEntity.setReturnBy(ShiroUtils.getUserId());
        busiReturnEntity.setUpdateBy(ShiroUtils.getUserId());
        int count = returnMapper.confirmReturn(busiReturnEntity);
        if (count>0) {
            BusiExhibitionRecord record = new BusiExhibitionRecord();
            BusiReturn busiReturn = returnMapper.selectReturnInfo(returnId);
            record.setExhibitionId(busiReturn.getExhibitionId());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(busiReturn.getReturnTime());
            record.setEvent("退还展会："+busiReturn.getExhibitionName()+" 物料，"+"退还单号："+busiReturn.getReturnNumber()+"、物流名称："+busiReturn.getLogisticsName()
            +"、物流单号："+busiReturn.getLogisticsNumber()+"、退还人："+busiReturn.getReturnName()+"、退还时间"+date);
            record.setStatus(7);
            exhibitionService.insertExhibitionRecord(record);
        }
        return count;
    }

    /**
     * 查看退还状态
     * @param returnId 退还ID
     */
    @Override
    public Object selectReturnStatus(Long returnId) {
        return returnMapper.selectReturnStatus(returnId);
    }

    /**
     * 查看未退还展会信息
     * @return 展会LIST
     */
    @Override
    public List<BusiExhibition> selectUnReturnExhibitionInfo() {
        return returnMapper.selectUnReturnExhibitionInfo();
    }

    /**
     * 查询展会ID
     * @param returnId
     * @return
     */
    @Override
    public Object selectExhibitionId(Long returnId) {
        return returnMapper.selectExhibitionId(returnId);
    }

    /**
     * 查询收货物料明细
     * @param returnId 展会ID
     * @param materialName 物料名称
     * @param materialCode 物料代码
     * @return 物料列表
     */
    @Override
    public List<BusiReturnMaterialDto> selectReturnMaterialDetail(Long returnId, String materialName, String materialCode) {
        return returnMapper.selectReturnMaterialDetail(returnId,materialName,materialCode);
    }

    /**
     * 根据ID查询退还信息
     * @param returnId
     */
    @Override
    public BusiReturn selectReturnMassageById(Long returnId) {
        return returnMapper.selectReturnMassageById(returnId);
    }

}
