package com.thedariusz.pdfreaderdemo.repository;

import com.thedariusz.pdfreaderdemo.repository.entity.AlertEntity;
import com.thedariusz.pdfreaderdemo.repository.entity.VoivodeshipEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

@SpringBootTest
@ActiveProfiles("test")
class AlertRepositoryIT {

    @Autowired
    private AlertRepository alertRepository;

    @Test
    public void shouldSaveMeteoAlertToTheBase() {
        VoivodeshipEntity voivodeship = new VoivodeshipEntity();
        voivodeship.setCode("MZW");
        voivodeship.setName("Masovia");

        AlertEntity alertEntity = new AlertEntity();
        alertEntity.setAlertNumber(12345);
        alertEntity.setVoivodeship(voivodeship);
        alertEntity.setPublishedDate(LocalDateTime.now());

        alertRepository.save(alertEntity);
    }
}