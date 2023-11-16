package com.example.demo.model;

import javax.persistence.*;


@Entity
public class TestBE extends NodeBE{

    public TestBE() {
    }












    @Column
    private String name;

    public TestBE(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
