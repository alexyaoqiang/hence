package com.wiscom.hence.model;

import java.util.Date;

public class LonLatData {
    private Long id;

    private String personlatlog;

    private String lantitude;

    private String longtitude;

    private Long personcount;

    private Date createtime;

    private Date updatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonlatlog() {
        return personlatlog;
    }

    public void setPersonlatlog(String personlatlog) {
        this.personlatlog = personlatlog == null ? null : personlatlog.trim();
    }

    public String getLantitude() {
        return lantitude;
    }

    public void setLantitude(String lantitude) {
        this.lantitude = lantitude == null ? null : lantitude.trim();
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude == null ? null : longtitude.trim();
    }

    public Long getPersoncount() {
        return personcount;
    }

    public void setPersoncount(Long personcount) {
        this.personcount = personcount;
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