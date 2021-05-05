package com.wiscom.hence.job;

import com.alibaba.fastjson.JSONObject;
import com.wiscom.hence.constants.GlobalStaticMap;
import com.wiscom.hence.dao.PedestrianflowHourMapper;
import com.wiscom.hence.model.PedestrianflowHour;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

@Component
public class PedestrianflowHourJob {
    private static final Logger log = LoggerFactory.getLogger(PedestrianflowHourJob.class);
    @Resource
    private PedestrianflowHourMapper pedestrianflowHourMapper;

    @XxlJob("pedestrianflowHourJob")
    public ReturnT<String> pedestrianflowHourJob(String param) throws Exception {
        XxlJobLogger.log("开始统计每小时的人流量数据");
        String cookie = GlobalStaticMap.loginCookie;
        String elid = "kj0w9f0n";
        String screenId = "screen1608696333426";
        String url = new StringBuffer("http://wldc.ubd.chinaunicom.com/basicweb/regionalinsight/getPedestrianflowHour").
                append("?elid=" + elid).append("&screenId=" + screenId).toString();
        ResponseEntity<JSONObject> result = HttpUtilsTemplate.getRequest(url, cookie);
        if (null != result && result.getStatusCodeValue() == 200) {
            if (1003 == Integer.parseInt(result.getBody().get("code").toString())) {
                XxlJobLogger.log("统计每小时的人流量数据接口-pedestrianflowHourJob-登陆cookie失效");
            } else if (200 == Integer.parseInt(result.getBody().get("code").toString())) {
                ArrayList<LinkedHashMap> arrayList = (ArrayList) result.getBody().get("data");
                if (null != arrayList && arrayList.size() > 0) {
                    for (int l = 0; l < arrayList.size(); l++) {
                        LinkedHashMap resultMap = arrayList.get(l);
                        PedestrianflowHour pedestrianflowHour = new PedestrianflowHour();
                        Long totalPerson = Long.valueOf(resultMap.get("totalPerson").toString());
                        String elidResult = resultMap.get("elid").toString();
                        String timeFrame = resultMap.get("timeFrame").toString();
                        int externalPerson = Integer.parseInt(resultMap.get("externalPerson").toString());
                        int foreignPerson = Integer.parseInt(resultMap.get("foreignPerson").toString());
                        int residentPerson = Integer.parseInt(resultMap.get("residentPerson").toString());
                        int xinjiangPerson = Integer.parseInt(resultMap.get("xinjiangPerson").toString());
                        int xizangPerson = Integer.parseInt(resultMap.get("xizangPerson").toString());
                        Date updateTime = null;
                        try {
                            updateTime = TimeUtils.stringToDate3(resultMap.get("updateTime").toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        pedestrianflowHour.setElid(elidResult);
                        pedestrianflowHour.setExternalperson(externalPerson);
                        pedestrianflowHour.setForeignperson(foreignPerson);
                        pedestrianflowHour.setResidentperson(residentPerson);
                        pedestrianflowHour.setXinjiangperson(xinjiangPerson);
                        pedestrianflowHour.setXizangperson(xizangPerson);
                        pedestrianflowHour.setTimeframe(timeFrame);
                        pedestrianflowHour.setTotalperson(totalPerson);
                        pedestrianflowHour.setCreatetime(new Date());
                        pedestrianflowHour.setUpdatetime(updateTime);
                        pedestrianflowHourMapper.insert(pedestrianflowHour);
                    }
                    XxlJobLogger.log("统计每小时的人流量数据接口成功");
                } else {
                    XxlJobLogger.log("统计每小时的人流量数据接口结果为空");
                }
            }
        } else {
            XxlJobLogger.log("服务提供方系统异常");
        }
        return ReturnT.SUCCESS;
    }
}
