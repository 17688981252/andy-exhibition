package com.zel.business.mapper;

import com.zel.business.domain.BusiMaterial;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusiMaterialMapper {

    /**
     * 校验物料名称是否唯一
     * @param material 物料信息
     */
    int checkMaterialNameUnique(BusiMaterial material);

    /**
     * 校验物料代码是否唯一
     * @param material 物料信息
     */
    int checkMaterialCodeUnique(BusiMaterial material);

    /**
     * 保存新增物料
     * @param material 物料信息
     */
    int insertMaterial(BusiMaterial material);

    /**
     * 获取物料列表
     * @param material 物料信息
     */
    List<BusiMaterial> selectMaterialList(BusiMaterial material);

    /**
     * 获取物料信息
     * @param materialId 物料ID
     */
    BusiMaterial selectMaterialById(@Param(value = "materialId") Long materialId);

    /**
     * 保存修改物料
     * @param material 物料信息
     */
    int updateMaterial(BusiMaterial material);

    /**
     * 删除物料
     * @param ids 物料ID
     */
    int deleteMaterialById(@Param(value = "ids") Long[] ids);

    /**
     * 导出物料数据
     * @param ids 物料id
     */
    List<BusiMaterial> selectExportMaterialList(@Param(value = "ids") Long[] ids);

    /**
     * 根据物料代码获取物料信息
     * @param materialCode 物料代码
     */
    BusiMaterial selectMaterialByCode(@Param(value = "materialCode") String materialCode);

    /**
     * 根据物料代码更新物料信息
     * @param material
     */
    void updateMaterialByCode(BusiMaterial material);

    /**
     * 查询可选发货物料
     * @param ids
     */
    List<BusiMaterial> selectMaterial(@Param(value = "ids") Long[] ids,
                                      @Param(value = "materialName") String materialName,
                                      @Param(value = "materialCode")String materialCode);


    /**
     * 查询退还物料明细
     * @param returnId 退还ID
     * @return 退还物料列表
     */
    List<BusiMaterial> selectRerurnMaterialDetial(@Param(value = "returnId") Long returnId);
}
