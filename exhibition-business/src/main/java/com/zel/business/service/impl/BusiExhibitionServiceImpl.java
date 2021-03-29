package com.zel.business.service.impl;

import com.zel.business.domain.*;
import com.zel.business.domain.dto.BusiExhibitionRecordDto;
import com.zel.business.mapper.BusiArrangeMapper;
import com.zel.business.mapper.BusiExhibitionMapper;
import com.zel.business.service.IBusiExhibitionService;
import com.zel.business.utils.ImageUtil;
import com.zel.common.config.Global;
import com.zel.common.constant.UserConstants;
import com.zel.common.core.domain.AjaxResult;
import com.zel.common.enums.ExhibitionStatus;
import com.zel.common.utils.StringUtils;
import com.zel.common.utils.file.FileUploadUtils;
import com.zel.framework.util.ShiroUtils;
import lombok.SneakyThrows;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.zel.common.config.datasource.DynamicDataSourceContextHolder.log;

@Service
public class BusiExhibitionServiceImpl implements IBusiExhibitionService {
    @Autowired
    private BusiExhibitionMapper exhibitionMapper;

    @Autowired
    private IBusiExhibitionService exhibitionService;

    @Autowired
    private BusiArrangeMapper arrangeMapper;


    /**
     * 检验展会名称是否唯一
     *
     * @param exhibition 展会信息
     */
    @Override
    public String checkExhibitionNameUnique(BusiExhibition exhibition) {

        int count = exhibitionMapper.checkExhibitionNameUnique(exhibition);

        if (count > 0) {
            return UserConstants.EXHIBITION_NAME_NOT_UNIQUE;
        } else {
            return UserConstants.EXHIBITION_NAME_UNIQUE;
        }

    }

    /**
     * 保存新增展会
     *
     * @param exhibition 展会信息
     */
    @Override
    public int insertExhibition(BusiExhibition exhibition) {
        int count = exhibitionMapper.insertExhibition(exhibition);
        if (count > 0) {
            BusiExhibitionRecord record = new BusiExhibitionRecord();
            record.setExhibitionId(exhibition.getExhibitionId());
            record.setEvent("创建展会：" + exhibition.getExhibitionName() + "  办展方：" + exhibition.getOrganizer() + "  展会地址：" + exhibition.getAddress()
                    + "  开始时间：" + exhibition.getStartTime() + "  结束时间：" + exhibition.getEndTime());
            record.setStatus(1);
            exhibitionService.insertExhibitionRecord(record);
        }
        return count;
    }

    /**
     * 获取展会列表
     *
     * @param exhibition 展会信息
     */
    @Override
    public List<BusiExhibition> selectExhibitionList(BusiExhibition exhibition) {
        return exhibitionMapper.selectExhibitionList(exhibition);
    }

    /**
     * 根据展会ID查询展会信息
     *
     * @param exhibitionId 展会ID
     */
    @Override
    public BusiExhibition selectExhibitionById(Long exhibitionId) {
        return exhibitionMapper.selectExhibitionById(exhibitionId);
    }

    /**
     * 查询勘展信息
     *
     * @param exhibitionId 展会ID
     */
    @Override
    public BusiExhibition selectProspect(Long exhibitionId) {
        return exhibitionMapper.selectProspect(exhibitionId);
    }

    /**
     * 保存修改展会信息
     *
     * @param exhibition 展会信息
     */
    @Override
    public int updateExhibition(BusiExhibition exhibition) {
        return exhibitionMapper.updateExhibition(exhibition);
    }

    /**
     * 删除展会
     *
     * @param exhibitionIds 展会ID
     */
    @Override
    public int deleteExhibition(Long[] exhibitionIds) {
        return exhibitionMapper.deleteExhibition(exhibitionIds);
    }

    /**
     * 保存勘展图片
     */
    @Override
    public int insertProspectUrl(BusiProspect busiProspect) {
        return exhibitionMapper.insertProspectUrl(busiProspect);
    }

    /**
     * 删除勘展图片
     * 根据 urlId
     *
     * @param
     */
    @Override
    public void deleteProspectUrl(Long prospectId) {
        exhibitionMapper.deleteProspectUrl(prospectId);
    }

    /**
     * 更新展会状态为勘展
     *
     * @param exhibitionId 展会ID
     */
    @Override
    public int updateStatus(Long exhibitionId) {
        int status = ExhibitionStatus.PROSPECT.getCode();
        int count = exhibitionMapper.updateStatus(exhibitionId, status);
        if (count > 0) {
            BusiExhibitionRecord record = new BusiExhibitionRecord();
            record.setExhibitionId(exhibitionId);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(new Date());
            record.setEvent("勘察展会并上传图片,时间：" + date);
            record.setStatus(2);
            exhibitionService.insertExhibitionRecord(record);
            BusiExhibitionRecordAttached recordAttached = new BusiExhibitionRecordAttached();
            recordAttached.setExhibitionRecordId(record.getExhibitionRecordId());
            List<BusiProspect> prospectList = exhibitionMapper.selectProsectInfo(exhibitionId);
            for (BusiProspect prospect : prospectList) {
                recordAttached.setPictureUrl(prospect.getProspectUrl());
                String thumbImage = prospect.getThumbImage();
                recordAttached.setThumbImage(thumbImage);
                recordAttached.setFileName(prospect.getFileName());
                exhibitionService.insertExhibitionRecordAttached(recordAttached);
            }
        }

        return count;
    }

    /**
     * 查询勘展图片
     *
     * @param prospectId
     * @param exhibitionId
     * @return
     */
    @Override
    public BusiProspect findProspectUrl(Long prospectId, Long exhibitionId) {
        return exhibitionMapper.findProspectUrl(prospectId, exhibitionId);
    }

    /**
     * 查询展会名称集合
     *
     * @param
     */
    @Override
    public List<BusiExhibition> selectExhibitionInfo() {

        return exhibitionMapper.selectExhibitionInfo();
    }

    /**
     * 查询导出展会列表
     *
     * @param ids
     */
    @Override
    public List<BusiExhibition> selectExportExhibitionList(Long[] ids) {
        return exhibitionMapper.selectExportExhibitionList(ids);
    }

    /**
     * 加载可修改展会信息
     *
     * @param id
     */
    @Override
    public List<BusiExhibition> selectEditExhibitionInfo(Long id) {
        List<BusiExhibition> sendExhibition = exhibitionMapper.selectEditExhibitionInfo(id);
        BusiExhibition exhibition = exhibitionMapper.selectExhibition(id);

        for (BusiExhibition se : sendExhibition) {
            if (se.getExhibitionId().equals(exhibition.getExhibitionId())) {
                se.setFlag(true);
                break;
            }
        }
        return sendExhibition;
    }

    /**
     * 展会记录
     *
     * @param record 记录实体
     */
    @Override
    public Integer insertExhibitionRecord(BusiExhibitionRecord record) {
        record.setCreateBy(ShiroUtils.getUserId());
        return exhibitionMapper.insertExhibitionRecord(record);
    }

    /**
     * 展会记录附件
     *
     * @param recordAttached 附件实体
     * @return
     */
    @Override
    public int insertExhibitionRecordAttached(BusiExhibitionRecordAttached recordAttached) {
        return exhibitionMapper.insertExhibitionRecordAttached(recordAttached);
    }

    /**
     * 查询展会记录
     *
     * @param exhibitionId 展会ID
     */
    @Override
    public Map<Integer, List<BusiExhibitionRecordDto>> selectExhibitionRecord(Long exhibitionId) {

        List<BusiExhibitionRecordDto> list = exhibitionMapper.selectExhibitionRecord(exhibitionId);

//        Map<Integer, List<BusiExhibitionRecordDto>> result = list.stream().collect(Collectors.groupingBy(e -> e.getStatus(), Collectors.toList()));
        Map<Integer, List<BusiExhibitionRecordDto>> result = list.stream().collect(Collectors.groupingBy(BusiExhibitionRecordDto::getStatus));

        return result;
    }

    /**
     * 定时任务
     * <p>
     * 每日23:59 更新流水号
     */
    @Override
    public Integer updateSerialUnmber() {
        return exhibitionMapper.updateSerialUnmber();
    }

    /**
     * 保存勘展图片
     * @param files
     * @param exhibitionId
     */
    @Override
    public boolean saveProspectUrl(MultipartFile[] files, Long exhibitionId) {
        boolean result = true;
        BusiProspect prospect = new BusiProspect();
        try
        {
            for(MultipartFile file:files)
            {
                //上传图片
                String prospectUrl = FileUploadUtils.upload(Global.getProspectUrlPath(), file);
                //生成缩略图
                String pre = "F:/zel_exhibition/uploadPath";
                String imageUrl = StringUtils.replace(prospectUrl, "/profile", pre);
                String thumbImage = ImageUtil.thumbnailImage(imageUrl, 100, 100, "thumb_", false);

//                String pre2 = "F:\\zel_exhibition\\uploadPath\\";
//
//                thumbImage.replace(pre2,"/profile");

                BusiProspect busiProspect = new BusiProspect(exhibitionId,file.getOriginalFilename(),prospectUrl);
                busiProspect.setCreateBy(ShiroUtils.getSysUser().getUserId());
                busiProspect.setThumbImage(thumbImage);
                exhibitionMapper.insertProspectUrl(busiProspect);
                prospect = exhibitionMapper.findProspectUrl(busiProspect.getProspectId(),exhibitionId) ;

                //file 转 multipartFile
//                MultipartFile multipartFile = null;
//                try {
//                    FileInputStream fileInputStream = new FileInputStream(fileThumb);
//                    multipartFile = new MockMultipartFile(fileThumb.getName(), fileThumb.getName(),
//                            ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                //上传缩略图并返回路径
//                String path = null;
//                try {
//                    path = FileUploadUtils.upload(Global.getProspectUrlPath(), multipartFile);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        }
        catch (Exception e)
        {
            log.error("保存勘布图片失败！");
            result = false;
        }
        return result;
    }
}
