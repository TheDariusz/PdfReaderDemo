package com.thedariusz.pdfreaderdemo.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "alert_status")
public class AlertStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
