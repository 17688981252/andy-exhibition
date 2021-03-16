package com.zel.business.service.impl;

import com.zel.business.domain.*;
import com.zel.business.domain.dto.BusiExhibitionRecordDto;
import com.zel.business.mapper.BusiArrangeMapper;
import com.zel.business.mapper.BusiExhibitionMapper;
import com.zel.business.service.IBusiExhibitionService;
import com.zel.common.constant.UserConstants;
import com.zel.common.enums.ExhibitionStatus;
import com.zel.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BusiExhibitionServiceImpl implements IBusiExhibitionService {
    @Autowired
    private BusiExhibitionMapper exhibitionMapper;

    @Autowired
    private IBusiExhibitionService exhibitionService;

    @Autowired
    private BusiArrangeMapper arrangeMapper;



    /**
     * 检验展会名称是否唯一
     *
     * @param exhibition 展会信息
     */
    @Override
    public String checkExhibitionNameUnique(BusiExhibition exhibition) {

        int count = exhibitionMapper.checkExhibitionNameUnique(exhibition);

        if (count > 0) {
            return UserConstants.EXHIBITION_NAME_NOT_UNIQUE;
        } else {
            return UserConstants.EXHIBITION_NAME_UNIQUE;
        }

    }

    /**
     * 保存新增展会
     *
     * @param exhibition 展会信息
     */
    @Override
    public int insertExhibition(BusiExhibition exhibition) {
        int count = exhibitionMapper.insertExhibition(exhibition);
        if (count > 0) {
            BusiExhibitionRecord record = new BusiExhibitionRecord();
            record.setExhibitionId(exhibition.getExhibitionId());
            record.setEvent("创建展会：" + exhibition.getExhibitionName() + "  办展方：" + exhibition.getOrganizer() + "  展会地址：" + exhibition.getAddress()
                    + "  开始时间：" + exhibition.getStartTime() + "  结束时间：" + exhibition.getEndTime());
            record.setStatus(1);
            exhibitionService.insertExhibitionRecord(record);
        }
        return count;
    }

    /**
     * 获取展会列表
     *
     * @param exhibition 展会信息
     */
    @Override
    public List<BusiExhibition> selectExhibitionList(BusiExhibition exhibition) {
        return exhibitionMapper.selectExhibitionList(exhibition);
    }

    /**
     * 根据展会ID查询展会信息
     *
     * @param exhibitionId 展会ID
     */
    @Override
    public BusiExhibition selectExhibitionById(Long exhibitionId) {
        return exhibitionMapper.selectExhibitionById(exhibitionId);
    }

    /**
     * 查询勘展信息
     *
     * @param exhibitionId 展会ID
     */
    @Override
    public BusiExhibition selectProspect(Long exhibitionId) {
        return exhibitionMapper.selectProspect(exhibitionId);
    }

    /**
     * 保存修改展会信息
     *
     * @param exhibition 展会信息
     */
    @Override
    public int updateExhibition(BusiExhibition exhibition) {
        return exhibitionMapper.updateExhibition(exhibition);
    }

    /**
     * 删除展会
     *
     * @param exhibitionIds 展会ID
     */
    @Override
    public int deleteExhibition(Long[] exhibitionIds) {
        return exhibitionMapper.deleteExhibition(exhibitionIds);
    }

    /**
     * 保存勘展图片
     */
    @Override
    public int insertProspectUrl(BusiProspect busiProspect) {
        return exhibitionMapper.insertProspectUrl(busiProspect);
    }

    /**
     * 删除勘展图片
     * 根据 urlId
     *
     * @param
     */
    @Override
    public void deleteProspectUrl(Long prospectId) {
        exhibitionMapper.deleteProspectUrl(prospectId);
    }

    /**
     * 更新展会状态为勘展
     *
     * @param exhibitionId 展会ID
     */
    @Override
    public int updateStatus(Long exhibitionId) {
        int status = ExhibitionStatus.PROSPECT.getCode();
        int count = exhibitionMapper.updateStatus(exhibitionId,status);
        if (count > 0) {
            BusiExhibitionRecord record = new BusiExhibitionRecord();
            record.setExhibitionId(exhibitionId);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(new Date());
            record.setEvent("勘察展会并上传图片,时间："+date);
            record.setStatus(2);
            exhibitionService.insertExhibitionRecord(record);
            BusiExhibitionRecordAttached recordAttached = new BusiExhibitionRecordAttached();
            recordAttached.setExhibitionRecordId(record.getExhibitionRecordId());
            List<BusiProspect> prospectList = exhibitionMapper.selectProsectInfo(exhibitionId);
            for (BusiProspect prospect : prospectList) {
                recordAttached.setPictureUrl(prospect.getProspectUrl());
                recordAttached.setFileName(prospect.getFileName());
                exhibitionService.insertExhibitionRecordAttached(recordAttached);
            }

        }

        return count;
    }

    /**
     * 查询勘展图片
     *
     * @param prospectId
     * @param exhibitionId
     * @return
     */
    @Override
    public BusiProspect findProspectUrl(Long prospectId, Long exhibitionId) {
        return exhibitionMapper.findProspectUrl(prospectId, exhibitionId);
    }

    /**
     * 查询展会名称集合
     *
     * @param
     */
    @Override
    public List<BusiExhibition> selectExhibitionInfo() {

        return exhibitionMapper.selectExhibitionInfo();
    }

    /**
     * 查询导出展会列表
     *
     * @param ids
     */
    @Override
    public List<BusiExhibition> selectExportExhibitionList(Long[] ids) {
        return exhibitionMapper.selectExportExhibitionList(ids);
    }

    /**
     * 加载可修改展会信息
     *
     * @param id
     */
    @Override
    public List<BusiExhibition> selectEditExhibitionInfo(Long id) {
        List<BusiExhibition> sendExhibition = exhibitionMapper.selectEditExhibitionInfo(id);
        BusiExhibition exhibition = exhibitionMapper.selectExhibition(id);

        for (BusiExhibition se : sendExhibition) {
            if (se.getExhibitionId().equals(exhibition.getExhibitionId())) {
                se.setFlag(true);
                break;
            }
        }
        return sendExhibition;
    }

    /**
     * 展会记录
     *
     * @param record 记录实体
     */
    @Override
    public Integer insertExhibitionRecord(BusiExhibitionRecord record) {
        record.setCreateBy(ShiroUtils.getUserId());
        return exhibitionMapper.insertExhibitionRecord(record);
    }

    /**
     * 展会记录附件
     * @param recordAttached 附件实体
     * @return
     */
    @Override
    public int insertExhibitionRecordAttached(BusiExhibitionRecordAttached recordAttached) {
        return exhibitionMapper.insertExhibitionRecordAttached(recordAttached);
    }

    /**
     * 查询展会记录
     * @param exhibitionId 展会ID
     */
    @Override
    public Map<Integer, List<BusiExhibitionRecordDto>> selectExhibitionRecord(Long exhibitionId) {

        List<BusiExhibitionRecordDto> list = exhibitionMapper.selectExhibitionRecord(exhibitionId);

//        Map<Integer, List<BusiExhibitionRecordDto>> result = list.stream().collect(Collectors.groupingBy(e -> e.getStatus(), Collectors.toList()));
        Map<Integer,List<BusiExhibitionRecordDto>> result = list.stream().collect(Collectors.groupingBy(BusiExhibitionRecordDto::getStatus));

        return result;
    }
}
