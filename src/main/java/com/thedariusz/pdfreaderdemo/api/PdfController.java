package com.thedariusz.pdfreaderdemo.api;

import com.thedariusz.pdfreaderdemo.ImgwPdfService;
import com.thedariusz.pdfreaderdemo.model.ImgwMeteoAlert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/alerts")
public class PdfController {

    private final ImgwPdfService imgwPdfService;

    public PdfController(ImgwPdfService imgwPdfService) {
        this.imgwPdfService = imgwPdfService;
    }

    @GetMapping("/actual")
    public ModelAndView getActualAlerts() {
        Map<String, Object> modelMap = new HashMap<>();
        List<String> textAlerts = imgwPdfService.fetchTextAlerts();
        List<ImgwMeteoAlert> imgwMeteoAlerts = textAlerts.stream()
                .map(ImgwMeteoAlert::fromText)
                .toList();
        modelMap.put("listOfAlerts", imgwMeteoAlerts);

        return new ModelAndView("actual", modelMap);
    }
}
