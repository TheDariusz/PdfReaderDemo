package com.thedariusz.pdfreaderdemo.repository;

import com.thedariusz.pdfreaderdemo.repository.entity.VoivodeshipEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoivodeshipService {

    private final VoivodeshipRepository voivodeshipRepository;

    public VoivodeshipService(VoivodeshipRepository voivodeshipRepository) {
        this.voivodeshipRepository = voivodeshipRepository;
    }

    public VoivodeshipEntity findByCode(String code) {
        Optional<VoivodeshipEntity> voivodeshipRepositoryByCode = voivodeshipRepository.findByCode(code);
        return voivodeshipRepositoryByCode
                .orElseThrow(() -> new IllegalArgumentException("Voivodeship not found"));
    }

}
