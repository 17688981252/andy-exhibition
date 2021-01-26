package com.zel.web.controller.business;

import com.zel.business.domain.BusiMaterial;
import com.zel.business.service.IBusiMaterialService;
import com.zel.common.annotation.Log;
import com.zel.common.constant.UserConstants;
import com.zel.common.core.controller.BaseController;
import com.zel.common.core.domain.AjaxResult;
import com.zel.common.core.page.TableDataInfo;
import com.zel.common.enums.BusinessType;
import com.zel.common.utils.poi.ExcelUtil;
import com.zel.framework.util.ShiroUtils;
import com.zel.system.domain.SysPost;
import com.zel.system.domain.SysUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:物料信息
 * @auther: andy
 * @date: 2020/10/26
 */
@Controller
@RequestMapping("/business/material")
public class BusiMaterialController extends BaseController {
    private String prefix = "business/material";

    @Autowired
    private IBusiMaterialService materialService;


    @RequiresPermissions("business:material:view")
    @GetMapping()
    public String material() {
        return prefix + "/material";
    }


    /**
     * 新增物料
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 保存新增物料
     */
    @RequiresPermissions("business:material:add")
    @Log(title = "物料管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated BusiMaterial material) {
        if (UserConstants.MATERIAL_NAME_NOT_UNIQUE.equals(materialService.checkMaterialNameUnique(material))) {
            return error("新增物料'" + material.getMaterialName() + "'失败，物料名称已存在");
        } else if (UserConstants.MATERIAL_CODE_NOT_UNIQUE.equals(materialService.checkMaterialCodeUnique(material))) {
            return error("新增物料'" + material.getMaterialCode() + "'失败，物料代码已存在");
        }
        material.setCreateBy(ShiroUtils.getUserId());
        return toAjax(materialService.insertMaterial(material));
    }

//    private final AtomicInteger ai = new AtomicInteger(0);
//
//    @GetMapping(value = "test/add")
//    public AjaxResult testAdd() {
//        int index = ai.incrementAndGet();
//        BusiMaterial obj = new BusiMaterial();
//        obj.setMaterialCode("" + index);
//        obj.setMaterialName("" + index);
//        obj.setNickName("" + index);
//        obj.setSpecifications("" + index);
//        obj.setPackageQuantity(Long.valueOf("" + index));
//        obj.setDel(1);
//        return toAjax(materialService.insertMaterial(obj));
//    }

    /**
     * 获取物料列表
     *
     * @param material 物料信息
     */
    @RequiresPermissions("business:material:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BusiMaterial material) {
        startPage();
        List<BusiMaterial> list = materialService.selectMaterialList(material);
        return getDataTable(list);
    }

    /**
     * 校验物料名称
     */
    @PostMapping("/checkMaterialNameUnique")
    @ResponseBody
    public String checkMaterialNameUnique(BusiMaterial material) {
        return materialService.checkMaterialNameUnique(material);
    }

    /**
     * 校验物料编码
     */
    @PostMapping("/checkMaterialCodeUnique")
    @ResponseBody
    public String checkMaterialCodeUnique(BusiMaterial material) {
        return materialService.checkMaterialCodeUnique(material);
    }

    /**
     * 修改物料
     *
     * @param materialId 物料ID
     */
    @GetMapping("/edit/{materialId}")
    public String edit(@PathVariable("materialId") Long materialId, ModelMap mmap) {
        mmap.put("material", materialService.selectMaterialById(materialId));
        return prefix + "/edit";
    }

    /**
     * 保存修改物料
     */
    @RequiresPermissions("business:material:edit")
    @Log(title = "物料管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated BusiMaterial material) {
        if (UserConstants.MATERIAL_NAME_NOT_UNIQUE.equals(materialService.checkMaterialNameUnique(material))) {
            return error("修改物料'" + material.getMaterialName() + "'失败，物料名称已存在");
        } else if (UserConstants.MATERIAL_CODE_NOT_UNIQUE.equals(materialService.checkMaterialCodeUnique(material))) {
            return error("修改物料'" + material.getMaterialCode() + "'失败，物料编码已存在");
        }
        material.setUpdateBy(ShiroUtils.getUserId());
        return toAjax(materialService.updateMaterial(material));
    }

    /**
     * 删除物料
     *
     * @param ids 物料ID
     */
    @RequiresPermissions("business:material:remove")
    @Log(title = "删除物料", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Long[] ids) {

        return toAjax(materialService.deleteMaterialById(ids));
    }

    /**
     * 导出物料数据
     * @param ids 物料ID
     */
    @RequiresPermissions("business:material:export")
    @Log(title = "物料管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Long[] ids) {
        List<BusiMaterial> list = materialService.selectExportMaterialList(ids);
        ExcelUtil<BusiMaterial> util = new ExcelUtil<BusiMaterial>(BusiMaterial.class);
        return util.exportExcel(list, "物料数据");
    }

    /**
     * 导入物料数据
     * @param file 物料数据
     */
    @Log(title = "物料管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("business:material:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception {
        ExcelUtil<BusiMaterial> util = new ExcelUtil<BusiMaterial>(BusiMaterial.class);
        List<BusiMaterial> materialList = util.importExcel(file.getInputStream());

        Map<String, Object> map = materialService.importMaterial(materialList);

        boolean flg = true;
        String msg = "";
        if(map !=null && map.size() >0){
            flg = (boolean) map.get("flg");
            msg = (String) map.get("msg");
        }
        if (flg){
            return AjaxResult.success(AjaxResult.Type.SUCCESS,msg,flg);
        }else {
            return AjaxResult.error(msg,flg);
        }
    }

    /**
     * 下载模板
     */
    @RequiresPermissions("business:material:importTemplate")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<BusiMaterial> util = new ExcelUtil<BusiMaterial>(BusiMaterial.class);
        return util.importTemplateExcel("用户数据");
    }

}