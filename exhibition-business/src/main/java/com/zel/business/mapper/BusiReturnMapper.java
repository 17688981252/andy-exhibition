package com.zel.business.mapper;

import com.zel.business.domain.BusiExhibition;
import com.zel.business.domain.BusiReturn;
import com.zel.business.domain.BusiSend;
import com.zel.business.domain.BusiSerialNumberInfo;
import com.zel.business.domain.dto.BusiReturnMaterialDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusiReturnMapper {

    /**
     * 查询退还列表
     * @param returnEntity 退还实体
     * @return 退还列表
     */
    List<BusiReturn> selectReturnList(BusiReturn returnEntity);

    /**
     * 查询退还展会信息
     * @return 退还展会列表
     * @param exhibitionId
     */
    BusiExhibition selectReturnExhibitionInfo(@Param(value = "exhibitionId")Long exhibitionId);

    /**
     * 查询退还流水号信息
     * @return 退还流水号信息
     */
    BusiSerialNumberInfo selectSerialNumberInFo();

    /**
     * 新增退还信息
     * @param returnEntity 退还实体
     * @return 新增数量
     */
    int insertReturn(BusiReturn returnEntity);

    /**
     * 插入退还物料明细
     *
     * @param returnId
     * @param receiveList 物料List
     * @param createBy 创建人
     * @return 返回插入数量
     */
    int insertReturnMaterialDetails(@Param(value = "returnId")Long returnId,
                                    @Param(value = "receiveList") List<BusiSend> receiveList,
                                    @Param(value = "createBy") Long createBy);

    /**
     * 更新退还序列号
     * @return 更新数
     */
    int updateReturnSerialNumber();

    /**
     * 删除退还信息
     * @param ids 退还id
     * @return 删除数
     */
    int removeReturn(@Param(value = "ids") Long[] ids);

    int updateReturnStatus(@Param(value = "ids") Long[] ids,
                           @Param(value = "returnBy")Long returnBy);

    /**
     * 查询未退还展会列表
     */
    List<BusiExhibition> selectUnreturnList();

    /**
     * 查询退还物料明细
     * @param returnId 退还ID
     * @param materialName 物料名称
     * @param materialCode 物料代码
     * @return 物料列表
     */
    List<BusiReturnMaterialDto> selectReturnMaterialDetail(@Param(value = "returnId") Long returnId,
                                                           @Param(value = "materialName") String materialName,
                                                           @Param(value = "materialCode") String materialCode);

    /**
     * 更新收货物料明细
     * @param busiReturn 收货实体
     * @return 更新数量
     */
    int updateReturnMaterialDetail(BusiReturn busiReturn);

    /**
     * 确认退还
     * @param returnId 退还ID
     * @param returnBy 退还人
     */
    int confirmReturn(@Param(value = "returnId") Long returnId,
                      @Param(value = "returnBy")Long returnBy);

    /**
     * 查看退还状态
     * @param returnId 退还ID
     */
    Object selectReturnStatus(@Param(value = "returnId") Long returnId);

    /**
     * 查看未退还展会信息
     * @return 展会LIST
     */
    List<BusiExhibition> selectUnReturnExhibitionInfo();
}
