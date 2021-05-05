package com.wiscom.hence.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xxl.job.core.log.XxlJobLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：alex_yq
 * @date ：Created in 2019/10/15 10:01
 * @description：http请求工具类
 * @modified By：
 * * 说明：1、url: 请求地址；
 *         2、method: 请求类型(如：POST,PUT,DELETE,GET)；
 *         3、requestEntity: 请求实体，封装请求头，请求内容
 *         4、responseType: 响应类型，根据服务接口的返回类型决定
 *         5、uriVariables: url中参数变量值
 */

@Component
public  class HttpUtilsTemplate {
    private static final Logger log = LoggerFactory.getLogger(HttpUtilsTemplate.class);
    //exchange方法提供统一的方法模板进行四种请求：POST,PUT,DELETE,GET
    static RestTemplate restTemplate = RestTemplateUtil.getInstance();
   /* private static void disableSslVerification() {
        try
        {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }*/


    //restTemplate get请求
    public static ResponseEntity<JSONObject> getRequest(String url,String Cookie) {
        HttpHeaders headers = new HttpHeaders();
        //如果发送的参数数据是json数据的话，需要添加特殊的请求头　
       // headers.setContentType(MediaType.APPLICATION_JSON);
        //Cookie 值从登陆接口带过来
        headers.add("Cookie", Cookie);
        headers.add("referer", "http://wldc.ubd.chinaunicom.com/");
        //添加请求的实体类，这里第一个参数是要发送的参数，第二个参数是请求头里的数据
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<JSONObject> exchange = restTemplate.exchange(url, HttpMethod.GET, requestEntity, JSONObject.class);
        return exchange;
    }

    //restTemplate post请求,获取cookie
    public static String postRequest(String url) {
        HttpHeaders headers = new HttpHeaders();
        //如果发送的参数数据是json数据的话，需要添加特殊的请求头　
        //headers.setContentType(MediaType.APPLICATION_JSON);
        //外网用域名
        headers.add("referer", "http://wldc.ubd.chinaunicom.com/");
        //内网用
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("username", "lcxx-1214");
        paramMap.add("password", "lcxx@1214");
        paramMap.add("verifyCode", "13hs47abj8");
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(paramMap,headers);
        ResponseEntity<Map> exchange = restTemplate.postForEntity(url, httpEntity, Map.class);
        String loginCookie = exchange.getHeaders().get("Set-Cookie").get(0);
        return loginCookie;
    }
}
