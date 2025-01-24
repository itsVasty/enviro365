package com.example.enviro365.entity.tips;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;


@Entity
public class RecyclingTip {

    private @Id
    @GeneratedValue Long id;
    private String name;
    private String tip;

    public RecyclingTip() {}

    public RecyclingTip(String name, String tip) {
        this.name = name;
        this.tip = tip;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getTip() {
        return this.tip;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTip(String tip) {
        this.tip = this.tip;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof RecyclingTip recyclingTip))
            return false;
        return Objects.equals(this.id, recyclingTip.id) && Objects.equals(this.name, recyclingTip.name)
                && Objects.equals(this.tip, recyclingTip.tip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.tip);
    }

    @Override
    public String toString() {
        return "RecyclingTip{" + "id=" + this.id + ", name='" + this.name + '\'' + ", tip='" + this.tip + '\'' + '}';
    }
}