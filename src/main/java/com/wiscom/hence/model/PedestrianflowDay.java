package com.wiscom.hence.model;

import java.util.Date;

public class PedestrianflowDay {
    private Long id;

    private Date day;

    private String elid;

    private Integer externalperson;

    private Integer foreignperson;

    private Integer residentperson;

    private Integer xinjiangperson;

    private Integer xizangperson;

    private Long realtotalmax;

    private Date createtime;

    private Date updatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getElid() {
        return elid;
    }

    public void setElid(String elid) {
        this.elid = elid == null ? null : elid.trim();
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

    public Long getRealtotalmax() {
        return realtotalmax;
    }

    public void setRealtotalmax(Long realtotalmax) {
        this.realtotalmax = realtotalmax;
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