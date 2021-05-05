package com.wiscom.hence.job;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wiscom.hence.constants.GlobalStaticMap;
import com.wiscom.hence.dao.LonLatDataMapper;
import com.wiscom.hence.model.LonLatData;
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
import java.util.Map;

@Component
public class LatLonDataJob {
    private static final Logger log = LoggerFactory.getLogger(LatLonDataJob.class);
    @Resource
    private LonLatDataMapper lonLatDataMapper;

    @XxlJob("latLonDataJob")
    public ReturnT<String> latLonDataJob(String param) throws Exception {
        XxlJobLogger.log("开始获取打点经纬度数据");
        String cookie = GlobalStaticMap.loginCookie;
        String elid = "kj0w9f0n";
        String functionType = "6";
        String screenId = "screen1608696333426";
        String url = new StringBuffer("http://wldc.ubd.chinaunicom.com/basicweb/regionalinsight/getLatLonData").
                append("?elid=" + elid).append("&functionType=" + functionType).append("&screenId=" + screenId).toString();
        ResponseEntity<JSONObject> result = HttpUtilsTemplate.getRequest(url, cookie);
        if (null != result && result.getStatusCodeValue() == 200) {
            if (1003 == Integer.parseInt(result.getBody().get("code").toString())) {
                XxlJobLogger.log("打点经纬度数据接口-getLatLonData-登陆cookie失效");
            } else if (200 == Integer.parseInt(result.getBody().get("code").toString())) {
                LinkedHashMap mapResult = (LinkedHashMap) result.getBody().get("data");
                for (Object key : mapResult.keySet()) {
                    LonLatData lonLatData = new LonLatData();
                    String personLatLog = key.toString();
                    lonLatData.setPersonlatlog(personLatLog);
                    ArrayList<Map> list = (ArrayList) mapResult.get(key);
                    if (null != list && list.size() > 0) {
                        for (int i = 0; i < list.size(); i++) {
                            String longitude = list.get(i).get("longitude").toString();
                            String lantitude = list.get(i).get("latitude").toString();
                            long personCount = Long.valueOf(list.get(i).get("personCount").toString());
                            lonLatData.setLantitude(lantitude);
                            lonLatData.setLongtitude(longitude);
                            lonLatData.setPersoncount(personCount);
                            lonLatData.setCreatetime(new Date());
                            lonLatDataMapper.insert(lonLatData);
                        }
                        XxlJobLogger.log("获取打点经纬度数据接口成功");
                    } else {
                        XxlJobLogger.log("获取打点经纬度数据接口结果为空");
                    }
                }
            }
        } else {
            XxlJobLogger.log("服务提供方系统异常");
        }
        return ReturnT.SUCCESS;
    }
}
