package com.example.enviro365.repository;

import com.example.enviro365.entity.waste.WasteCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WasteCategoryRepository extends JpaRepository<WasteCategory, Long> {
}
