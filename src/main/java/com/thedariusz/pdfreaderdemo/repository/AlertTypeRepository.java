package com.thedariusz.pdfreaderdemo.repository;

import com.thedariusz.pdfreaderdemo.repository.entity.AlertTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertTypeRepository extends JpaRepository<AlertTypeEntity, Long> {
}