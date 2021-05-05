package com.wiscom.hence.job;

import com.alibaba.fastjson.JSONObject;
import com.wiscom.hence.constants.GlobalStaticMap;
import com.wiscom.hence.dao.PedestrianflowDayMapper;
import com.wiscom.hence.dao.PedestrianflowHourMapper;
import com.wiscom.hence.model.PedestrianflowDay;
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
public class PedestrianflowDayJob {
    private static final Logger log = LoggerFactory.getLogger(PedestrianflowDayJob.class);
    @Resource
    private PedestrianflowDayMapper pedestrianflowDayMapper;

    @XxlJob("pedestrianflowDayJob")
    public ReturnT<String> pedestrianflowDayJob(String param) throws Exception {
        XxlJobLogger.log("开始统计每天的人流量数据");
        String cookie = GlobalStaticMap.loginCookie;
        String elid = "kj0w9f0n";
        String screenId = "screen1608696333426";
        String url = new StringBuffer("http://wldc.ubd.chinaunicom.com/basicweb/regionalinsight/getPedestrianflowDay").
                append("?elid=" + elid).append("&screenId=" + screenId).toString();
        ResponseEntity<JSONObject> result = HttpUtilsTemplate.getRequest(url, cookie);
        if (null != result && result.getStatusCodeValue() == 200) {
            if (1003 == Integer.parseInt(result.getBody().get("code").toString())) {
                XxlJobLogger.log("统计每天的人流量数据接口-pedestrianflowDayJob-登陆cookie失效");
            } else if (200 == Integer.parseInt(result.getBody().get("code").toString())) {
                ArrayList<LinkedHashMap> arrayList = (ArrayList) result.getBody().get("data");
                if (null != arrayList && arrayList.size() > 0) {
                    for (int l = 0; l < arrayList.size(); l++) {
                        LinkedHashMap resultMap = arrayList.get(l);
                        PedestrianflowDay pedestrianflowDay = new PedestrianflowDay();
                        Long realTotalMax = Long.valueOf(resultMap.get("realTotalMax").toString());
                        String elidResult = resultMap.get("elid").toString();
                        Date day = TimeUtils.stringToDate4(resultMap.get("day").toString());
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
                        pedestrianflowDay.setElid(elidResult);
                        pedestrianflowDay.setExternalperson(externalPerson);
                        pedestrianflowDay.setForeignperson(foreignPerson);
                        pedestrianflowDay.setResidentperson(residentPerson);
                        pedestrianflowDay.setXinjiangperson(xinjiangPerson);
                        pedestrianflowDay.setXizangperson(xizangPerson);
                        pedestrianflowDay.setDay(day);
                        pedestrianflowDay.setRealtotalmax(realTotalMax);
                        pedestrianflowDay.setCreatetime(new Date());
                        pedestrianflowDay.setUpdatetime(updateTime);
                        pedestrianflowDayMapper.insert(pedestrianflowDay);
                    }
                    XxlJobLogger.log("统计每天的人流量数据接口成功");
                } else {
                    XxlJobLogger.log("统计每天的人流量数据接口结果为空");
                }
            }
        } else {
            XxlJobLogger.log("服务提供方系统异常");
        }
        return ReturnT.SUCCESS;
    }
}
