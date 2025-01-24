package com.example.enviro365.repository;

import com.example.enviro365.entity.tips.RecyclingTip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecyclingTipRepository extends JpaRepository<RecyclingTip, Long> {
}
