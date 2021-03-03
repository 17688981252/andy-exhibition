package com.zel.business.mapper;

import com.zel.business.domain.BusiExhibition;
import com.zel.business.domain.BusiRevoke;
import com.zel.business.domain.dto.BusiRevokeDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusiRevokeMapper {

    List<BusiRevokeDto> selectRevokeList(BusiRevokeDto revokeDto);



    Long selectSendId(@Param(value = "exhibitionId") Long exhibitionId);


    /**
     * 插入撤展图片
     * @param busiRevoke
     * @return
     */
    int insertRevokeUrl(BusiRevoke busiRevoke);

    /**
     * 查询撤展Url
     * @param revokeId
     * @return
     */
    BusiRevoke findRevokeUrl(@Param(value = "revokeId") Long revokeId);

    void deleteRevokeUrl(@Param(value = "revokeId") Long revokeId);



    /**
     * 更新展会状态为撤展
     * @param exhibitionId 展会ID
     * @Param updateBy 更新人
     */
    int updateExhibitionStatus(@Param(value = "exhibitionId") Long exhibitionId,
                               @Param(value = "updateBy") Long updateBy);
}
