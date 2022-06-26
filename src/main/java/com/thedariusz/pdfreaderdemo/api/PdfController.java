package com.thedariusz.pdfreaderdemo.api;

import com.thedariusz.pdfreaderdemo.ImgwAlertMapper;
import com.thedariusz.pdfreaderdemo.ImgwPdfService;
import com.thedariusz.pdfreaderdemo.model.ImgwMeteoWarning;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/alerts")
public class PdfController {

    private final ImgwPdfService imgwPdfService;
    private final ImgwAlertMapper imgwAlertMapper;

    public PdfController(ImgwPdfService imgwPdfService, ImgwAlertMapper imgwAlertMapper) {
        this.imgwPdfService = imgwPdfService;
        this.imgwAlertMapper = imgwAlertMapper;
    }

    @GetMapping("/actual")
    public ModelAndView getActualAlerts() {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            List<String> actualListOfAlerts = imgwPdfService.getActualListOfAlerts();
            List<ImgwMeteoWarning> imgwMeteoWarnings = actualListOfAlerts.stream()
                    .map(imgwAlertMapper::toModel)
                    .toList();
            modelMap.put("listOfAlerts", imgwMeteoWarnings);

        } catch (IOException e) {
            modelMap.put("listOfAlerts", List.of());
        }

        return new ModelAndView("actual", modelMap);
    }
}
