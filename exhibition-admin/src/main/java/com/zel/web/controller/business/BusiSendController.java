package com.zel.web.controller.business;

import com.zel.business.domain.BusiMaterial;
import com.zel.business.domain.BusiSend;
import com.zel.business.domain.BusiSendMaterialDetial;
import com.zel.business.domain.dto.BusiSendMaterialDto;
import com.zel.business.service.IBusiExhibitionService;
import com.zel.business.service.IBusiMaterialService;
import com.zel.business.service.IBusiSendService;
import com.zel.common.annotation.Log;
import com.zel.common.core.controller.BaseController;
import com.zel.common.core.domain.AjaxResult;
import com.zel.common.core.domain.Ztree;
import com.zel.common.core.page.TableDataInfo;
import com.zel.common.enums.BusinessType;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;

/**
 * @description:发货信息
 * @auther: andy
 * @date: 2020/11/20
 */
@Controller
@RequestMapping("/business/send")
public class BusiSendController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(BusiSendController.class);

    private String prefix = "/Business/send";

    @Autowired
    private IBusiSendService sendService;

    @Autowired
    private IBusiExhibitionService exhibitionService;

    @Autowired
    private IBusiMaterialService materialService;

    @RequiresPermissions("business:send:view")
    @GetMapping()
    public String send() {
        return prefix + "/send";
    }

    /**
     * 获取发货列表
     *
     * @param send 发货信息
     */
    @RequiresPermissions("business:send:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BusiSend send) {
        startPage();
        List<BusiSend> list = sendService.selectSendList(send);
        return getDataTable(list);
    }

    /**
     * 新增发货
     */
    @GetMapping(value = "/add")
    public String add(ModelMap mmap) {
        mmap.put("exhibitionInfo", exhibitionService.selectExhibitionInfo());
        mmap.put("sendNumber", sendService.createSerialNumber());
        return prefix + "/add";
    }


    /**
     * 查询发货物料
     *
     * @param id
     */
    @GetMapping("/selectSendMaterial/{id}")
    public String selectSendMaterial(@PathVariable("id") Long id, ModelMap map) {
        map.put("id", id);
        Long exhibitionId = sendService.selectExhibitionId(id);
        map.put("exhibitionInfo", exhibitionService.selectExhibitionInfoById(exhibitionId));
        return prefix + "/sendMaterialDetail";
    }

    /**
     * 查询发货物料明细
     *
     * @param id
     */
    @PostMapping("/selectSendMaterialDetail")
    @ResponseBody
    public TableDataInfo selectSendMaterialDetail(@RequestParam(value = "id") Long id,
                                                  @RequestParam(value = "materialName", required = false) String materialName,
                                                  @RequestParam(value = "materialCode", required = false) String materialCode) {
        startPage();
        List<BusiSendMaterialDto> list = sendService.selectSendMaterialDetail(id, materialName, materialCode);
        return getDataTable(list);
    }

    /**
     * 查询可添加物料
     *
     * @param ids 已添加物料ID
     */
    @GetMapping({"/addMaterial/{ids}", "/addMaterial"})
    public String addMaterial(@PathVariable(value = "ids", required = false) Long[] ids, ModelMap map) {
        map.put("ids", ids);
        return prefix + "/addMaterial";
    }

    /**
     * 加载可添加物料列表
     */
    @PostMapping("/addMaterialList")
    @ResponseBody
    public TableDataInfo addMaterialList(@RequestParam(value = "ids[]", required = false) Long[] ids,
                                         @RequestParam(value = "materialName", required = false) String materialName,
                                         @RequestParam(value = "materialCode", required = false) String materialCode) {
        startPage();
        List<BusiMaterial> list = materialService.selectMaterial(ids, materialName, materialCode);
        return getDataTable(list);
    }


    /**
     * 保存发货信息
     *
     * @return
     */
    @Log(title = "保存发货信息", businessType = BusinessType.INSERT)
    @PostMapping("/saveSend")
    @ResponseBody
    public AjaxResult saveSend(@Validated BusiSend busiSend) {

        return toAjax(sendService.saveSend(busiSend));
    }

    /**
     * 查询人员Tree
     */
    @GetMapping(value = "/selectUserTree")
    public String selectUserTree(ModelMap mmap) {
        mmap.put("userTree", sendService.selectUserTree());
        return prefix + "/tree";
    }


    /**
     * 发货单选择收货人员 组织树查询
     * 根据parentId 父级id 关联子集
     *
     * @return
     */
    @GetMapping(value = "/sendZTree")
    @ResponseBody
    public List<Ztree> sendZTree() {
        return sendService.sendZTree();
    }

    /**
     * 删除发货信息
     *
     * @param ids
     */
    @PostMapping(value = "/remove")
    @ResponseBody
    public AjaxResult remove(Long ids[]) {

        return toAjax(sendService.remove(ids));
    }


    /**
     * 发货
     *
     * @param id
     */
    @PostMapping(value = "/send")
    @ResponseBody
    public AjaxResult send(Long id) {
        return toAjax(sendService.send(id));
    }


    /**
     * 加载修改发货信息
     *
     * @param id 发货ID
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, ModelMap mmp) {
        mmp.put("sendInfo", sendService.selectsendInfo(id));
        mmp.put("exhibitionInfo", exhibitionService.selectEditExhibitionInfo(id));
        return prefix + "/edit";
    }

    /**
     * 保存修改发货信息
     */
    @Log(title = "保存修改发货信息", businessType = BusinessType.UPDATE)
    @PostMapping("/saveEdit")
    @ResponseBody
    public AjaxResult saveEdit(@Validated BusiSend busiSend) {

        return toAjax(sendService.saveEdit(busiSend));
    }

    /**
     * 更新发货物料明细
     *
     * @param send 发货实体
     * @return 更新数量
     */
    @PostMapping("/updateSendMaterialDetail")
    @ResponseBody
    public AjaxResult updateSendMaterialDetail(BusiSend send) {
        return toAjax(sendService.updateSendMaterialDetail(send));
    }

}
