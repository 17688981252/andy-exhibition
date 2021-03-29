package com.zel.business.service;

import com.zel.business.domain.BusiExhibition;
import com.zel.business.domain.BusiExhibitionRecord;
import com.zel.business.domain.BusiExhibitionRecordAttached;
import com.zel.business.domain.BusiProspect;
import com.zel.business.domain.dto.BusiExhibitionRecordDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IBusiExhibitionService {

    /**
     * 检验展会名称是否唯一
     * @param exhibition
     */
    String checkExhibitionNameUnique(BusiExhibition exhibition);

    /**
     * 保存新增展会
     * @param exhibition 展会信息
     */
    int insertExhibition(BusiExhibition exhibition);


    /**
     * 获取展会列表
     * @param exhibition 展会信息
     */
    List<BusiExhibition> selectExhibitionList(BusiExhibition exhibition);

    /**
     * 根据展会ID查询展会信息
     * @param exhibitionId 展会ID
     */
    BusiExhibition selectExhibitionById(Long exhibitionId);

    /**
     * 查询勘展信息
     * @param exhibitionId 展会ID
     */
    BusiExhibition selectProspect(Long exhibitionId);

    /**
     * 保存修改展会信息
     * @param exhibition 展会信息
     */
    int updateExhibition(BusiExhibition exhibition);

    /**
     * 删除展会
     * @param exhibitionIds 展会ID
     */
    int deleteExhibition(Long[] exhibitionIds);

    /**
     * 保存勘展图片

     */
    int insertProspectUrl(BusiProspect busiProspect);



    /**
     * 删除勘展图片
     * 根据 prospectId
     * @param prospectId
     */
    void deleteProspectUrl(Long prospectId);

    /**
     * 更新展会状态为勘展
     * @param exhibitionId 展会ID
     */
    int updateStatus(Long exhibitionId);

    /**
     * 查询勘展图片
     * @param prospectId
     * @param exhibitionId
     * @return
     */
    BusiProspect findProspectUrl(Long prospectId, Long exhibitionId);

    /**
     * 查询展会名称集合
     * @param
     */
    List<BusiExhibition> selectExhibitionInfo();

    /**
     * 查询导出展会列表
     * @param ids
     * @return
     */
    List<BusiExhibition> selectExportExhibitionList(Long[] ids);

    /**
     * 加载可修改展会信息
     * @param id
     */
    List<BusiExhibition> selectEditExhibitionInfo(Long id);

    /**
     * 展会记录
     * @param record 记录实体
     */
    Integer insertExhibitionRecord(BusiExhibitionRecord record);

    /**
     * 展会记录附件
     * @param recordAttached 附件实体
     * @return
     */
    int insertExhibitionRecordAttached(BusiExhibitionRecordAttached recordAttached);

    /**
     * 查询展会记录
     * @param exhibitionId 展会ID
     */
    Map<Integer, List<BusiExhibitionRecordDto>> selectExhibitionRecord(Long exhibitionId);


    /**
     * 定时任务
     *
     * 每日23:59 更新流水号
     */
    Integer updateSerialUnmber();

    /**
     * 保存勘展图片
     * @param files
     * @param exhibitionId
     */
    boolean saveProspectUrl(MultipartFile[] files, Long exhibitionId);
}
