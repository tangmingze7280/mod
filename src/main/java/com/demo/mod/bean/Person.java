package com.demo.mod.bean;
public class Person {
    private Integer id;
    private String name;//人名
    private String position;//职位名
    private String rank;
    private String depName;
    private Integer zjId;//政治上的

    public Person() {
    }

    public Person(Integer id, String name, String position, String rank, String depName, Integer zjId, Integer xzId, Integer dpId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.rank = rank;
        this.depName = depName;
        this.zjId = zjId;
        this.xzId = xzId;
        this.dpId = dpId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    private Integer xzId;//职级号
    private Integer dpId;//部门

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", rank='" + rank + '\'' +
                ", zjId=" + zjId +
                ", xzId=" + xzId +
                ", dpId=" + dpId +
                '}';
    }
}
