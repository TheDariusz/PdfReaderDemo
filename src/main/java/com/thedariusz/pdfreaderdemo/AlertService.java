package com.thedariusz.pdfreaderdemo;

import com.thedariusz.pdfreaderdemo.model.MeteoAlert;
import com.thedariusz.pdfreaderdemo.repository.AlertEntityDto;
import com.thedariusz.pdfreaderdemo.repository.AlertRepository;
import com.thedariusz.pdfreaderdemo.repository.entity.AlertEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    private final AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public void saveAlerts(List<MeteoAlert> meteoAlerts) {
        meteoAlerts.stream()
                .map(this::getAlertEntity)
                .forEach(alertRepository::save);
    }

    private AlertEntity getAlertEntity(MeteoAlert meteoAlert) {
        AlertEntityDto.VoivodeshipEntityDto voivodeshipEntityDto =
                new AlertEntityDto.VoivodeshipEntityDto(meteoAlert.getVoivodeship().label, meteoAlert.getVoivodeship().name());
        AlertEntityDto alertEntityDto =
                new AlertEntityDto(meteoAlert.getAlertNumber(), meteoAlert.getPublishedDate(), voivodeshipEntityDto.toEntity());
        return alertEntityDto.toEntity();
    }

}
