package com.wiscom.hence.job;

import com.alibaba.fastjson.JSONObject;
import com.wiscom.hence.constants.GlobalStaticMap;
import com.wiscom.hence.utils.HttpUtilsTemplate;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Component
public class ElidsJob {
    private static final Logger log = LoggerFactory.getLogger(ElidsJob.class);

    @XxlJob("elidsJob")
    public ReturnT<String> elidsJob(String param) throws Exception {
        XxlJobLogger.log("开始获取围栏打点接口数据");
        String cookie = GlobalStaticMap.loginCookie;
        String functionType = "6";
        String url = new StringBuffer("http://wldc.ubd.chinaunicom.com/basicweb/regionalinsight/getElids").
                append("?functionType=" + functionType).toString();
        ResponseEntity<JSONObject> result = HttpUtilsTemplate.getRequest(url, cookie);
        if (null != result && result.getStatusCodeValue() == 200) {
            if (1003 == Integer.parseInt(result.getBody().get("code").toString())) {
                XxlJobLogger.log("围栏打点接口-elidsJob-登陆cookie失效");
            } else if (200 == Integer.parseInt(result.getBody().get("code").toString())) {
                ArrayList<LinkedHashMap> list = (ArrayList) result.getBody().get("data");
                XxlJobLogger.log("围栏打点数据接口成功");
                System.out.println(list);
            }
        } else {
            XxlJobLogger.log("服务提供方系统异常");
        }
        return ReturnT.SUCCESS;
    }
}
