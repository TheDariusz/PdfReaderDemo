package com.thedariusz.pdfreaderdemo.repository;

import com.thedariusz.pdfreaderdemo.repository.entity.AlertEntity;
import com.thedariusz.pdfreaderdemo.repository.entity.AlertStatusEntity;
import com.thedariusz.pdfreaderdemo.repository.entity.AlertTypeEntity;
import com.thedariusz.pdfreaderdemo.repository.entity.VoivodeshipEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

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

        AlertEntity alertEntity = new AlertEntity();
        alertEntity.setAlertNumber(12345);
        alertEntity.setVoivodeship(voivodeship);
        alertEntity.setPublishedDate(LocalDateTime.now());

        alertRepository.save(alertEntity);
    }
}