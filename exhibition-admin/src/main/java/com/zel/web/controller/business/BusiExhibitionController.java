package com.zel.web.controller.business;

import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.zel.business.domain.BusiExhibition;
import com.zel.business.domain.BusiExhibitionRecord;
import com.zel.business.domain.BusiExhibitionRecordAttached;
import com.zel.business.domain.BusiProspect;
import com.zel.business.domain.dto.BusiExhibitionRecordDto;
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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:展会信息
 * @auther: andy
 * @date: 2020/11/02
 */
@Controller
@RequestMapping("/business/exhibition")
@Component("serialNumberTask")
public class BusiExhibitionController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(BusiExhibitionController.class);

    private String prefix = "business/exhibition";

    @Autowired
    private IBusiExhibitionService exhibitionService;

    @Autowired
    private  FastFileStorageClient storageclient;

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
        if(exhibition != null){
            Date startTime = exhibition.getStartTime();
            Date endTime = exhibition.getEndTime();
            int compareTo = startTime.compareTo(endTime);
            if(compareTo == 1){
                return error("“开始时间”不能大于“结束时间”");
            }
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
    @RequiresPermissions("business:exhibition:edit")
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
        if(exhibition != null){
            Date startTime = exhibition.getStartTime();
            Date endTime = exhibition.getEndTime();
            int compareTo = startTime.compareTo(endTime);
            if(compareTo == 1){
                return error("“开始时间”不能大于“结束时间”");
            }
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
     */
    @GetMapping("/prospect")
    public String prospect(){
        return "business/prospect/prospect";
    }

    /**
     * 勘展列表
     * @param exhibition
     */
    @PostMapping("/prospectList")
    @ResponseBody
    public TableDataInfo selectProspectList(BusiExhibition exhibition){
        List<BusiExhibition> list = exhibitionService.selectProspectList(exhibition);
        return getDataTable(list);
    }


    /**
     * 上传勘展图片
     * @param exhibitionId 展会ID
     */
    @RequiresPermissions("business:exhibition:prospect")
    @GetMapping(value = "/prospect/{exhibitionId}")
    public String prospect(@PathVariable("exhibitionId")Long exhibitionId,ModelMap map){
        map.put("prospect",exhibitionService.selectProspect(exhibitionId));
        return "business/prospect/prospectUrl";
    }

    /**
     * 查看勘展图片
     * @param exhibitionId 展会ID
     */
    @GetMapping(value = "prospectImageUrl/{id}")
    public String image(@PathVariable("id")Long exhibitionId,ModelMap map){
        map.put("prospect",exhibitionService.selectProspect(exhibitionId));
        return "business/prospect/image";
    }
    /**
     * 保存勘展图片
     */
    @Log(title = "勘展图片", businessType = BusinessType.INSERT)
    @PostMapping("/saveProspectUrl")
    @ResponseBody
    public AjaxResult saveProspectUrl(@RequestParam(value = "file_data") MultipartFile[] files,@RequestParam(value = "exhibitionId") Long exhibitionId)
    {
        boolean result = exhibitionService.saveProspectUrl(files, exhibitionId);
        if (!result) {
            return new AjaxResult(AjaxResult.Type.ERROR, "保存布展图片失败");
        } else {
            return new AjaxResult(AjaxResult.Type.SUCCESS, "保存布展图片成功",exhibitionService.selectProspectUrlList(exhibitionId));
        }
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
     * @param exhibitionId 展会ID
     */
    @GetMapping("/exhibitionTimeLine/{exhibitionId}")
    public String exhibitionTimeLine(@PathVariable(value = "exhibitionId") Long exhibitionId,ModelMap mmp){
        mmp.put("exhibitionId",exhibitionId);
        mmp.put("exhibitionRecord",exhibitionService.selectExhibitionRecord(exhibitionId));
        return prefix + "/timeline";
    }

    /**
     * 查询展会记录
     * @param exhibitionId 展会ID
     */
    @PostMapping("/selectExhibitionRecord")
    @ResponseBody
    public Map<Integer, List<BusiExhibitionRecordDto>> selectExhibitionRecord(Long exhibitionId){
        Map<Integer, List<BusiExhibitionRecordDto>> result = exhibitionService.selectExhibitionRecord(exhibitionId);
        return result;
    }

    /**
     * 展会记录
     * @param record 记录实体
     */
    public Integer insertExhibitionRecord(BusiExhibitionRecord record){
        return exhibitionService.insertExhibitionRecord(record);
    }

    /**
     * 更新展会记录
     * @param number
     */
    public void updateExhibitionRecordEvent(String number){
        exhibitionService.updateExhibitionRecordEvent(number);
    }

    /**
     * 展会记录附件
     * @param recordAttached 附件实体
     */
    public Integer insertExhibitionRecordAttached(BusiExhibitionRecordAttached recordAttached){
        return exhibitionService.insertExhibitionRecordAttached(recordAttached);
    }

    /**
     * 定时任务
     *
     * 每日23:59 更新流水号
     */
    @GetMapping("/updateSerialNumber")
    public int updateSerialNumber(){
//        System.out.println("执行定时任务：更新流水号");
        return exhibitionService.updateSerialNumber();
    }

}