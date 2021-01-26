package com.zel.business.service;

import com.zel.business.domain.BusiMaterial;
import com.zel.business.domain.BusiSend;
import com.zel.business.domain.BusiSendMaterialDetial;
import com.zel.business.domain.dto.BusiSendMaterialDto;
import com.zel.business.domain.dto.BusiUserTreeOutDto;
import com.zel.common.core.domain.Ztree;

import java.lang.reflect.Array;
import java.util.List;

public interface IBusiSendService {

    /**
     * 获取发货列表
     * @param send 发货信息
     */
    List<BusiSend> selectSendList(BusiSend send);

    /**
     * 加载发货物料明细
     * @param id  发货id
     * @param materialName 物料名称
     * @param materialCode  物料代码
     */
    List<BusiSendMaterialDto> selectSendMaterialDetail(Long id, String materialName, String materialCode);

    /**
     * 保存发货信息
     */
    int saveSend(BusiSend busiSend);

    /**
     * 创建发货单号
     */
    String createSerialNumber();

    /**
     * 查询人员Tree
     */
    BusiUserTreeOutDto selectUserTree();

    /**
     * 发货单选择收货人员 组织树查询
     * 根据parentId 父级id 关联子集
     * @return
     */
    List<Ztree> sendZTree();

    /**
     * 删除发货信息
     * @param ids
     */
    int remove(Long ids[]);

    /**
     * 发货
     * @param ids
     */
    int send(Long ids[]);

    /**
     * 查询发货信息
     * @param id 发货单号
     */
    BusiSend selectsendInfo(Long id);

    /**
     * 保存修改发货信息
     */
    int saveEdit(BusiSend busiSend);
}
