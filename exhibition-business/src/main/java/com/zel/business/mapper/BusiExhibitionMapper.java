package com.zel.business.mapper;

import com.zel.business.domain.BusiExhibition;
import com.zel.business.domain.BusiExhibitionRecord;
import com.zel.business.domain.BusiExhibitionRecordAttached;
import com.zel.business.domain.BusiProspect;
import com.zel.business.domain.dto.BusiExhibitionRecordDto;
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
     * 根据 prospectId
     * @param prospectId
     */
    int deleteProspectUrl(@Param(value = "prospectId") Long prospectId);

    /**
     * 更新展会状态
     * @param exhibition 展会实体
     */
    int updateStatus(BusiExhibition exhibition);
    /**
     * 查询勘展图片
     * @param prospectId
     * @param exhibitionId
     * @return
     */
    BusiProspect findProspectUrl(@Param(value = "prospectId")Long prospectId, @Param(value = "exhibitionId") Long exhibitionId);


    /**
     * 查询展会名称集合
     * @param
     */
    List<BusiExhibition> selectExhibitionInfo();

    /**
     * 查询导出展会列表
     * @param ids
     */
    List<BusiExhibition> selectExportExhibitionList(@Param(value = "ids") Long[] ids);

    /**
     * 加载可修改展会信息列表
     * @param id
     */
    List<BusiExhibition> selectEditExhibitionInfo(@Param(value = "id") Long id);

    BusiExhibition selectExhibition(@Param(value = "id")Long id);

    BusiExhibition selectExhibitionInfoById(@Param(value = "exhibitionId") Long exhibitionId);

    /**
     * 查询撤展展会信息
     * @param exhibitionId 展会ID
     */
    BusiExhibition selectRevokeExhibitionInfo(Long exhibitionId);

    int insertExhibitionRecord(BusiExhibitionRecord record);

    /**
     * 展会记录附件
     * @param recordAttached 附件实体
     */
    int insertExhibitionRecordAttached(BusiExhibitionRecordAttached recordAttached);

    /**
     * 查询勘展信息
     * @param exhibitionId 展会ID
     */
    List<BusiProspect> selectProsectInfo(@Param(value = "exhibitionId") Long exhibitionId);

    /**
     * 查询展会记录
     * @param exhibitionId 展会ID
     */
    List<BusiExhibitionRecordDto> selectExhibitionRecord(@Param(value = "exhibitionId") Long exhibitionId);

    /**
     * 定时任务
     *
     * 每日23:59 更新流水号
     */
    Integer updateSerialNumber();

    /**
     * 更新展会记录表
     * @param record  展会记录实体
     */
    int updateExhibitionRecord(BusiExhibitionRecord record);

    void updateExhibitionRecordEvent(@Param(value = "number") String number);

    /**
     * 查看勘展列表
     * @return
     * @param exhibition
     */
    List<BusiExhibition> selectProspectList(BusiExhibition exhibition);

    /**
     * 查询勘展图片List
     * @param exhibitionId
     */
    List<BusiExhibition> selectProspectUrlList(@Param(value = "exhibitionId") Long exhibitionId);
}
