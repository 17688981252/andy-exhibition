package com.zel.business.service.impl;

import com.zel.business.domain.BusiExhibition;
import com.zel.business.domain.BusiMaterial;
import com.zel.business.domain.BusiReturn;
import com.zel.business.domain.BusiSerialNumberInfo;
import com.zel.business.mapper.BusiMaterialMapper;
import com.zel.business.mapper.BusiReturnMapper;
import com.zel.business.service.IBusiReturnService;
import com.zel.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BusiReturnServiceImpl implements IBusiReturnService {

    @Autowired
    BusiReturnMapper returnMapper;

    @Autowired
    BusiMaterialMapper materialMapper;

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
    public List<BusiExhibition> selectReturnExhibitionInfo() {
        return returnMapper.selectReturnExhibitionInfo();
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
        returnEntity.setCreateBy(ShiroUtils.getUserId());
        int count1 = returnMapper.insertReturn(returnEntity);
        returnEntity.getReturnId();
        int count2 = returnMapper.insertReturnMaterialDetails(returnEntity);
        if (count2>0) {
            returnMapper.updateReturnSerialNumber();
        }
        return count2;
    }

    /**
     * 删除退还信息
     * @param ids 退还id
     * @return 删除数
     */
    @Override
    public int removeReturn(Long[] ids) {
        return returnMapper.removeReturn(ids);
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
}
