package com.zel.web.controller.business;

import com.zel.business.domain.BusiExhibition;
import com.zel.business.domain.dto.BusiArrangeDto;
import com.zel.business.service.IBusiArrangeService;
import com.zel.common.core.controller.BaseController;
import com.zel.common.core.domain.AjaxResult;
import com.zel.common.core.page.TableDataInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description:布展信息
 * @auther: andy
 * @date: 2021/02/04
 */
@Controller
@RequestMapping("/business/arrange")
@Slf4j
public class BusiArrangeController extends BaseController {

    private final String prefix = "business/arrange";

    @Autowired
    private IBusiArrangeService arrangeService;

    /**
     * 布展
     * @return 布展URL
     */
    @GetMapping()
    @RequestMapping(method = RequestMethod.GET)
    public String arrange(){
        return prefix + "/arrange";
    }

    /**
     * 查询布展列表
     * @param arrangeDto  布展信息
     * @return 布展列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo selectArrangeList(BusiArrangeDto arrangeDto){
        startPage();
        List<BusiExhibition> list = arrangeService.selectArrangeList(arrangeDto);
        return getDataTable(list);
    }

    /**
     * 加载布展图片
     * @param exhibitionId 展会ID
     * @param mmap 返回信息
     * @return 返回前端页面路径
     */
    @GetMapping("/arrangeUrl/{exhibitionId}")
    public String arrangeUrl(@PathVariable("exhibitionId") Long exhibitionId, ModelMap mmap){
        mmap.put("arrange",arrangeService.selectExhibitionInfo(exhibitionId));
        return prefix + "/arrangeUrl";
    }


    /**
     * 保存布展图片
     * @param files 图片信息
     * @param exhibitionId 展会ID
     * @return
     */
    @PostMapping("/saveArrangeUrl")
    @ResponseBody
    public AjaxResult saveArrangeUrl(@RequestParam(value = "file_data") MultipartFile[] files, @RequestParam(value = "exhibitionId") Long exhibitionId)
    {
        boolean result = arrangeService.saveArrangeUrl(files,exhibitionId);
        if (!result) {
            return new AjaxResult(AjaxResult.Type.ERROR,"保存布展图片失败");
        }else {
            return new AjaxResult(AjaxResult.Type.SUCCESS,"保存布展图片成功");
        }
    }

    /**
     * 删除布展图片
     * @param arrangeId 布展
     * @return 是否删除成功
     */
    @PostMapping("/deleteArrangeUrl")
    @ResponseBody
    public AjaxResult deleteArrangeUrl(@RequestParam(value = "key") Long arrangeId){
        boolean result = arrangeService.deleteArrangeUrl(arrangeId);
        if (!result) {
            return new AjaxResult(AjaxResult.Type.ERROR,"删除布展图片失败");
        }else{
            return new AjaxResult(AjaxResult.Type.SUCCESS,"删除布展图片成功");
        }
    }

    /**
     * 更新展会状态为布展
     * @param exhibitionId 展会ID
     * @return 受影响的条数
     */
    @PostMapping("/updateExhibitionStatus")
    @ResponseBody
    public AjaxResult updateExhibitionStatus(@RequestParam(value = "exhibitionId") Long exhibitionId){
        return toAjax(arrangeService.updateExhibitionStatus(exhibitionId));
    }

}
