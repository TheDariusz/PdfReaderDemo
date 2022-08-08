package com.thedariusz.pdfreaderdemo.repository;

import com.thedariusz.pdfreaderdemo.repository.entity.AlertEntity;
import com.thedariusz.pdfreaderdemo.repository.entity.VoivodeshipEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;

@SpringBootTest
@Testcontainers
class AlertRepositoryTest {

    @Autowired
    private AlertRepository alertRepository;

    @Autowired
    private VoivodeshipService voivodeshipService;

    @Test
    void shouldSaveMeteoAlertToTheBase() {

        VoivodeshipEntity voivodeshipEntity = voivodeshipService.findByName("MZ");

        AlertEntity alertEntity = new AlertEntity(123, LocalDateTime.now(), voivodeshipEntity);


        alertRepository.save(alertEntity);
    }
}