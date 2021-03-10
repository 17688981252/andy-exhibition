package com.zel.web.controller.business;

import com.zel.business.domain.BusiReturn;
import com.zel.business.domain.BusiReturnReceive;
import com.zel.business.domain.dto.BusiReturnMaterialDto;
import com.zel.business.service.IBusiReturnReceiveService;
import com.zel.common.core.controller.BaseController;
import com.zel.common.core.domain.AjaxResult;
import com.zel.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/business/returnReceive")
public class BuiReturnReceiveController extends BaseController {

    private String prefix = "/business/returnReceive";

    @Autowired
    private IBusiReturnReceiveService returnReceiveService;



    @GetMapping()
    public String returnReceive(){return prefix + "/returnReceive";}

    /**
     * 查询签收列表
     * @param returnReceive 退还签收实体
     * @return 签收list
     */
     @PostMapping("/list")
     @ResponseBody
     public TableDataInfo findReceivelist(BusiReturnReceive returnReceive){
        startPage();
        List<BusiReturn> list = returnReceiveService.findReceivelist(returnReceive);
        return getDataTable(list);
     }



    @GetMapping("/add")
    public String add(ModelMap mmp){
         mmp.put("exhibitionInfo",returnReceiveService.selectExhibitionInfo());
         mmp.put("returnReceiveNumber",returnReceiveService.createReturnReceiveNumber());
         return prefix +"/add";
    }


    /**
     * 查看退还未签收列表
     * @param exhibitionId 展会ID
     * @return 退还list
     */
    @PostMapping("/unReceiveList")
    @ResponseBody
    public TableDataInfo findUnReceiveList(@RequestParam(value = "exhibitionId") Long exhibitionId){
        startPage();
        List<BusiReturn> list = returnReceiveService.findUnReceiveList(exhibitionId);
        TableDataInfo result = getDataTable(list);
        return result;
    }

    /**
     * 物料界面
     * @param mmp
     * @return 物料URL
     */
    @GetMapping("/returnMaterial/{returnId}")
    private String materialDetail(@PathVariable("returnId")Long returnId,ModelMap mmp){
        mmp.put("returnId",returnId);
        mmp.put("status",returnReceiveService.seleceReturnReceiveStatus(returnId));
        return prefix + "/materialDetail";
    }

    /**
     * 查询退还物料
     * @param returnId 退还ID
     * @param materialName 物料名称
     * @param materialCode 物料代码
     */
    @PostMapping("/selectReturnReceiveMaterialDetail")
    @ResponseBody
    public TableDataInfo findReturnMaterial(@RequestParam(value = "returnId") Long returnId,
                                            @RequestParam(value = "materialName",required = false) String materialName,
                                            @RequestParam(value = "materialCode",required = false) String materialCode){
        startPage();
        List<BusiReturnMaterialDto> list = returnReceiveService.findReturnMaterial(returnId, materialName, materialCode);
        return getDataTable(list);
    }

    /**
     * 更新退还签收物料明细
     * @param busiReturn 退还实体
     */
    @PostMapping("/updateReturnMaterialDetail")
    @ResponseBody
    public AjaxResult updateReturnMaterialDetail(BusiReturn busiReturn){
        return toAjax(returnReceiveService.updateReturnMaterialDetail(busiReturn));
    }

    /**
     * 保存退还签收
     * @param returnReceive 退还签收实体
     */
    @PostMapping("/saveReturnReceive")
    @ResponseBody
    public AjaxResult saveReturnReceive(BusiReturnReceive returnReceive){
        return toAjax(returnReceiveService.saveReturnReceive(returnReceive));
    }

    /**
     * 删除退还签收
     * @param ids 退还签收id
     */
    @PostMapping("/deleteReturnReceive")
    @ResponseBody
    public AjaxResult deleteReturnReceive(Long[] ids){
        return toAjax(returnReceiveService.deleteReturnReceive(ids));
    }

    /**
     * 确认退还签收
     * @param returnReceiveId
     * @return
     */
    @PostMapping("/confirmReturnReceive")
    @ResponseBody
    public AjaxResult confirmReturnReceive(Long returnReceiveId){
        return toAjax(returnReceiveService.confirmReturnReceive(returnReceiveId));
    }
}
