package com.thedariusz.pdfreaderdemo;

import com.thedariusz.pdfreaderdemo.model.MeteoAlert;
import com.thedariusz.pdfreaderdemo.repository.AlertEntityDto;
import com.thedariusz.pdfreaderdemo.repository.AlertRepository;
import com.thedariusz.pdfreaderdemo.repository.VoivodeshipService;
import com.thedariusz.pdfreaderdemo.repository.entity.AlertEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    private final AlertRepository alertRepository;
    private final VoivodeshipService voivodeshipService;

    public AlertService(AlertRepository alertRepository, VoivodeshipService voivodeshipService) {
        this.alertRepository = alertRepository;
        this.voivodeshipService = voivodeshipService;
    }

    public void saveAlerts(List<MeteoAlert> meteoAlerts) {
        meteoAlerts.stream()
                .map(this::getAlertEntity)
                .forEach(alertRepository::save);
    }

    private AlertEntity getAlertEntity(MeteoAlert meteoAlert) {
        AlertEntityDto alertEntityDto =
                new AlertEntityDto(meteoAlert.getAlertNumber(),
                        meteoAlert.getPublishedDate(),
                        voivodeshipService.findByName(meteoAlert.getVoivodeship().name()));
        return alertEntityDto.toEntity();
    }

}
