package com.thedariusz.pdfreaderdemo.repository;

import com.thedariusz.pdfreaderdemo.repository.entity.AlertStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlertStatusEntityRepository extends JpaRepository<AlertStatusEntity, Long> {

    Optional<AlertStatusEntity> findByStatus(String status);
}