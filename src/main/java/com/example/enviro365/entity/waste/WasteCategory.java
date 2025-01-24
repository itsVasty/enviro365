package com.example.enviro365.entity.waste;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class WasteCategory {

    private @Id
    @GeneratedValue Long id;
    private String name;
    private Long guideline_id;

    public WasteCategory() {}

    public WasteCategory(String name, Long guideline_id) {

        this.name = name;
        this.guideline_id = guideline_id;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Long getGuideLineId() {
        return this.guideline_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGuideLineId(Long guideline_id) {
        this.guideline_id = guideline_id;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof WasteCategory wasteCategory))
            return false;
        return Objects.equals(this.id, wasteCategory.id) && Objects.equals(this.name, wasteCategory.name)
                && Objects.equals(this.guideline_id, wasteCategory.guideline_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.guideline_id);
    }

    @Override
    public String toString() {
        return "WasteCategory{" + "id=" + this.id + ", name='" + this.name + '\'' + ", guideline_id='" + this.guideline_id + '\'' + '}';
    }
}