package com.thedariusz.pdfreaderdemo.repository.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "local_meteo_alert")
public class LocalAlertEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "meteo_alert_id", referencedColumnName = "id")
    private AlertEntity alert;

    @ManyToOne
    @JoinColumn(name = "alert_type_id", referencedColumnName = "id")
    private AlertTypeEntity alertType;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "local_alert_county",
            joinColumns = @JoinColumn(name = "local_meteo_alert_id"),
            inverseJoinColumns = @JoinColumn(name = "county_id"))
    private Set<CountyEntity> countyEntities = new HashSet<>();

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "stop_date")
    private LocalDateTime stopDate;
    private String description;
    private String sms;
    private int degree;

    public AlertTypeEntity getAlertType() {
        return alertType;
    }

    public void setAlertType(AlertTypeEntity alertType) {
        this.alertType = alertType;
    }

    public AlertEntity getAlert() {
        return alert;
    }

    public void setAlert(AlertEntity alert) {
        this.alert = alert;
    }

    public Set<CountyEntity> getCountyEntities() {
        return countyEntities;
    }

    public void setCountyEntities(Set<CountyEntity> countyEntities) {
        this.countyEntities = countyEntities;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getStopDate() {
        return stopDate;
    }

    public void setStopDate(LocalDateTime stopDate) {
        this.stopDate = stopDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
}
