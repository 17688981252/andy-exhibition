package com.zel.business.mapper;

import com.zel.business.domain.BusiArrange;
import com.zel.business.domain.BusiExhibition;
import com.zel.business.domain.dto.BusiArrangeDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface BusiArrangeMapper {

    /**
     * 查询布展列表
     * @param arrangeDto 布展信息
     * @return
     */
    List<BusiExhibition> selectArrangeList(BusiArrangeDto arrangeDto);

    /**
     * 插入布展图片信息
     * @param busiArrange 布展实体
     */
    int insertArrangeUrl(BusiArrange busiArrange);

    /**
     * 查询布展信息
     * @param arrangeId 布展ID
     */
    BusiArrange findArrangeUrl(@Param(value = "arrangeId") Long arrangeId);

    /**
     * 删除布展图片
     * @param arrangeId 布展ID
     */
    void deleteArrangeUrl(@Param(value = "arrangeId") Long arrangeId);

    /**
     * 更新展会状态为布展
     * @param exhibitionId 展会ID
     */
    int updateExhibitionStatus(@Param(value = "exhibitionId") Long exhibitionId);
}
