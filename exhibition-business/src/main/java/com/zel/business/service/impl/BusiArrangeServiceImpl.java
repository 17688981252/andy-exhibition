package com.zel.business.service.impl;

import com.zel.business.domain.BusiArrange;
import com.zel.business.domain.BusiExhibition;
import com.zel.business.domain.BusiExhibitionRecord;
import com.zel.business.domain.BusiExhibitionRecordAttached;
import com.zel.business.domain.dto.BusiArrangeDto;
import com.zel.business.mapper.BusiArrangeMapper;
import com.zel.business.mapper.BusiExhibitionMapper;
import com.zel.business.service.IBusiArrangeService;
import com.zel.business.service.IBusiExhibitionService;
import com.zel.business.utils.ImageUtil;
import com.zel.common.config.Global;
import com.zel.common.enums.ExhibitionStatus;
import com.zel.common.utils.file.FileUploadUtils;
import com.zel.framework.util.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.hibernate.management.api.EhcacheStats;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Slf4j
@Service
public class BusiArrangeServiceImpl implements IBusiArrangeService {

    @Autowired
    private BusiArrangeMapper arrangeMapper;
    @Autowired
    private BusiExhibitionMapper exhibitionMapper;

    @Autowired
    private IBusiExhibitionService exhibitionService;

    /**
     * 查询布展列表
     *
     * @param arrangeDto 布展信息
     * @return
     */
    @Override
    public List<BusiExhibition> selectArrangeList(BusiArrangeDto arrangeDto) {
        return arrangeMapper.selectArrangeList(arrangeDto);
    }

    /**
     * 插入布展图片信息
     *
     * @param busiArrange 布展实体
     */
    @Override
    public int insertArrangeUrl(BusiArrange busiArrange) {
        return arrangeMapper.insertArrangeUrl(busiArrange);
    }

    /**
     * 查询布展信息
     *
     * @param arrangeId 布展ID
     */
    @Override
    public BusiArrange findArrangeUrl(Long arrangeId) {
        return arrangeMapper.findArrangeUrl(arrangeId);
    }

    /**
     * 查询展会信息
     *
     * @param exhibitionId 展会ID
     * @return 展会实体
     */
    @Override
    public BusiExhibition selectExhibitionInfo(Long exhibitionId) {
        return exhibitionMapper.selectExhibitionInfoById(exhibitionId);
    }

    /**
     * 删除布展图片
     *
     * @param arrangeId 布展ID
     * @return 是否删除成功
     */
    @Override
    public boolean deleteArrangeUrl(Long arrangeId) {
        boolean result = false;
        try {
            BusiArrange arrange = arrangeMapper.findArrangeUrl(arrangeId);
            String url = arrange.getArrangeUrl();
            if (StringUtils.isNotBlank(url)) {
                result = FileUploadUtils.deleteFile(url.replace("/profile/arrangeUrl", Global.getArrangeUrlPath()));
            }
            if (result == false) {
                new RuntimeException("删除图片失败");
            }
            if (result) {
                arrangeMapper.deleteArrangeUrl(arrangeId);
            }
        } catch (Exception e) {
            log.error("删除布展图片失败");
        }
        return result;
    }

    /**
     * 保存布展图片
     *
     * @param files        图片文件
     * @param exhibitionId 展会ID
     * @return
     */
    @Override
    public boolean saveArrangeUrl(MultipartFile[] files, Long exhibitionId) {
        boolean result = true;
        try {
            for (MultipartFile file : files) {
                //上传图片
                String arrangeUrl = FileUploadUtils.upload(Global.getArrangeUrlPath(), file);
                //生成缩略图
//                String fullPath = arrangeUrl.replace("/"+Global.getProfile(),Global.getDiskPre());

                String fullPath = StringUtils.replace(arrangeUrl,"/profile",Global.getDiskPre());
                String thumbImage = ImageUtil.thumbnailImage(fullPath,100,100,"thumb_",false);

                BusiArrange busiArrange = new BusiArrange(exhibitionId, file.getOriginalFilename(), arrangeUrl);
                busiArrange.setCreateBy(ShiroUtils.getSysUser().getUserId());
                busiArrange.setThumbImage(thumbImage);
                arrangeMapper.insertArrangeUrl(busiArrange);
            }
        } catch (Exception e) {
            log.error("保存勘布图片失败！");
            result = false;
        }
        return result;
    }

    /**
     * 更新展会状态为布展
     *
     * @param exhibitionId 展会ID
     * @return 受影响的条数
     */
    @Override
    public int updateExhibitionStatus(Long exhibitionId) {

        BusiExhibition exhibition = new BusiExhibition();
        exhibition.setExhibitionId(exhibitionId);
        exhibition.setStatus(ExhibitionStatus.ARRANGE.getCode());
        exhibition.setUpdateBy(ShiroUtils.getUserId());
        int count = exhibitionMapper.updateStatus(exhibition);
        if (count>0) {
            BusiExhibitionRecord record = new BusiExhibitionRecord();
            BusiExhibitionRecordAttached recordAttached = new BusiExhibitionRecordAttached();
            List<BusiArrange> arrangeList = arrangeMapper.selectArrangeInfo(exhibitionId);
            for (BusiArrange arrange : arrangeList) {
                record.setExhibitionId(exhibitionId);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(new Date());
                record.setEvent("布展并上传图片，时间："+date);
                record.setStatus(5);
                int count1 = exhibitionService.insertExhibitionRecord(record);
                if (count1>0) {
                    recordAttached.setExhibitionRecordId(record.getExhibitionRecordId());
                    recordAttached.setPictureUrl(arrange.getArrangeUrl());
                    recordAttached.setThumbImage(arrange.getThumbImage());
                    recordAttached.setFileName(arrange.getFileName());
                    exhibitionService.insertExhibitionRecordAttached(recordAttached);
                }
            }
        }
        return count;
    }
}
