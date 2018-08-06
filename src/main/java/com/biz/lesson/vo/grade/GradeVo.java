package com.biz.lesson.vo.grade;

import java.io.Serializable;

public class GradeVo implements Serializable {

    private static final long serialVersionUID = -5781349197675281006L;
    private String id;//主键

    private Integer code;//班级编号

    private String className;//班级名

    private Integer numForHuman;//班级人数

    private Integer avgsorce;//平均分

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getNumForHuman() {
        return numForHuman;
    }

    public void setNumForHuman(Integer numForHuman) {
        this.numForHuman = numForHuman;
    }

    public Integer getAvgsorce() {
        return avgsorce;
    }

    public void setAvgsorce(Integer avgsorce) {
        this.avgsorce = avgsorce;
    }
}
