package com.wiscom.hence.job;

import com.alibaba.fastjson.JSONObject;
import com.wiscom.hence.constants.GlobalStaticMap;
import com.wiscom.hence.dao.ReligionAgeMapper;
import com.wiscom.hence.model.ReligionAge;
import com.wiscom.hence.utils.HttpUtilsTemplate;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

@Component
public class InsightAgeDistrubJob {
    private static final Logger log = LoggerFactory.getLogger(InsightAgeDistrubJob.class);
    @Resource
    private ReligionAgeMapper religionAgeMapper;

    @XxlJob("insightAgeDistrubJob")
    public ReturnT<String> insightAgeDistrubJob(String param) throws Exception {
        XxlJobLogger.log("开始获取区域人口年龄分布接口数据");
        String cookie = GlobalStaticMap.loginCookie;
        String elid = "kj0w9f0n";
        String screenId = "screen1608696333426";
        String url = new StringBuffer("http://wldc.ubd.chinaunicom.com/basicweb/regionalinsight/getInsightAgeDistribution").
                append("?elid=" + elid).append("&screenId=" + screenId).toString();
        ResponseEntity<JSONObject> result = HttpUtilsTemplate.getRequest(url, cookie);
        if (null != result && result.getStatusCodeValue() == 200) {
            if (1003 == Integer.parseInt(result.getBody().get("code").toString())) {
                XxlJobLogger.log("获取区域人口年龄分布接口数据-insightAgeDistrubJob-登陆cookie失效");
            } else if (200 == Integer.parseInt(result.getBody().get("code").toString())) {
                ArrayList<LinkedHashMap> arrayList = (ArrayList) result.getBody().get("data");
                if (null != arrayList && arrayList.size() > 0) {
                    for (int n = 0; n < arrayList.size(); n++) {
                        LinkedHashMap resultMap = arrayList.get(n);
                        ReligionAge religionAge = new ReligionAge();
                        Long ageNum = Long.valueOf(resultMap.get("ageNum").toString());
                        String age = resultMap.get("age").toString();
                        religionAge.setAge(age);
                        religionAge.setAgenum(ageNum);
                        religionAge.setCreatetime(new Date());
                        religionAgeMapper.insert(religionAge);
                    }
                    XxlJobLogger.log("获取区域人口年龄分布接口数据成功");
                } else {
                    XxlJobLogger.log("获取区域人口年龄分布接口数据结果为空");
                }
            }
        } else {
            XxlJobLogger.log("服务提供方系统异常");
        }
        return ReturnT.SUCCESS;
    }
}
