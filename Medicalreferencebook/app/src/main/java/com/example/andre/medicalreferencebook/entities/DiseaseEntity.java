package com.example.andre.medicalreferencebook.entities;

/**
 * Created by Andre on 22.04.2017.
 */

public class DiseaseEntity {

    private int id;
    private String name;
    private String description;

    public DiseaseEntity() {
    }

    public DiseaseEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public DiseaseEntity(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiseaseEntity that = (DiseaseEntity) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "DiseaseEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
