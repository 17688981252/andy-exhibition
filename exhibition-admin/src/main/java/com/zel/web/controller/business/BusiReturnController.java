package com.zel.web.controller.business;

import com.zel.business.domain.BusiExhibition;
import com.zel.business.domain.BusiMaterial;
import com.zel.business.domain.BusiReturn;
import com.zel.business.domain.BusiSend;
import com.zel.business.domain.dto.BusiReturnMaterialDto;
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
//        mmp.put("exhibitionInfo",returnService.selectReturnExhibitionInfo());
//        mmp.put("returnNumber",returnService.createReturnNumber());
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

    /**
     * 查询未退还展会列表
     * @return
     */
    @PostMapping("/unReturnList")
    @ResponseBody
    public TableDataInfo selectUnreturnList(){
        startPage();
        List<BusiExhibition> list = returnService.selectUnreturnList();
        return getDataTable(list);
    }

    /**
     * 新增退还
     * @return
     */
    @GetMapping("/addReturn/{exhibitionId}")
    public String addReturn(ModelMap mmp){
        mmp.put("returnNumber",returnService.createReturnNumber());
//        mmp.put("exhibitionInfo",returnService.selectReturnExhibitionInfo(exhibitionId));
        mmp.put("exhibitionInfo",returnService.selectUnReturnExhibitionInfo());
        return prefix + "/returnInfo";
    }

    /**
     * 查看/编辑 退还物料明细
     * @param returnId 退还ID
     * @return URL路径
     */
    @GetMapping("/selectReturnMaterial/{returnId}")
    public String selectReturnMaterial(@PathVariable("returnId")Long returnId,ModelMap mmp){
        mmp.put("returnId",returnId);
        mmp.put("status",returnService.selectReturnStatus(returnId));
        return prefix + "/returnMaterialDetial";
    }

    @PostMapping("/selectReturnMaterialDetail")
    @ResponseBody
    public TableDataInfo selectReturnMaterialDetail(@RequestParam(value = "returnId") Long returnId,
                                                    @RequestParam(value = "materialName",required = false) String materialName,
                                                    @RequestParam(value = "materialCode",required = false) String materialCode){
        startPage();
//        List<BusiReturnMaterialDto> list = returnService.selectReceiveMaterialDetail(exhibitionId,materialName,materialCode);
        List<BusiReturnMaterialDto> list = returnService.selectReturnMaterialDetail(returnId,materialName,materialCode);
        return getDataTable(list);
    }

    /**
     * 更新收货物料明细
     * @param busiReturn 收货实体
     * @return 更新数量
     */
    @PostMapping("/updateReturnMaterialDetail")
    @ResponseBody
    public AjaxResult updateReturnMaterialDetail(BusiReturn busiReturn){
       return toAjax(returnService.updateReturnMaterialDetail(busiReturn));
    }

    /**
     * 确认退还
     * @param returnId 退还ID
     */
    @PostMapping("/confirmReturn")
    @ResponseBody
    public AjaxResult confirmReturn(Long returnId){
        return toAjax(returnService.confirmReturn(returnId));
    }



}
