package com.thedariusz.pdfreaderdemo.repository;

import com.thedariusz.pdfreaderdemo.repository.entity.CountyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CountyRepository extends JpaRepository<CountyEntity, Long> {

    @Query("select c from CountyEntity c where c.name in ?1 and c.voivodeship.id = ?2")
    Set<CountyEntity> findAllByNameInAndVoivodeshipId(Set<String> keySet, Long voivodeshipId);
}