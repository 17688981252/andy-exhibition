package com.zel.web.controller.business;

import com.zel.business.domain.BusiMaterial;
import com.zel.business.domain.BusiReturn;
import com.zel.business.service.IBusiReturnService;
import com.zel.common.core.controller.BaseController;
import com.zel.common.core.domain.AjaxResult;
import com.zel.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description: 退还物料
 * @auther:  andy
 * @date: 20210209
 */
@Controller
@RequestMapping("/business/return")
public class BusiReturnController extends BaseController {

    @Autowired
    private IBusiReturnService returnService;

    private String prefix = "business/return";

    @GetMapping()
    public String returnList(){return prefix + "/return";}

    /**
     * 查询退还列表
     * @param returnEntity 退还实体
     * @return 列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo selectReturnList(BusiReturn returnEntity){
        startPage();
        List<BusiReturn> list = returnService.selectReturnList(returnEntity);
        return getDataTable(list);
    }

    /**
     * 新增退还
     * @return 退还URL
     */
    @GetMapping("/add")
    public String add(ModelMap mmp){
        mmp.put("exhibitionInfo",returnService.selectReturnExhibitionInfo());
        mmp.put("returnNumber",returnService.createReturnNumber());
        return prefix + "/add";
    }

    /**
     * 保存退还信息
     * @param returnEntity 退还实体
     * @return 保存成功数
     */
    @PostMapping("/saveReturn")
    @ResponseBody
    public AjaxResult saveReturn(@Validated BusiReturn returnEntity){
        return toAjax(returnService.saveReturn(returnEntity));
    }

    /**
     * 删除退还信息
     * @param ids 退还id
     * @return 删除数
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult removeReturn(Long[] ids){
        return toAjax(returnService.removeReturn(ids));
    }

    /**
     * 查询退还物料明细
     * @param returnId 退还ID
     * @return 退还物料列表
     */
    @PostMapping("/selectReturnMaterialDetails")
    @ResponseBody
    public TableDataInfo selectRerurnMaterialDetial(Long returnId){
        startPage();
       List<BusiMaterial> list = returnService.selectRerurnMaterialDetial(returnId);
       return getDataTable(list);
    }


    @PostMapping("/returnMaterial")
    @ResponseBody
    public AjaxResult returnMaterial(Long[] ids){
        return toAjax(returnService.returnMaterial(ids));
    }




}
