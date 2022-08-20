package com.thedariusz.pdfreaderdemo.repository;

import com.thedariusz.pdfreaderdemo.repository.entity.CountyEntity;
import com.thedariusz.pdfreaderdemo.repository.entity.VoivodeshipEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class VoivodeshipService {

    private final VoivodeshipRepository voivodeshipRepository;
    private final CountyRepository countyRepository;

    public VoivodeshipService(VoivodeshipRepository voivodeshipRepository, CountyRepository countyRepository) {
        this.voivodeshipRepository = voivodeshipRepository;
        this.countyRepository = countyRepository;
    }

    public VoivodeshipEntity findByCode(String code) {
        Optional<VoivodeshipEntity> voivodeshipRepositoryByCode = voivodeshipRepository.findByCode(code);
        return voivodeshipRepositoryByCode
                .orElseThrow(() -> new IllegalArgumentException("Voivodeship not found"));
    }

    public Set<CountyEntity> getCountyEntities(Map<String, Integer> counties, Long voivodeshipId) {
        return countyRepository.findAllByNameInAndVoivodeshipId(counties.keySet() , voivodeshipId);
    }
}
