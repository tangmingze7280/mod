package com.demo.mod.entity;

import javax.persistence.*;

/**
 * 政治上的
 */
@Entity
public class JobInstructions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String name;//正处级。。。
    @Column(unique = true)
    private Integer zjId;//匹配职级的ID

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        id = id;
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
}
