package com.zel.business.service.impl;

import com.zel.business.domain.BusiArrange;
import com.zel.business.domain.BusiExhibition;
import com.zel.business.domain.BusiRevoke;
import com.zel.business.domain.dto.BusiReceiveInDto;
import com.zel.business.domain.dto.BusiReceiveMaterialDto;
import com.zel.business.domain.dto.BusiRevokeDto;
import com.zel.business.mapper.BusiArrangeMapper;
import com.zel.business.mapper.BusiExhibitionMapper;
import com.zel.business.mapper.BusiReceiveMapper;
import com.zel.business.mapper.BusiRevokeMapper;
import com.zel.business.service.IBusiRevokeService;
import com.zel.common.config.Global;
import com.zel.common.utils.StringUtils;
import com.zel.common.utils.file.FileUploadUtils;
import com.zel.framework.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Slf4j
public class BusiRevokeServiceImpl implements IBusiRevokeService {
    @Autowired
    BusiRevokeMapper revokeMapper;

    @Autowired
    BusiReceiveMapper receiveMapper;

    @Autowired
    BusiExhibitionMapper exhibitionMapper;

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
                String revokeUrl = FileUploadUtils.upload(Global.getRevokeUrlPath(), file);
                BusiRevoke busiRevoke = new BusiRevoke(exhibitionId, file.getOriginalFilename(), revokeUrl);
                busiRevoke.setCreateBy(ShiroUtils.getSysUser().getUserId());
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
        Long updateBy = ShiroUtils.getSysUser().getUserId();
        return revokeMapper.updateExhibitionStatus(exhibitionId,updateBy);
    }
}
