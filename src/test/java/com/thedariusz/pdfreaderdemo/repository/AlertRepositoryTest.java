package com.thedariusz.pdfreaderdemo.repository;

import com.thedariusz.pdfreaderdemo.repository.entity.AlertEntity;
import com.thedariusz.pdfreaderdemo.repository.entity.VoivodeshipEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;

@SpringBootTest
@ActiveProfiles("test-containers-flyway")
@Testcontainers
class AlertRepositoryTest {

    @Autowired
    private AlertRepository alertRepository;

    @Test
    void shouldSaveMeteoAlertToTheBase() {


        VoivodeshipEntity voivodeship = new VoivodeshipEntity();
        voivodeship.setCode("MZW");
        voivodeship.setName("Masovia");

        AlertEntity alertEntity = new AlertEntity(123, LocalDateTime.now(), null);


        alertRepository.save(alertEntity);
    }
}