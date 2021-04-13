package com.zel.web.controller.business;

import com.zel.business.service.IBusiExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {

    @Autowired
    private IBusiExhibitionService exhibitionService;

    /**
     * 定时任务
     *
     * 每日23:59 更新流水号
     */
    //每1分钟执行一次
//    @Scheduled(cron = "0 */1 *  * * * ")
    //每天23:59:59 执行一次
    @Scheduled(cron = "59 59 23 * * ? ")
    public int updateSerialNumber(){
        return exhibitionService.updateSerialNumber();
    }

}
