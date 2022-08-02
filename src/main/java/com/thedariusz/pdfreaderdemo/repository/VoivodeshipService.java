package com.thedariusz.pdfreaderdemo.repository;

import com.thedariusz.pdfreaderdemo.repository.entity.VoivodeshipEntity;
import org.springframework.stereotype.Service;

@Service
public class VoivodeshipService {

    private final VoivodeshipRepository voivodeshipRepository;

    public VoivodeshipService(VoivodeshipRepository voivodeshipRepository) {
        this.voivodeshipRepository = voivodeshipRepository;
    }

    public VoivodeshipEntity findByName(String name) {
        return voivodeshipRepository.findByCode(name)
                .orElseThrow(() -> new IllegalArgumentException("Voivodeship not found"));
    }

}
