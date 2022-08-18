package com.thedariusz.pdfreaderdemo.repository;

import com.thedariusz.pdfreaderdemo.repository.entity.VoivodeshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoivodeshipRepository extends JpaRepository<VoivodeshipEntity, Long> {

    @Query("select v from VoivodeshipEntity v where v.code = ?1")
    Optional<VoivodeshipEntity> findByCode(String code);

}

