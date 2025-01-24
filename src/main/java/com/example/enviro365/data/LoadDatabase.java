package com.example.enviro365.data;

import com.example.enviro365.entity.guidelines.DisposalGuideline;
import com.example.enviro365.entity.tips.RecyclingTip;
import com.example.enviro365.entity.waste.WasteCategory;
import com.example.enviro365.repository.DisposalGuidelineRepository;
import com.example.enviro365.repository.RecyclingTipRepository;
import com.example.enviro365.repository.WasteCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(WasteCategoryRepository wasteCategoryRepository, DisposalGuidelineRepository disposalGuidelineRepository, RecyclingTipRepository recyclingTipRepository) {

        return args -> {
            log.info("Preloading " + recyclingTipRepository.save(new RecyclingTip("Tip 1", "Tip 1 info")));
            log.info("Preloading " + recyclingTipRepository.save(new RecyclingTip("Tip 2", "Tip 2 info")));

            log.info("Preloading " + disposalGuidelineRepository.save(new DisposalGuideline("Guideline 1", "Guideline 1 info")));
            log.info("Preloading " + disposalGuidelineRepository.save(new DisposalGuideline("Guideline 2", "Guideline 2 info")));

            log.info("Preloading " + wasteCategoryRepository.save(new WasteCategory("Plastic", 2L)));
            log.info("Preloading " + wasteCategoryRepository.save(new WasteCategory("Paper", 1L)));
        };
    }
}
