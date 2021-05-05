package com.wiscom.hence.job;

import com.alibaba.fastjson.JSONObject;
import com.wiscom.hence.constants.GlobalStaticMap;
import com.wiscom.hence.dao.GenderAnalysisMapper;
import com.wiscom.hence.model.GenderAnalysis;
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
public class GenderAnalysisJob {
    private static final Logger log = LoggerFactory.getLogger(GenderAnalysisJob.class);
    @Resource
    private GenderAnalysisMapper genderAnalysisMapper;

    @XxlJob("genderAnalysisJob")
    public ReturnT<String> genderAnalysisJob(String param) throws Exception {
        XxlJobLogger.log("开始获取性别分析接口数据");
        log.info("开始获取性别分析接口数据");
        String cookie = GlobalStaticMap.loginCookie;
        String elid = "kj0w9f0n";
        String screenId = "screen1608696333426";
        String url = new StringBuffer("http://wldc.ubd.chinaunicom.com/basicweb/regionalinsight/getGenderAnalysis").
                append("?elid=" + elid).append("&screenId=" + screenId).toString();
        ResponseEntity<JSONObject> result = HttpUtilsTemplate.getRequest(url, cookie);
        if (null != result && result.getStatusCodeValue() == 200) {
            if (1003 == Integer.parseInt(result.getBody().get("code").toString())) {
                XxlJobLogger.log("性别分析接口-genderAnalysisJob-登陆cookie失效");
            } else if (200 == Integer.parseInt(result.getBody().get("code").toString())) {
                ArrayList<LinkedHashMap> arrayList = (ArrayList) result.getBody().get("data");
                if (null != arrayList && arrayList.size() > 0) {
                    for (int l = 0; l < arrayList.size(); l++) {
                        LinkedHashMap resultMap = arrayList.get(l);
                        GenderAnalysis genderAnalysis = new GenderAnalysis();
                        Long totalFemale = Long.valueOf(resultMap.get("totalFemale").toString());
                        Long totalMale = Long.valueOf(resultMap.get("totalMale").toString());
                        String elidResult = resultMap.get("elid").toString();
                        String timeFrame = resultMap.get("timeFrame").toString();
                        int externalPersonFemale = Integer.parseInt(resultMap.get("externalPersonFemale").toString());
                        int externalPersonMale = Integer.parseInt(resultMap.get("externalPersonMale").toString());
                        int foreignPerson = Integer.parseInt(resultMap.get("foreignPerson").toString());
                        int residentPersonFemale = Integer.parseInt(resultMap.get("residentPersonFemale").toString());
                        int residentPersonMale = Integer.parseInt(resultMap.get("residentPersonMale").toString());
                        int xinjiangPersonFemale = Integer.parseInt(resultMap.get("xinjiangPersonFemale").toString());
                        int xinjiangPersonMale = Integer.parseInt(resultMap.get("xinjiangPersonMale").toString());
                        int xizangPersonFemale = Integer.parseInt(resultMap.get("xizangPersonFemale").toString());
                        int xizangPersonMale = Integer.parseInt(resultMap.get("xizangPersonMale").toString());
                        Date updateTime = null;
                        try {
                            updateTime = TimeUtils.stringToDate3(resultMap.get("updateTime").toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        genderAnalysis.setCreatetime(new Date());
                        genderAnalysis.setElid(elidResult);
                        genderAnalysis.setExternalpersonfemale(externalPersonFemale);
                        genderAnalysis.setForeignperson(foreignPerson);
                        genderAnalysis.setExternalpersonmale(externalPersonMale);
                        genderAnalysis.setResidentpersonfemale(residentPersonFemale);
                        genderAnalysis.setResidentpersonmale(residentPersonMale);
                        genderAnalysis.setTimeframe(timeFrame);
                        genderAnalysis.setXinjiangpersonfemale(xinjiangPersonFemale);
                        genderAnalysis.setXinjiangpersonmale(xinjiangPersonMale);
                        genderAnalysis.setXizangpersonfemale(xizangPersonFemale);
                        genderAnalysis.setXizangpersonmale(xizangPersonMale);
                        genderAnalysis.setUpdatetime(updateTime);
                        genderAnalysis.setTotalmale(totalMale);
                        genderAnalysis.setTotalfemale(totalFemale);
                        genderAnalysisMapper.insert(genderAnalysis);
                    }
                    XxlJobLogger.log("性别分析数据接口成功");
                } else {
                    XxlJobLogger.log("性别分析数据接口结果为空");
                }
            }
        } else {
            XxlJobLogger.log("服务提供方系统异常");
        }
        return ReturnT.SUCCESS;
    }
}
