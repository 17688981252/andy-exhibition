package com.zel.business.service.impl;

import com.zel.business.domain.dto.BusiReceiveInDto;
import com.zel.business.domain.dto.BusiReceiveMaterialDto;
import com.zel.business.domain.dto.BusiRevokeDto;
import com.zel.business.mapper.BusiReceiveMapper;
import com.zel.business.mapper.BusiRevokeMapper;
import com.zel.business.service.IBusiRevokeService;
import com.zel.framework.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusiRevokeServiceImpl implements IBusiRevokeService {
    @Autowired
    BusiRevokeMapper revokeMapper;

    @Autowired
    BusiReceiveMapper receiveMapper;

    /**
     * 查询撤展列表
     * @param revokeDto  撤展DTO
     * @return 撤展列表
     */
    @Override
    public List<BusiRevokeDto> selectRevokeList(BusiRevokeDto revokeDto) {
        return revokeMapper.selectRevokeList(revokeDto);
    }

    /**
     * 撤展
     * @param ids 展会IDs
     * @return 撤展数
     */
    @Override
    public int revokeExhibition(Long[] ids) {
        Long updateBy = ShiroUtils.getSysUser().getUserId();
        return revokeMapper.updateExhibitionStatus(ids,updateBy);
    }

    @Override
    public List<BusiReceiveMaterialDto> selectReceiveMaterialDetial(Long exhibitionId) {
        Long sendId = revokeMapper.selectSendId(exhibitionId);
        List<BusiReceiveMaterialDto> list = receiveMapper.selectReceiveMaterialDetialList(sendId);
        return list;
    }
}
