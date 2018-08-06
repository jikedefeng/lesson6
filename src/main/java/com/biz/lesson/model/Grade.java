package com.biz.lesson.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stu_grade")
public class Grade extends Persistent {

    private static final long serialVersionUID = -6204196441829724900L;
    @Column
    private Integer code;//班级编号

    @Column(length = 20,nullable = false)
    private String className;//班级名

    @Column
    private Integer numForHuman;//班级人数

    @Column
    private Integer avgsorce;//平均分

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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
