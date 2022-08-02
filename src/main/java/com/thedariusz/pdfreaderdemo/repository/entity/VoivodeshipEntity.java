package com.thedariusz.pdfreaderdemo.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "voivodeship")
public class VoivodeshipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;


    public VoivodeshipEntity() {
    }

    public VoivodeshipEntity(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
