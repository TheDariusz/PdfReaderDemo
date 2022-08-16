package com.thedariusz.pdfreaderdemo.repository;

import com.thedariusz.pdfreaderdemo.repository.entity.AlertEntity;
import com.thedariusz.pdfreaderdemo.repository.entity.VoivodeshipEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
class AlertRepositoryTest {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private VoivodeshipService voivodeshipService;

    @Test
    void shouldSaveMeteoAlertToTheBase() {

        VoivodeshipEntity voivodeshipEntity = voivodeshipService.findByCode("MZ");

        AlertEntity alertEntity = new AlertEntity(123, LocalDateTime.now(), voivodeshipEntity);

        AlertEntity alert = alertRepository.save(alertEntity);

        assertNotNull(alert);
    }
}