package com.demo.mod.entity;

import javax.persistence.*;

@Entity
@Table(name = "responsie")
public class Responsie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 32)
    private String name;//职位名  院长w校长
    @Column
    private Integer pId;//上级id
    @Column(unique = true)
    private Integer xzId;//外键

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

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getXzId() {
        return xzId;
    }

    public void setXzId(Integer xzId) {
        this.xzId = xzId;
    }
}
