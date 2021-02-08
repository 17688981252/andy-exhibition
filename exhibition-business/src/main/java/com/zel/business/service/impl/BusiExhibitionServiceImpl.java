package com.zel.business.service.impl;

import com.zel.business.domain.BusiExhibition;
import com.zel.business.domain.BusiMaterial;
import com.zel.business.domain.BusiProspect;
import com.zel.business.mapper.BusiExhibitionMapper;
import com.zel.business.service.IBusiExhibitionService;
import com.zel.common.annotation.Log;
import com.zel.common.constant.UserConstants;
import com.zel.common.core.domain.AjaxResult;
import com.zel.common.enums.ExhibitionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusiExhibitionServiceImpl implements IBusiExhibitionService {
    @Autowired
    private BusiExhibitionMapper exhibitionMapper;

    /**
     * 检验展会名称是否唯一
     * @param exhibition 展会信息
     */
    @Override
    public String checkExhibitionNameUnique(BusiExhibition exhibition) {

        int count = exhibitionMapper.checkExhibitionNameUnique(exhibition);

        if (count> 0) {
            return UserConstants.EXHIBITION_NAME_NOT_UNIQUE;
        }else {
            return UserConstants.EXHIBITION_NAME_UNIQUE;
        }

    }

    /**
     * 保存新增展会
     * @param exhibition 展会信息
     */
    @Override
    public int insertExhibition(BusiExhibition exhibition) {
        return exhibitionMapper.insertExhibition(exhibition);
    }

    /**
     * 获取展会列表
     * @param exhibition 展会信息
     */
    @Override
    public List<BusiExhibition> selectExhibitionList(BusiExhibition exhibition) {
        return exhibitionMapper.selectExhibitionList(exhibition);
    }

    /**
     * 根据展会ID查询展会信息
     * @param exhibitionId 展会ID
     */
    @Override
    public BusiExhibition selectExhibitionById(Long exhibitionId) {
        return exhibitionMapper.selectExhibitionById(exhibitionId);
    }

    /**
     * 查询勘展信息
     * @param exhibitionId 展会ID
     */
    @Override
    public BusiExhibition selectProspect(Long exhibitionId) {
        return exhibitionMapper.selectProspect(exhibitionId);
    }

    /**
     * 保存修改展会信息
     * @param exhibition 展会信息
     */
    @Override
    public int updateExhibition(BusiExhibition exhibition) {
        return exhibitionMapper.updateExhibition(exhibition);
    }

    /**
     * 删除展会
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
     * @param
     */
    @Override
    public void deleteProspectUrl(Long prospectId) {
        exhibitionMapper.deleteProspectUrl(prospectId);
    }

    /**
     * 更新展会状态
     * @param exhibitionId 展会ID
     */
    @Override
    public int updateStatus(Long exhibitionId) {
        int status = ExhibitionStatus.PROSPECT.getCode();
        return exhibitionMapper.updateStatus(exhibitionId,status);
    }

    /**
     * 查询勘展图片
     * @param prospectId
     * @param exhibitionId
     * @return
     */
    @Override
    public BusiProspect findProspectUrl(Long prospectId, Long exhibitionId) {
        return exhibitionMapper.findProspectUrl(prospectId,exhibitionId);
}

    /**
     * 查询展会名称集合
     * @param
     */
    @Override
    public List<BusiExhibition> selectExhibitionInfo() {

        return exhibitionMapper.selectExhibitionInfo();
    }

    /**
     * 查询导出展会列表
     * @param ids
     */
    @Override
    public List<BusiExhibition> selectExportExhibitionList(Long[] ids) {
        return exhibitionMapper.selectExportExhibitionList(ids);
    }

    /**
     * 加载可修改展会信息
     * @param id
     */
    @Override
    public List<BusiExhibition> selectEditExhibitionInfo(Long id) {
        List<BusiExhibition> sendExhibition = exhibitionMapper.selectEditExhibitionInfo(id);
        BusiExhibition exhibition = exhibitionMapper.selectExhibition(id);

        for(BusiExhibition  se : sendExhibition){
            if(se.getExhibitionId().equals(exhibition.getExhibitionId()) ){
                se.setFlag(true);
                break;
            }
        }
        return sendExhibition;
    }
}
