package com.zel.business.mapper;

import com.zel.business.domain.BusiExhibition;
import com.zel.business.domain.BusiReturn;
import com.zel.business.domain.BusiSerialNumberInfo;
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
     */
    List<BusiExhibition> selectReturnExhibitionInfo();

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
     * 新增退还物料明细
     * @param returnEntity 退还实体
     * @return 新增数量
     */
    int insertReturnMaterialDetails(BusiReturn returnEntity);

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
}
