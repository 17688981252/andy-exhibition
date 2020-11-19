package com.zel.business.mapper;

import com.zel.business.domain.BusiExhibition;
import com.zel.business.domain.BusiProspect;
import com.zel.common.annotation.Log;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusiExhibitionMapper {

    /**
     * 检验展会名称是否唯一
     *
     * @param exhibition 展会信息
     */
    int checkExhibitionNameUnique(BusiExhibition exhibition);

    /**
     * 保存新增展会
     *
     * @param exhibition 展会信息
     */
    int insertExhibition(BusiExhibition exhibition);

    /**
     * 获取展会列表
     *
     * @param exhibition 展会信息
     */
    List<BusiExhibition> selectExhibitionList(BusiExhibition exhibition);

    /**
     * 根据展会ID查询展会信息
     *
     * @param exhibitionId 展会ID
     */
    BusiExhibition selectExhibitionById(@Param(value = "exhibitionId") Long exhibitionId);

    /**
     * 查询勘展信息
     * @param exhibitionId 展会ID
     */
    BusiExhibition selectProspect(Long exhibitionId);

    /**
     * 保存修改展会信息
     *
     * @param exhibition 展会信息
     */
    int updateExhibition(BusiExhibition exhibition);

    /**
     * 删除展会
     *
     * @param exhibitionIds 展会ID
     */
    int deleteExhibition(@Param(value = "exhibitionIds") Long[] exhibitionIds);

    /**
     * 保存勘展图片

     */
    int insertProspectUrl(BusiProspect busiProspect);

    /**
     * 删除勘展图片
     * 根据 urlId
     * @param urlId
     */
    int deleteProspectUrl(Long urlId);

    /**
     * 更新展会状态
     * @param exhibitionId 展会ID
     */
    int updateStatus(@Param(value = "exhibitionId") Long exhibitionId,
                     @Param(value = "status") int status);

    /**
     * 查询勘展图片
     * @param urlId
     * @param exhibitionId
     * @return
     */
    BusiProspect findProspectUrl(@Param(value = "urlId")Long urlId, @Param(value = "exhibitionId") Long exhibitionId);
}
