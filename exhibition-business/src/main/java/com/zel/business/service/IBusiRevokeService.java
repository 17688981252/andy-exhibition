package com.zel.business.service;

import com.zel.business.domain.dto.BusiReceiveInDto;
import com.zel.business.domain.dto.BusiReceiveMaterialDto;
import com.zel.business.domain.dto.BusiRevokeDto;

import java.util.List;

public interface IBusiRevokeService {

    /**
     * 查询撤展列表
     * @param revokeDto  撤展DTO
     * @return 撤展列表
     */
    List<BusiRevokeDto> selectRevokeList(BusiRevokeDto revokeDto);

    /**
     * 撤展
     * @param ids 展会IDs
     * @return 撤展数
     */
    int revokeExhibition(Long[] ids);

    List<BusiReceiveMaterialDto> selectReceiveMaterialDetial(Long exhibitionId);
}
