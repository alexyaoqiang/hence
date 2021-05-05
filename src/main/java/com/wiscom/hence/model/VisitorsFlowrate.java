package com.wiscom.hence.model;

import java.util.Date;

public class VisitorsFlowrate {
    private Long id;

    private Integer hour;

    private Integer externalperson;

    private Integer foreignperson;

    private Integer residentperson;

    private Integer xinjiangperson;

    private Integer xizangperson;

    private Integer realtotal;

    private String timeframe;

    private Date createtime;

    private Date updatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getExternalperson() {
        return externalperson;
    }

    public void setExternalperson(Integer externalperson) {
        this.externalperson = externalperson;
    }

    public Integer getForeignperson() {
        return foreignperson;
    }

    public void setForeignperson(Integer foreignperson) {
        this.foreignperson = foreignperson;
    }

    public Integer getResidentperson() {
        return residentperson;
    }

    public void setResidentperson(Integer residentperson) {
        this.residentperson = residentperson;
    }

    public Integer getXinjiangperson() {
        return xinjiangperson;
    }

    public void setXinjiangperson(Integer xinjiangperson) {
        this.xinjiangperson = xinjiangperson;
    }

    public Integer getXizangperson() {
        return xizangperson;
    }

    public void setXizangperson(Integer xizangperson) {
        this.xizangperson = xizangperson;
    }

    public Integer getRealtotal() {
        return realtotal;
    }

    public void setRealtotal(Integer realtotal) {
        this.realtotal = realtotal;
    }

    public String getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(String timeframe) {
        this.timeframe = timeframe == null ? null : timeframe.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}