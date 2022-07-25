package com.thedariusz.pdfreaderdemo.api;

import com.thedariusz.pdfreaderdemo.ImgwPdfService;
import com.thedariusz.pdfreaderdemo.model.MeteoAlert;
import com.thedariusz.pdfreaderdemo.repository.AlertRepository;
import com.thedariusz.pdfreaderdemo.repository.VoivodeshipRepository;
import com.thedariusz.pdfreaderdemo.repository.entity.AlertEntity;
import com.thedariusz.pdfreaderdemo.repository.entity.VoivodeshipEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/alerts")
public class PdfController {

    private final ImgwPdfService imgwPdfService;
    private final AlertRepository alertRepository;
    private final VoivodeshipRepository voivodeshipRepository;

    public PdfController(ImgwPdfService imgwPdfService, AlertRepository alertRepository, VoivodeshipRepository voivodeshipRepository) {
        this.imgwPdfService = imgwPdfService;
        this.alertRepository = alertRepository;
        this.voivodeshipRepository = voivodeshipRepository;
    }

    @GetMapping("/actual")
    public ModelAndView getActualAlerts() {
        Map<String, Object> modelMap = new HashMap<>();
        List<String> textAlerts = imgwPdfService.fetchTextAlerts();
        List<MeteoAlert> meteoAlerts = textAlerts.stream()
                .map(MeteoAlert::new)
                .toList();
        modelMap.put("listOfAlerts", meteoAlerts);

        AlertEntity alertEntity = new AlertEntity();
        alertEntity.setAlertNumber(12345);
        alertEntity.setPublishedDate(LocalDateTime.now());
        VoivodeshipEntity voivodeship = new VoivodeshipEntity("MZW", "Masovia");
        voivodeshipRepository.save(voivodeship);

        alertEntity.setVoivodeship(voivodeship);

        alertRepository.save(alertEntity);
        return new ModelAndView("actual", modelMap);
    }
}
