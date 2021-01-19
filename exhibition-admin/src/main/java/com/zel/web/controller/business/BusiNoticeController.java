package com.zel.web.controller.business;

import com.zel.business.domain.BusiSend;
import com.zel.business.domain.dto.BusiNoticeOutDto;
import com.zel.business.domain.dto.BusiSendMaterialDto;
import com.zel.business.service.IBusiNoticeService;
import com.zel.business.service.IBusiSendService;
import com.zel.common.core.controller.BaseController;
import com.zel.common.core.page.TableDataInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:到货通知
 * @auther: andy
 * @date: 2020/12/14
 */
@Controller
@RequestMapping("/business/notice")
public class BusiNoticeController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(BusiNoticeController.class);

    private String prefix = "/business/notice";

    @Autowired
    private IBusiNoticeService noticeService;

    @Autowired
    private IBusiSendService sendService;

    /**
     * 查询到货通知
     */
    @GetMapping()
    public String notice(){return prefix + "/notice";}

    /**
     * 加载到货通知列列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BusiSend send){
        startPage();
        List<BusiNoticeOutDto> list = new ArrayList<>();
        try{
            list  = noticeService.selectNoticeList(send);
        }catch (Exception e){
            e.getMessage();
        }

        return getDataTable(list);
    }


    @PostMapping("/selectSendMaterialDetail")
    @ResponseBody
    public TableDataInfo list(@RequestParam(value = "sendId")Long id,
                              @RequestParam(value = "materialName",required = false) String materialName,
                              @RequestParam(value = "materialCode",required = false) String materialCode){
        startPage();
        List<BusiSendMaterialDto> list = sendService.selectSendMaterialDetail(id,materialName,materialCode);
        return getDataTable(list);
    }


}
