package com.demo.mod.entity;

import sun.awt.image.IntegerComponentRaster;

import javax.persistence.*;

@Entity
@Table(name= "personnativ")
public class Personnativ {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String name;//人名
    @Column
    private Integer zjId;//政治上的
    @Column
    private Integer xzId;//职级
    @Column
    private Integer dpId;//所属部门
    @Column(length = 255)
    private String depList;//分管部门
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getZjId() {
        return zjId;
    }

    public void setZjId(Integer zjId) {
        this.zjId = zjId;
    }

    public Integer getXzId() {
        return xzId;
    }

    public void setXzId(Integer xzId) {
        this.xzId = xzId;
    }

    public Integer getDpId() {
        return dpId;
    }

    public void setDpId(Integer dpId) {
        this.dpId = dpId;
    }

    public String getDepList() {
        return depList;
    }

    public void setDepList(String depList) {
        this.depList = depList;
    }
}
