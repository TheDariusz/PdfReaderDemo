package com.thedariusz.pdfreaderdemo;

import com.thedariusz.pdfreaderdemo.model.AlertStatus;
import com.thedariusz.pdfreaderdemo.model.LocalAlert;
import com.thedariusz.pdfreaderdemo.model.MeteoAlert;
import com.thedariusz.pdfreaderdemo.repository.*;
import com.thedariusz.pdfreaderdemo.repository.entity.AlertEntity;
import com.thedariusz.pdfreaderdemo.repository.entity.AlertStatusEntity;
import com.thedariusz.pdfreaderdemo.repository.entity.AlertTypeEntity;
import com.thedariusz.pdfreaderdemo.repository.entity.LocalAlertEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertService {

    private final AlertRepository alertRepository;
    private final VoivodeshipService voivodeshipService;
    private final AlertTypeRepository alertTypeRepository;
    private final AlertStatusEntityRepository alertStatusEntityRepository;
    private final LocalAlertRepository localAlertRepository;

    private static final Logger logger = LoggerFactory.getLogger(AlertService.class);

    public AlertService(AlertRepository alertRepository, VoivodeshipService voivodeshipService, AlertTypeRepository alertTypeRepository, AlertStatusEntityRepository alertStatusEntityRepository, LocalAlertRepository localAlertRepository) {
        this.alertRepository = alertRepository;
        this.voivodeshipService = voivodeshipService;
        this.alertTypeRepository = alertTypeRepository;
        this.alertStatusEntityRepository = alertStatusEntityRepository;
        this.localAlertRepository = localAlertRepository;
    }

    public void saveAlerts(List<MeteoAlert> meteoAlerts) {
        for (MeteoAlert meteoAlert : meteoAlerts) {
            Optional<AlertEntity> persistAlert = save(meteoAlert);
            if (persistAlert.isPresent()) {
                logger.info("Alert saved: {}", persistAlert.get());
                saveLocalAlerts(meteoAlert, persistAlert.get());
            } else {
                logger.info("Alert not saved");
            }
        }
    }

    private void saveLocalAlerts(MeteoAlert meteoAlert, AlertEntity alert) {
        Long voivodeshipId = alert.getVoivodeship().getId();
        meteoAlert.getLocalMeteoWarnings().stream()
                .map(localAlert -> getLocalAlertEntity(localAlert, voivodeshipId))
                .peek(localAlertEntity -> localAlertEntity.setAlert(alert))
                .forEach(localAlertRepository::save);
    }

    private Optional<AlertEntity> save(MeteoAlert meteoAlert) {
        try {
            return Optional.of(alertRepository.save(getAlertEntity(meteoAlert)));
        } catch (Exception e) {
            logger.warn("Alert already exists ({})", e.getMessage());
            return Optional.empty();
        }
    }

    private LocalAlertEntity getLocalAlertEntity(LocalAlert localAlert, Long voivodeshipId) {
        LocalAlertEntity localAlertEntity = new LocalAlertEntity();

        AlertTypeEntity alertTypeEntity = getAlertTypeEntity(localAlert.type());
        localAlertEntity.setAlertType(alertTypeEntity);

        AlertStatusEntity statusEntity = getStatusEntity(localAlert.status());
        localAlertEntity.setAlertStatus(statusEntity);

        localAlertEntity.setStartDate(localAlert.start());

        localAlertEntity.setCountyEntities(voivodeshipService.getCountyEntities(localAlert.counties(), voivodeshipId));

        return localAlertEntity;
    }

    private AlertStatusEntity getStatusEntity(AlertStatus.Status status) {
        return alertStatusEntityRepository.findByStatus(status.name().toLowerCase())
                .orElseThrow(() -> new IllegalArgumentException("Status " + status + " not found"));
    }

    private AlertTypeEntity getAlertTypeEntity(String type) {
        return alertTypeRepository.findByType(type)
                .orElseGet(() -> alertTypeRepository.save(new AlertTypeEntity(type)));
    }

    private AlertEntity getAlertEntity(MeteoAlert meteoAlert) {
        AlertEntityDto alertEntityDto =
                new AlertEntityDto(meteoAlert.getAlertNumber(),
                        meteoAlert.getPublishedDate(),
                        voivodeshipService.findByCode(meteoAlert.getVoivodeship().name()));
        return alertEntityDto.toEntity();
    }

}
