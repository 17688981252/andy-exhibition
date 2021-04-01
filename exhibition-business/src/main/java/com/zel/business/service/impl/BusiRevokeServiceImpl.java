package com.zel.business.service.impl;

import com.zel.business.domain.*;
import com.zel.business.domain.dto.BusiReceiveInDto;
import com.zel.business.domain.dto.BusiReceiveMaterialDto;
import com.zel.business.domain.dto.BusiRevokeDto;
import com.zel.business.mapper.BusiArrangeMapper;
import com.zel.business.mapper.BusiExhibitionMapper;
import com.zel.business.mapper.BusiReceiveMapper;
import com.zel.business.mapper.BusiRevokeMapper;
import com.zel.business.service.IBusiExhibitionService;
import com.zel.business.service.IBusiRevokeService;
import com.zel.business.utils.ImageUtil;
import com.zel.common.config.Global;
import com.zel.common.enums.ExhibitionStatus;
import com.zel.common.utils.StringUtils;
import com.zel.common.utils.file.FileUploadUtils;
import com.zel.framework.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class BusiRevokeServiceImpl implements IBusiRevokeService {
    @Autowired
    private BusiRevokeMapper revokeMapper;

    @Autowired
    private BusiReceiveMapper receiveMapper;

    @Autowired
    private BusiExhibitionMapper exhibitionMapper;

    @Autowired
    private IBusiExhibitionService exhibitionService;

    /**
     * 查询撤展列表
     * @param revokeDto  撤展DTO
     * @return 撤展列表
     */
    @Override
    public List<BusiRevokeDto> selectRevokeList(BusiRevokeDto revokeDto) {
        return revokeMapper.selectRevokeList(revokeDto);
    }



    @Override
    public List<BusiReceiveMaterialDto> selectReceiveMaterialDetial(Long exhibitionId) {
        Long sendId = revokeMapper.selectSendId(exhibitionId);
        List<BusiReceiveMaterialDto> list = receiveMapper.selectReceiveMaterialDetialList(sendId);
        return list;
    }

    /**
     * 查询撤展展会信息
     * @param exhibitionId 展会ID
     */
    @Override
    public BusiExhibition selectRevokeExhibitionInfo(Long exhibitionId) {
        return exhibitionMapper.selectRevokeExhibitionInfo(exhibitionId);
    }

    /**
     * 保存撤展图片
     *
     * @param files        图片信息
     * @param exhibitionId 展会ID
     * @return
     */
    @Override
    public boolean saveRevokeUrl(MultipartFile[] files, Long exhibitionId) {
        boolean result = true;
        try {
            for (MultipartFile file : files) {
                //上传图片
                String revokeUrl = FileUploadUtils.upload(Global.getRevokeUrlPath(), file);
                //生成缩略图
                String fullPath = StringUtils.replace(revokeUrl,"/profile",Global.getDiskPre());
                String thumbImage = ImageUtil.thumbnailImage(fullPath,100,100,Global.getThumbPre(),false);

                BusiRevoke busiRevoke = new BusiRevoke(exhibitionId, file.getOriginalFilename(), revokeUrl);
                busiRevoke.setCreateBy(ShiroUtils.getSysUser().getUserId());
                busiRevoke.setThumbImage(thumbImage);
                revokeMapper.insertRevokeUrl(busiRevoke);
            }
        } catch (Exception e) {
            log.error("保存撤展图片失败！");
            result = false;
        }
        return result;
    }

    /**
     * 删除撤展图片
     * @param revokeId 撤展ID
     * @return 是否删除成功
     */
    @Override
    public boolean deleteRevokeUrl(Long revokeId) {
        boolean result = false;
        BusiRevoke revoke = revokeMapper.findRevokeUrl(revokeId);
        String url = revoke.getRevokeUrl();

        try {
            if (StringUtils.isNotBlank(url)) {
                result = FileUploadUtils.deleteFile(url.replace("/profile/revokeUrl",Global.getRevokeUrlPath()));
            }
        } catch (Exception e) {
            log.error("删除失败");
        }
        if (result) {
            revokeMapper.deleteRevokeUrl(revokeId);
        }
        return result;
    }

    /**
     * 更新展会状态为撤展
     * @param exhibitionId
     * @return
     */
    @Override
    public int updateExhibitionStatus(Long exhibitionId) {
        //更新展会状态
        BusiExhibition exhibition = new BusiExhibition();
        exhibition.setExhibitionId(exhibitionId);
        exhibition.setStatus(ExhibitionStatus.REVOKE.getCode());
        exhibition.setUpdateBy(ShiroUtils.getUserId());
        int count = exhibitionMapper.updateStatus(exhibition);
        //展会记录
        if (count>0) {
            List<BusiRevoke> revokeList = revokeMapper.selectRevokeInfo(exhibitionId);
            BusiExhibitionRecord record = new BusiExhibitionRecord();
            for (BusiRevoke revoke : revokeList) {
                record.setExhibitionId(exhibitionId);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(new Date());
                record.setEvent("撤展并上传图片，时间："+date);
                record.setStatus(6);
                int count1 = exhibitionService.insertExhibitionRecord(record);
                if (count1>0) {
                    BusiExhibitionRecordAttached recordAttached = new BusiExhibitionRecordAttached();
                    recordAttached.setExhibitionRecordId(record.getExhibitionRecordId());
                    recordAttached.setPictureUrl(revoke.getRevokeUrl());
                    recordAttached.setThumbImage(revoke.getThumbImage());
                    recordAttached.setFileName(revoke.getFileName());
                    exhibitionService.insertExhibitionRecordAttached(recordAttached);
                }
            }
        }
        return count;
    }
}
