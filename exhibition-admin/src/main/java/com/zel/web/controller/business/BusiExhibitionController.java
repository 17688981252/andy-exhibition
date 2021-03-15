package com.zel.web.controller.business;

import com.zel.business.domain.BusiExhibition;
import com.zel.business.domain.BusiExhibitionRecord;
import com.zel.business.domain.BusiExhibitionRecordAttached;
import com.zel.business.domain.BusiProspect;
import com.zel.business.service.IBusiExhibitionService;
import com.zel.common.annotation.Log;
import com.zel.common.config.Global;
import com.zel.common.constant.UserConstants;
import com.zel.common.core.controller.BaseController;
import com.zel.common.core.domain.AjaxResult;
import com.zel.common.core.page.TableDataInfo;
import com.zel.common.enums.BusinessType;
import com.zel.common.enums.ExhibitionStatus;
import com.zel.common.utils.file.FileUploadUtils;
import com.zel.common.utils.poi.ExcelUtil;
import com.zel.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * @description:展会信息
 * @auther: andy
 * @date: 2020/11/02
 */
@Controller
@RequestMapping("/business/exhibition")
public class BusiExhibitionController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(BusiExhibitionController.class);

    private String prefix = "business/exhibition";

    @Autowired
    private IBusiExhibitionService exhibitionService;

    @RequiresPermissions("business:exhibition:view")
    @GetMapping()
    public String exhibiton()
    {
        return prefix + "/exhibition";
    }

    /**
     * 新增展会
     */
    @GetMapping(value = "/add")
    public String add(){return prefix + "/add";}

    /**
     * 保存新增展会
     * @param exhibition 展会信息
     * @return
     */
    @RequiresPermissions(value = "business:exhibition:add")
    @Log(title = "展会管理",businessType = BusinessType.INSERT)
    @PostMapping(value = "/add")
    @ResponseBody
    public AjaxResult addSave(@Validated BusiExhibition exhibition){
        if (UserConstants.EXHIBITION_NAME_NOT_UNIQUE.equals(exhibitionService.checkExhibitionNameUnique(exhibition))) {
            return error("新增展会"+ exhibition.getExhibitionName() +"失败，展会名称已存在");
        }
        exhibition.setCreateBy(ShiroUtils.getUserId());
        exhibition.setStatus(ExhibitionStatus.SAVE.getCode());
        return toAjax(exhibitionService.insertExhibition(exhibition));
    }

    /**
     * 获取展会列表
     * @param exhibition 展会信息
     */
    @RequiresPermissions("business:exhibition:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BusiExhibition exhibition) {
        startPage();
        List<BusiExhibition> list = exhibitionService.selectExhibitionList(exhibition);
        return getDataTable(list);
    }

    /**
     * 校验展会名称是否唯一
     */
    @PostMapping("/checkExhibitionNameUnique")
    @ResponseBody
    public String checkExhibitionNameUnique(BusiExhibition exhibition) {
        return exhibitionService.checkExhibitionNameUnique(exhibition);
    }

    /**
     *修改展会
     * @param exhibitionId
     */
    @GetMapping("/edit/{exhibitionId}")
    public String edit(@PathVariable("exhibitionId") Long exhibitionId, ModelMap mmap) {
        mmap.put("exhibition", exhibitionService.selectExhibitionById(exhibitionId));
        return prefix + "/edit";
    }

    /**
     * 保存修改展会信息
     * @param exhibition 展会信息
     */
    @RequiresPermissions("business:exhibition:edit")
    @Log(title = "保存修改展会",businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated BusiExhibition exhibition){
        if (UserConstants.EXHIBITION_NAME_NOT_UNIQUE.equals(exhibitionService.checkExhibitionNameUnique(exhibition))) {
            return error("新增展会"+exhibition.getExhibitionName()+"失败，展会名称已存在");
        }
        /*exhibition.setUpdateBy(ShiroUtils.getLoginName());*/
        exhibition.setUpdateBy(ShiroUtils.getUserId());
        return toAjax(exhibitionService.updateExhibition(exhibition));
    }

    /**
     * 删除展会
     * @param ids 展会ID
     */
    @RequiresPermissions("business:exhibition:remove")
    @Log(title = "删除展会",businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult deleteExhibition(@Validated Long[] ids){
        return toAjax(exhibitionService.deleteExhibition(ids));
    }

    /**
     * 导出展会信息
     * @param ids
     */
    @RequiresPermissions("business:exhibition:export")
    @Log(title = "导出展会信息",businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult exportExhibition(@Validated Long[] ids){
        List<BusiExhibition> list = exhibitionService.selectExportExhibitionList(ids);
        ExcelUtil<BusiExhibition> util = new ExcelUtil<>(BusiExhibition.class);
        return util.exportExcel(list,"展会数据");
    }

    /**
     * 勘展
     * @param exhibitionId 展会ID
     */
    @GetMapping(value = "/prospect/{exhibitionId}")
    public String prospect(@PathVariable("exhibitionId")Long exhibitionId,ModelMap map){
        map.put("prospect",exhibitionService.selectProspect(exhibitionId));
        return prefix + "/prospect";
    }

    /**
     * 保存勘展图片
     */
    @Log(title = "勘展图片", businessType = BusinessType.INSERT)
    @PostMapping("/saveProspectUrl")
    @ResponseBody
    public AjaxResult saveProspectUrl(@RequestParam(value = "file_data") MultipartFile[] files,@RequestParam(value = "exhibitionId") Long exhibitionId)
    {
        BusiProspect prospect = new BusiProspect();
        try
        {
            for(MultipartFile file:files)
            {
                String prospectUrl = FileUploadUtils.upload(Global.getProspectUrlPath(), file);
                BusiProspect busiProspect = new BusiProspect(exhibitionId,file.getOriginalFilename(),prospectUrl);
                busiProspect.setCreateBy(ShiroUtils.getSysUser().getUserId());
                exhibitionService.insertProspectUrl(busiProspect);
                prospect = exhibitionService.findProspectUrl(busiProspect.getProspectId(),exhibitionId) ;
            }
        }
        catch (Exception e)
        {
            log.error("保存勘展图片失败！", e);
            return error(e.getMessage());
        }
        return new AjaxResult(  AjaxResult.Type.SUCCESS,"保存勘展图片成功",prospect);
    }


    /**
     * 删除勘展图片
     */
    @Log(title = "删除勘展图片", businessType = BusinessType.DELETE)
    @PostMapping("/deleteProspectUrl")
    @ResponseBody
    public AjaxResult deleteProspectUrl (@RequestParam(value = "key") Long prospectId,@RequestParam(value = "exhibitionId") Long exhibitionId)
    {
        boolean result = false;
        try{
            BusiProspect busiProspect = exhibitionService.findProspectUrl(prospectId,exhibitionId);
            if(busiProspect != null){

                if(!"".equals(busiProspect.getProspectUrl()) && busiProspect.getProspectUrl() != null)
                {
                    result = FileUploadUtils.deleteFile(busiProspect.getProspectUrl().replace("/profile/prospectUrl",Global.getProspectUrlPath()));
                }

                if(!result){
                    new RuntimeException("删除图片失败");
                }else{
                    exhibitionService.deleteProspectUrl(busiProspect.getProspectId());
                }
            }
        }
        catch (Exception e)
        {
            log.error("删除图片失败！", e);
            return error(e.getMessage());
        }
        return success();
    }

    /**
     * 更新展会状态为勘展
     * @param exhibitionId 展会ID
     */
    @Log(title = "更新展会状态",businessType = BusinessType.UPDATE)
    @PostMapping("/updateStatus")
    @ResponseBody
    public AjaxResult updateStatus(@RequestParam (value = "exhibitionId") Long exhibitionId){
       return toAjax(exhibitionService.updateStatus(exhibitionId));
    }


    /**
     * 展会时间轴
     * @param id 展会ID
     */
    @GetMapping("/exhibitionTimeLine/{id}")
    public String exhibitionTimeLine(@PathVariable(value = "id") Long id,ModelMap mmp){
        mmp.put("exhibitionId",id);
        return prefix + "/timeline";
    }

    /**
     * 查询展会记录
     * @param exhibitionId 展会ID
     */
    @PostMapping("/selectExhibitionRecord")
    @ResponseBody
    public TableDataInfo selectExhibitionRecord(Long exhibitionId){
        startPage();
        List<BusiExhibitionRecord> list = exhibitionService.selectExhibitionRecord(exhibitionId);
        return getDataTable(list);
    }

    /**
     * 展会记录
     * @param record 记录实体
     */
    public Integer insertExhibitionRecord(BusiExhibitionRecord record){
        return exhibitionService.insertExhibitionRecord(record);
    }

    /**
     * 展会记录附件
     * @param recordAttached 附件实体
     */
    public Integer insertExhibitionRecordAttached(BusiExhibitionRecordAttached recordAttached){
        return exhibitionService.insertExhibitionRecordAttached(recordAttached);
    }


}