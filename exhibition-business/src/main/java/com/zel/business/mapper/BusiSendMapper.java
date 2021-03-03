package com.zel.business.mapper;

import com.zel.business.domain.*;
import com.zel.business.domain.dto.BusiSendMaterialDto;
import com.zel.business.domain.dto.BusiUserTreeOutDto;
import com.zel.common.core.domain.Ztree;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.List;


@Repository
public interface BusiSendMapper {

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
    List<BusiSendMaterialDto> selectSendMaterialDetail(@Param(value = "id") Long id,
                                                       @Param(value = "materialName") String materialName,
                                                       @Param(value = "materialCode") String materialCode);

    /**
     * 删除发货信息
     * @param sendId
     */
    void deleteSend(@Param(value = "sendId") Long sendId);

    /**
     * 删除发货明细
     * @param sendId
     */
    void deleteSendMaterialDetial(@Param(value = "sendId") Long sendId);

    /**
     * 保存发货信息
     * @param busiSend
     * @return
     */
    int insertSend(BusiSend busiSend);

    /**
     * 保存发货明细
     * @return
     */
    int insertSendMaterialDetail(BusiSend busiSend);

    /**
     * 查询流水号信息
     * @return
     */
    BusiSerialNumberInfo selectSerialNumberInfo();

    /**
     * 更新流水号信息
     * @return
     */
    int updateSerialNumber(@Param(value = "serialNumber") Long serialNumber);

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
    int updateDel(@Param(value = "ids") Long ids[]);

    /**
     * 发货
     * @param ids
     */
    int send(@Param(value = "ids") Long ids[],
             @Param(value = "sendBy") Long sendBy);

    /**
     * 查询发货信息
     * @param id 发货单号
     */
    BusiSend selectsendInfo(@Param(value = "id") Long id);

    /**
     * 未收货列表
     * @param send 发货信息
     */
    List<BusiSend> selectUnReceiveList(BusiSend send);

    /**
     * 更新发货状态
     * @param id 发货ID
     */
    void updateSendStatus(@Param(value = "id") Long id);


    /**
     * 更新发货物料明细
     * @param send 发货实体
     * @return 更新数量
     */
    int updateSendMaterialDetail(BusiSend send);

    /**
     * 查询收货物料明细
     * @param exhibitionId 展会ID
     */
    List<BusiSend> selectReceiveMaterialDetial(@Param(value = "exhibitionId") Long exhibitionId);
}
