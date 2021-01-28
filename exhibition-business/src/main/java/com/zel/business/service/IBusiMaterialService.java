package com.zel.business.service;

import com.zel.business.domain.BusiMaterial;

import java.util.List;
import java.util.Map;

public interface IBusiMaterialService {

    /**
     * 校验物料名称是否唯一
     * @param material 物料信息
     */
  public String checkMaterialNameUnique(BusiMaterial material);

    /**
     * 校验物料代码是否唯一
     * @param material 物料信息
     */
   public String checkMaterialCodeUnique(BusiMaterial material);

    /**
     * 保存新增物料
     * @param material 物料信息
     */
   public int insertMaterial(BusiMaterial material);

    /**
     * 获取物料列表
     * @param material 物料信息
     */
    List<BusiMaterial> selectMaterialList(BusiMaterial material);

    /**
     * 获取物料信息
     * @param materialId 物料ID
     */
    BusiMaterial selectMaterialById(Long materialId);

    /**
     * 保存修改物料
     * @param material 物料信息
     */
    int updateMaterial(BusiMaterial material);

    /**
     * 删除物料
     * @param ids 物料ID
     */
    int deleteMaterialById(Long[] ids);

    /**
     * 导出物料数据
     * @param ids 物料id
     */
    List<BusiMaterial> selectExportMaterialList(Long[] ids);

    /**
     * 导入物料数据
     * @param materialList 物料列表
     */
    Map<String,Object>  importMaterial(List<BusiMaterial> materialList);

   /**
    * 查询可选发货物料
    * @param ids
    */
    List<BusiMaterial> selectMaterial(Long[] ids,String materialName,String materialCode);



}
