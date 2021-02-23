package com.zel.business.mapper;

import com.zel.business.domain.dto.BusiRevokeDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusiRevokeMapper {

    List<BusiRevokeDto> selectRevokeList(BusiRevokeDto revokeDto);

    /**
     * 撤展
     * @param ids 展会IDs
     * @param updateBy
     * @return 撤展数
     */
    int updateExhibitionStatus(@Param(value = "ids") Long[] ids,
                               @Param(value = "updateBy") Long updateBy);

    Long selectSendId(@Param(value = "exhibitionId") Long exhibitionId);
}
