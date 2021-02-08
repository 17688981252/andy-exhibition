package com.zel.business.service;

import com.zel.business.domain.BusiArrange;
import com.zel.business.domain.BusiExhibition;
import com.zel.business.domain.dto.BusiArrangeDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IBusiArrangeService {


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
    BusiArrange findArrangeUrl(Long arrangeId);

    /**
     * 查询展会信息
     * @param exhibitionId 展会ID
     * @return 展会实体
     */
    BusiExhibition selectExhibitionInfo(Long exhibitionId);

    /**
     * 删除布展图片
     * @param arrangeId 布展ID
     * @return 是否删除成功
     */
    boolean deleteArrangeUrl(Long arrangeId);

    /**
     * 保存布展图片
     * @param files 图片文件
     * @param exhibitionId 展会ID
     * @return
     */
    boolean saveArrangeUrl(MultipartFile[] files, Long exhibitionId);

    /**
     * 更新展会状态为布展
     * @param exhibitionId 展会ID
     * @return 受影响的条数
     */
    int updateExhibitionStatus(Long exhibitionId);
}
