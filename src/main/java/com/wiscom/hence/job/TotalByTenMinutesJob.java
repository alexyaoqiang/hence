package com.wiscom.hence.job;

import com.alibaba.fastjson.JSONObject;
import com.wiscom.hence.constants.GlobalStaticMap;
import com.wiscom.hence.dao.VisitorsFlowrateMapper;
import com.wiscom.hence.model.VisitorsFlowrate;
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
public class TotalByTenMinutesJob {
    private static final Logger log = LoggerFactory.getLogger(TotalByTenMinutesJob.class);
    @Resource
    private VisitorsFlowrateMapper visitorsFlowrateMapper;

    @XxlJob("totalByTenMinutesJob")
    public ReturnT<String> totalByTenMinutesJob(String param) throws Exception {
        XxlJobLogger.log("开始每十分钟获取人流量变化数据");
        String cookie = GlobalStaticMap.loginCookie;
        String elid = "kj0w9f0n";
        String functionType = "6";
        String screenId = "screen1608696333426";
        String url = new StringBuffer("http://wldc.ubd.chinaunicom.com/basicweb/regionalinsight/getTotalByTenMinutes").
                append("?elid=" + elid).append("&functionType=" + functionType).append("&screenId=" + screenId).toString();
        ResponseEntity<JSONObject> result = HttpUtilsTemplate.getRequest(url, cookie);
        if (null != result && result.getStatusCodeValue() == 200) {
            if (1003 == Integer.parseInt(result.getBody().get("code").toString())) {
                XxlJobLogger.log("每十分钟获取人流量变化数据接口-totalByTenMinutesJob-登陆cookie失效");
            } else if (200 == Integer.parseInt(result.getBody().get("code").toString())) {
                ArrayList<LinkedHashMap> arrayList = (ArrayList) result.getBody().get("data");
                if (null != arrayList && arrayList.size() > 0) {
                    for (int j = 0; j < arrayList.size(); j++) {
                        LinkedHashMap<String, ArrayList> resultMap = arrayList.get(j);
                        for (String key : resultMap.keySet()) {
                            ArrayList<LinkedHashMap> value = resultMap.get(key);
                            if (null != value && value.size() > 0) {
                                for (int k = 0; k < value.size(); k++) {
                                    LinkedHashMap data = value.get(k);
                                    VisitorsFlowrate visitorsFlowrate = new VisitorsFlowrate();
                                    int hour = Integer.parseInt(data.get("hour").toString());
                                    int foreignPerson = Integer.parseInt(data.get("foreignPerson").toString());
                                    int xinjiangPerson = Integer.parseInt(data.get("xinjiangPerson").toString());
                                    int residentPerson = Integer.parseInt(data.get("residentPerson").toString());
                                    int xizangPerson = Integer.parseInt(data.get("xizangPerson").toString());
                                    int externalPerson = Integer.parseInt(data.get("externalPerson").toString());
                                    int realTotal = Integer.parseInt(data.get("realTotal").toString());
                                    String timeFrame = data.get("timeFrame").toString();
                                    visitorsFlowrate.setHour(hour);
                                    visitorsFlowrate.setForeignperson(foreignPerson);
                                    visitorsFlowrate.setXinjiangperson(xinjiangPerson);
                                    visitorsFlowrate.setResidentperson(residentPerson);
                                    visitorsFlowrate.setXizangperson(xizangPerson);
                                    visitorsFlowrate.setExternalperson(externalPerson);
                                    visitorsFlowrate.setRealtotal(realTotal);
                                    visitorsFlowrate.setTimeframe(timeFrame);
                                    visitorsFlowrate.setCreatetime(new Date());
                                    visitorsFlowrateMapper.insert(visitorsFlowrate);
                                }
                            }
                        }
                    }
                    XxlJobLogger.log("每十分钟获取人流量变化数据接口成功");
                } else {
                    XxlJobLogger.log("每十分钟获取人流量变化数据接口结果为空");
                }

            }
        } else {
            XxlJobLogger.log("服务提供方系统异常");
        }
        return ReturnT.SUCCESS;
    }

}
