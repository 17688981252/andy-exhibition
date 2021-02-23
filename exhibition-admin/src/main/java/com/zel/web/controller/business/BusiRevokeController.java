package com.zel.web.controller.business;
import com.zel.business.domain.dto.BusiReceiveInDto;
import com.zel.business.domain.dto.BusiReceiveMaterialDto;
import com.zel.business.domain.dto.BusiRevokeDto;
import com.zel.business.service.IBusiRevokeService;
import com.zel.common.core.controller.BaseController;
import com.zel.common.core.domain.AjaxResult;
import com.zel.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description: 撤展
 * @auther: andy
 * @date: 2020/02/08
 */
@Controller
@RequestMapping("/business/revoke")
public class BusiRevokeController extends BaseController {

    @Autowired
    IBusiRevokeService revokeService;

    private final String prefix = "business/revoke";

    /**
     * 撤展
     * @return 撤展Url
     */
    @GetMapping()
    public String revoke(){return prefix +"/revoke";}

    /**
     * 查询撤展列表
     * @param revokeDto  撤展DTO
     * @return 撤展列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo selectRevokeList(BusiRevokeDto revokeDto){
        startPage();
        List<BusiRevokeDto> list = revokeService.selectRevokeList(revokeDto);
        return  getDataTable(list);
    }

    /**
     * 撤展
     * @param ids 展会IDs
     * @return 撤展数
     */
    @PostMapping("/revokeUrl")
    @ResponseBody
    public AjaxResult revokeExhibition(@RequestParam("ids")Long[] ids){
        return toAjax(revokeService.revokeExhibition(ids));
    }

    /**
     * 查看收货物料明细
     * @param exhibitionId 展会ID
     * @return 明细列表
     */
    @PostMapping("/selectReceiveMaterialDetial")
    @ResponseBody
    public TableDataInfo list(@RequestParam(value = "exhibitionId")Long exhibitionId){
        startPage();
        List<BusiReceiveMaterialDto> list = revokeService.selectReceiveMaterialDetial(exhibitionId);
        return getDataTable(list);
    }
}
