package com.zel.business.service.impl;

import com.zel.business.domain.*;
import com.zel.business.domain.dto.BusiSendMaterialDto;
import com.zel.business.domain.dto.BusiUserTreeOutDto;
import com.zel.business.mapper.BusiSendMapper;
import com.zel.business.service.IBusiSendService;
import com.zel.common.core.domain.Ztree;
import com.zel.common.utils.uuid.IdUtils;
import com.zel.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BusiSendServiceImpl implements IBusiSendService {

    @Autowired
    private BusiSendMapper sendMapper;

    /**
     * 获取发货列表
     * @param send 发货信息
     */
    @Override
    public List<BusiSend> selectSendList(BusiSend send) {
        return sendMapper.selectSendList(send);
    }

    /**
     * 加载发货物料明细
     * @param id  发货id
     * @param materialName 物料名称
     * @param materialCode  物料代码
     */
    @Override
    public  List<BusiSendMaterialDto> selectSendMaterialDetail(Long id,String materialName,String materialCode) {
        List<BusiSendMaterialDto> detial = sendMapper.selectSendMaterialDetail(id,materialName,materialCode);
        return detial;
    }

    /**
     * 保存发货信息
     */
    @Override
    public int saveSend(BusiSend busiSend) {

        busiSend.setCreateBy(ShiroUtils.getSysUser().getUserId());
        int count1 = sendMapper.insertSend(busiSend);
        busiSend.getSendId();
        int count2 = sendMapper.insertSendMaterialDetail(busiSend);

        String number = busiSend.getSendNumber();
        if (number.length() >= 3) { // 判断是否长度大于等于3
            String strsub = number.substring(number.length()-3);    //一个参数表示截取传递的序号之后的部分
            Long serialNumber = Long.parseLong(strsub)+1;
            sendMapper.updateSerialNumber(serialNumber);
        }
        return count2;
    }
    /**
     * 创建发货单号
     */
    @Override
    public String createSerialNumber(){

        String sendNumber = "";
        BusiSerialNumberInfo serialNumberInfo = sendMapper.selectSerialNumberInfo();
        String pre = serialNumberInfo.getPrefix();
        String ver = serialNumberInfo.getVer().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date()); // 格式化日期 date: 20201203
        String serialNumber = String.valueOf(serialNumberInfo.getSerialNumber());

        if (serialNumber.length() < 3) {
//            serialNumber= String.format("%04s",serialNumber);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3-serialNumber.length(); i++) {
                sb.append('0');
            }
            serialNumber = sb.append(serialNumber).toString();
        }
        sendNumber = pre + ver + date + serialNumber;

        return sendNumber;
    }

    /**
     * 查询人员Tree
     */
    @Override
    public BusiUserTreeOutDto selectUserTree() {

        return sendMapper.selectUserTree();
    }


    /**
     * 发货单选择收货人员 组织树查询
     * 根据parentId 父级id 关联子集
     * @return
     */
    @Override
    public List<Ztree> sendZTree(){
        return sendMapper.sendZTree();
    }

    /**
     * 删除发货信息
     * @param ids
     */
    @Override
    public int remove(Long ids[]) {
        return sendMapper.updateDel(ids);
    }

    /**
     * 发货
     * @param ids
     */
    @Override
    public int send(Long ids[]) {
        Long sendBy = ShiroUtils.getSysUser().getUserId();
        return sendMapper.send(ids,sendBy);
    }

    /**
     * 查询发货信息
     * @param id 发货单号
     */
    @Override
    public BusiSend selectsendInfo(Long id) {

        return sendMapper.selectsendInfo(id);
    }

    /**
     * 保存修改发货信息
     */
    @Override
    public int saveEdit(BusiSend busiSend) {
        sendMapper.deleteSend(busiSend.getSendId());
        sendMapper.deleteSendMaterialDetial(busiSend.getSendId());

        busiSend.setCreateBy(ShiroUtils.getSysUser().getUserId());
        int count1 = sendMapper.insertSend(busiSend);
        busiSend.getSendId();
        int count2 = sendMapper.insertSendMaterialDetail(busiSend);
        return count2;
    }


}
