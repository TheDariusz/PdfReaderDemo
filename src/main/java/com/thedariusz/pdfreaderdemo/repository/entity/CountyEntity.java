package com.thedariusz.pdfreaderdemo.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "county")
public class CountyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "voivodeship_id", referencedColumnName = "id")
    private VoivodeshipEntity voivodeship;

    private String name;

    @Column(name = "teryt_id")
    private String terytId;

    public VoivodeshipEntity getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(VoivodeshipEntity voivodeship) {
        this.voivodeship = voivodeship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerytId() {
        return terytId;
    }

    public void setTerytId(String terytId) {
        this.terytId = terytId;
    }
}
