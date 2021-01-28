package com.zel.business.service.impl;

import com.zel.business.domain.BusiMaterial;
import com.zel.business.mapper.BusiMaterialMapper;
import com.zel.business.service.IBusiMaterialService;
import com.zel.common.constant.UserConstants;
import com.zel.common.exception.BusinessException;
import com.zel.common.utils.StringUtils;
import com.zel.framework.util.ShiroUtils;
import com.zel.system.service.impl.SysUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BusiMaterialServiceImpl implements IBusiMaterialService {

    private static final Logger log = LoggerFactory.getLogger(BusiMaterialServiceImpl.class);

    @Autowired
    private BusiMaterialMapper materialMapper;

    /**
     * 校验物料名称是否唯一
     * @param material 物料信息
     */
    @Override
    public String checkMaterialNameUnique(BusiMaterial material) {
        int count = materialMapper.checkMaterialNameUnique(material);
        if (count>0) {
            return UserConstants.MATERIAL_NAME_NOT_UNIQUE;
        }
        return UserConstants.MATERIAL_NAME_UNIQUE;
    }

    /**
     * 校验物料名称是否唯一
     * @param material 物料信息
     */
    @Override
    public String checkMaterialCodeUnique(BusiMaterial material) {
        int count = materialMapper.checkMaterialCodeUnique(material);
        if (count>0) {
            return UserConstants.MATERIAL_NAME_NOT_UNIQUE;
        }
        return UserConstants.MATERIAL_NAME_UNIQUE;
    }

    /**
     * 保存新增物料
     * @param material 物料信息
     */
    @Override
    public int insertMaterial(BusiMaterial material) {
        return materialMapper.insertMaterial(material);
    }

    /**
     * 获取物料列表
     * @param material 物料信息
     */
    @Override
    public List<BusiMaterial> selectMaterialList(BusiMaterial material) {

        return materialMapper.selectMaterialList(material);
    }

    /**
     * 获取物料信息
     * @param materialId 物料ID
     */
    @Override
    public BusiMaterial selectMaterialById(Long materialId) {
        return materialMapper.selectMaterialById(materialId);
    }

    /**
     * 保存修改物料
     * @param material 物料信息
     */
    @Override
    public int updateMaterial(BusiMaterial material) {
        return materialMapper.updateMaterial(material);
    }

    /**
     * 删除物料
     * @param ids 物料ID
     */
    @Override
    public int deleteMaterialById(Long[] ids) {
       return materialMapper.deleteMaterialById(ids);
    }

    /**
     * 导出物料数据
     * @param ids 物料id
     */
    @Override
    public List<BusiMaterial> selectExportMaterialList(Long[] ids) {
        return materialMapper.selectExportMaterialList(ids);
    }

    /**
     * 导入物料数据
     * @param materialList 物料列表
     */
    @Override
    public Map<String,Object>importMaterial(List<BusiMaterial> materialList)
    {
        Map<String,Object> map = new HashMap<>();
        boolean flg = true;
        String result = new String();
        if (StringUtils.isNull(materialList) || materialList.size() == 0)
        {
            throw new BusinessException("导入物料数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successData = new StringBuilder();
        StringBuilder failureData = new StringBuilder();
        for(BusiMaterial material:materialList){
            material.setCreateBy(ShiroUtils.getSysUser().getUserId());
            try {
                if (StringUtils.isEmpty(material.getMaterialCode())) {
                    throw new BusinessException("物料代码格式不正确！");
                }else if (StringUtils.isEmpty(material.getMaterialName())){
                    throw new BusinessException("物料名称格式不正确！");
                }else if (StringUtils.isEmpty(material.getNickName())){
                    throw new BusinessException("物料简称格式不正确！");
                }else if (StringUtils.isEmpty(material.getSpecifications())){
                    throw new BusinessException("规格型号格式不正确！");
                }else if(material.getPackageQuantity() == null){
                    throw new BusinessException("标包数量格式不正确！");
                }
                BusiMaterial m = materialMapper.selectMaterialByCode(material.getMaterialCode());
                if (StringUtils.isNull(m)) {
                    this.insertMaterial(material);
                    successNum++;
                    successData.append("<br/>" + successNum + "、物料代码 " + material.getMaterialCode() + " 导入成功");
                }else {
                    materialMapper.updateMaterialByCode(material);
                    successNum++;
                    successData.append("<br/>" + successNum + "、物料代码 " + material.getMaterialCode() + " 更新成功");
                }
            }
            catch (BusinessException e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、物料代码 " + material.getMaterialCode()+ " 导入失败：";
                failureData.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (successNum > 0)
        {
            result ="成功导入数据共 " + successNum + " 条" + successData + "<br/>"+"未导入数据共 "+ failureNum + " 条"+failureData;
        }
        else {
            flg = false;
            result = "很抱歉，导入失败！"+ "共" + failureNum + " 条数据格式不正确，错误如下："+failureData;
        }
        map.put("flg",flg);
        map.put("msg",result);
        return map;
    }

    /**
     * 查询可选发货物料
     * @param ids
     */
    @Override
    public List<BusiMaterial> selectMaterial(Long[] ids,String materialName,String materialCode) {
        return materialMapper.selectMaterial(ids,materialName,materialCode);
    }



}
