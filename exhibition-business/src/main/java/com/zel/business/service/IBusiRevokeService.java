package com.zel.business.service;

import com.zel.business.domain.BusiExhibition;
import com.zel.business.domain.dto.BusiReceiveInDto;
import com.zel.business.domain.dto.BusiReceiveMaterialDto;
import com.zel.business.domain.dto.BusiRevokeDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IBusiRevokeService {

    /**
     * 查询撤展列表
     * @param revokeDto  撤展DTO
     * @return 撤展列表
     */
    List<BusiRevokeDto> selectRevokeList(BusiRevokeDto revokeDto);

//    /**
//     * 撤展
//     * @param ids 展会IDs
//     * @return 撤展数
//     */
//    int revokeExhibition(Long[] ids);

    /**
     * 查询收货物料明细
     * @param exhibitionId
     * @return
     */
    List<BusiReceiveMaterialDto> selectReceiveMaterialDetial(Long exhibitionId);

    /**
     * 查询撤展展会信息
     * @param exhibitionId 展会ID
     */
    BusiExhibition selectRevokeExhibitionInfo(Long exhibitionId);

    /**
     * 保存撤展图片
     *
     * @param files        图片信息
     * @param exhibitionId 展会ID
     * @return
     */
    boolean saveRevokeUrl(MultipartFile[] files, Long exhibitionId);

    /**
     * 删除撤展图片
     * @param revokeId 撤展ID
     * @return 是否删除成功
     */
    boolean deleteRevokeUrl(Long revokeId);

    /**
     * 更新展会状态为撤展
     * @param exhibitionId
     * @return
     */
    int updateExhibitionStatus(Long exhibitionId);
}
