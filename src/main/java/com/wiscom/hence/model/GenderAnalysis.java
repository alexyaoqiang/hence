package com.wiscom.hence.model;

import java.util.Date;

public class GenderAnalysis {
    private Long id;

    private String elid;

    private Integer externalpersonfemale;

    private Integer externalpersonmale;

    private Integer foreignperson;

    private Integer residentpersonfemale;

    private Integer residentpersonmale;

    private Integer xinjiangpersonfemale;

    private Integer xinjiangpersonmale;

    private Integer xizangpersonfemale;

    private Integer xizangpersonmale;

    private Long totalfemale;

    private Long totalmale;

    private String timeframe;

    private Date createtime;

    private Date updatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getElid() {
        return elid;
    }

    public void setElid(String elid) {
        this.elid = elid == null ? null : elid.trim();
    }

    public Integer getExternalpersonfemale() {
        return externalpersonfemale;
    }

    public void setExternalpersonfemale(Integer externalpersonfemale) {
        this.externalpersonfemale = externalpersonfemale;
    }

    public Integer getExternalpersonmale() {
        return externalpersonmale;
    }

    public void setExternalpersonmale(Integer externalpersonmale) {
        this.externalpersonmale = externalpersonmale;
    }

    public Integer getForeignperson() {
        return foreignperson;
    }

    public void setForeignperson(Integer foreignperson) {
        this.foreignperson = foreignperson;
    }

    public Integer getResidentpersonfemale() {
        return residentpersonfemale;
    }

    public void setResidentpersonfemale(Integer residentpersonfemale) {
        this.residentpersonfemale = residentpersonfemale;
    }

    public Integer getResidentpersonmale() {
        return residentpersonmale;
    }

    public void setResidentpersonmale(Integer residentpersonmale) {
        this.residentpersonmale = residentpersonmale;
    }

    public Integer getXinjiangpersonfemale() {
        return xinjiangpersonfemale;
    }

    public void setXinjiangpersonfemale(Integer xinjiangpersonfemale) {
        this.xinjiangpersonfemale = xinjiangpersonfemale;
    }

    public Integer getXinjiangpersonmale() {
        return xinjiangpersonmale;
    }

    public void setXinjiangpersonmale(Integer xinjiangpersonmale) {
        this.xinjiangpersonmale = xinjiangpersonmale;
    }

    public Integer getXizangpersonfemale() {
        return xizangpersonfemale;
    }

    public void setXizangpersonfemale(Integer xizangpersonfemale) {
        this.xizangpersonfemale = xizangpersonfemale;
    }

    public Integer getXizangpersonmale() {
        return xizangpersonmale;
    }

    public void setXizangpersonmale(Integer xizangpersonmale) {
        this.xizangpersonmale = xizangpersonmale;
    }

    public Long getTotalfemale() {
        return totalfemale;
    }

    public void setTotalfemale(Long totalfemale) {
        this.totalfemale = totalfemale;
    }

    public Long getTotalmale() {
        return totalmale;
    }

    public void setTotalmale(Long totalmale) {
        this.totalmale = totalmale;
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