package com.wiscom.hence.job;

import com.alibaba.fastjson.JSONObject;
import com.wiscom.hence.constants.GlobalStaticMap;
import com.wiscom.hence.dao.PlaceOriginOutMapper;
import com.wiscom.hence.model.PlaceOriginOut;
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
public class PlaceOfOriginOutJob {
    private static final Logger log = LoggerFactory.getLogger(PlaceOfOriginOutJob.class);
    @Resource
    private PlaceOriginOutMapper placeOriginOutMapper;

    @XxlJob("placeOfOriginOutJob")
    public ReturnT<String> placeOfOriginOutJob(String param) throws Exception {
        XxlJobLogger.log("开始省外来源地数据分析数据");
        String cookie = GlobalStaticMap.loginCookie;
        String elid = "kj0w9f0n";
        String screenId = "screen1608696333426";
        String url = new StringBuffer("http://wldc.ubd.chinaunicom.com/basicweb/regionalinsight/getPlaceOfOriginOut").
                append("?elid=" + elid).append("&screenId=" + screenId).toString();
        ResponseEntity<JSONObject> result = HttpUtilsTemplate.getRequest(url, cookie);
        if (null != result && result.getStatusCodeValue() == 200) {
            if (1003 == Integer.parseInt(result.getBody().get("code").toString())) {
                XxlJobLogger.log("省外来源地数据分析数据接口-placeOfOriginOutJob-登陆cookie失效");
            } else if (200 == Integer.parseInt(result.getBody().get("code").toString())) {
                ArrayList<LinkedHashMap> arrayList = (ArrayList) result.getBody().get("data");
                if (null != arrayList && arrayList.size() > 0) {
                    for (int l = 0; l < arrayList.size(); l++) {
                        LinkedHashMap resultMap = arrayList.get(l);
                        PlaceOriginOut placeOriginOut = new PlaceOriginOut();
                        Long count = Long.valueOf(resultMap.get("count").toString());
                        String place = resultMap.get("place").toString();
                        placeOriginOut.setCount(count);
                        placeOriginOut.setPlace(place);
                        placeOriginOut.setCreatetime(new Date());
                        placeOriginOutMapper.insert(placeOriginOut);
                    }
                    XxlJobLogger.log("省外来源地数据分析数据接口成功");
                } else {
                    XxlJobLogger.log("省外来源地数据分析数据接口结果为空");
                }
            }
        } else {
            XxlJobLogger.log("服务提供方系统异常");
        }
        return ReturnT.SUCCESS;
    }

}
