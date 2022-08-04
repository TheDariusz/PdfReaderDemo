package com.thedariusz.pdfreaderdemo.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "alert_type")
public class AlertTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    public AlertTypeEntity() {
    }

    public AlertTypeEntity(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
