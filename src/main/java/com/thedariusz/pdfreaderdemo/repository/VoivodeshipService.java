package com.thedariusz.pdfreaderdemo.repository;

import com.thedariusz.pdfreaderdemo.repository.entity.VoivodeshipEntity;
import org.springframework.stereotype.Service;

@Service
public class VoivodeshipService {

    private final VoivodeshipRepository voivodeshipRepository;

    public VoivodeshipService(VoivodeshipRepository voivodeshipRepository) {
        this.voivodeshipRepository = voivodeshipRepository;
    }

    public VoivodeshipEntity findByCode(String code) {
        return voivodeshipRepository.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("Voivodeship not found"));
    }

}
