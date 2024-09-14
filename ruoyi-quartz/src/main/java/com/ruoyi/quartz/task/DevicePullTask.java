package com.ruoyi.quartz.task;

import com.ruoyi.quartz.domain.SysJob;
import com.ruoyi.quartz.service.ISysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName DeviceJobRunner
 * @Description 中台设备信息拉取定时任务
 * @Author JiangXiong
 * @Date 2024/4/22 20:12
 * @Version 1.0
 */
@Component
public class DevicePullTask implements CommandLineRunner {

    @Autowired
    private ISysJobService jobService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("123");
        // 添加中台设备信息拉取定时任务
        SysJob job = new SysJob();
        job.setConcurrent("1");
        job.setCronExpression("* * 0/1 * * ?");
        job.setInvokeTarget("ryTask.ryParams('ry')");
        job.setJobGroup("DEFAULT");
        job.setJobName("定时任务test321");
        job.setMisfirePolicy("2");
        job.setCreateBy("admin");
        jobService.insertJob(job);
    }
}
