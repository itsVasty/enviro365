package com.example.enviro365.entity.guidelines;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;


@Entity
public class DisposalGuideline {

    private @Id
    @GeneratedValue Long id;
    private String name;
    private String guideline;

    public DisposalGuideline() {}

    public DisposalGuideline(String name, String guideline) {
        this.name = name;
        this.guideline = guideline;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getGuideLine() {
        return this.guideline;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGuideLine(String guideline) {
        this.guideline = this.guideline;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof DisposalGuideline disposalGuideline))
            return false;
        return Objects.equals(this.id, disposalGuideline.id) && Objects.equals(this.name, disposalGuideline.name)
                && Objects.equals(this.guideline, disposalGuideline.guideline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.guideline);
    }

    @Override
    public String toString() {
        return "DisposalGuideLine{" + "id=" + this.id + ", name='" + this.name + '\'' + ", guideline='" + this.guideline + '\'' + '}';
    }
}