package com.wiscom.hence.job;

import com.alibaba.fastjson.JSONObject;
import com.wiscom.hence.constants.GlobalStaticMap;
import com.wiscom.hence.dao.LonLatDataMapper;
import com.wiscom.hence.utils.HttpUtilsTemplate;
import com.wiscom.hence.utils.TimeUtils;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class LoginCookieJob {
    @Resource
    private LonLatDataMapper busAlarmStatMapper;
    private static final Logger log = LoggerFactory.getLogger(LoginCookieJob.class);

    @XxlJob("loginCookieJob")
    public ReturnT<String> loginCookieJob(String param) throws Exception {
        XxlJobLogger.log("开始获取登陆接口cookie数据");
        String url = "http://wldc.ubd.chinaunicom.com/basicweb/auth/login";
        String cookie = HttpUtilsTemplate.postRequest(url);
        GlobalStaticMap.loginCookie = cookie;
        XxlJobLogger.log("获取登陆接口cookie数据成功");
        return ReturnT.SUCCESS;
    }
}
