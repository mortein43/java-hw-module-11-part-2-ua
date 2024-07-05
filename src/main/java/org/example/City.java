package org.example;

public class City {
    private int id;
    private String name;
    private int countryId;
    private int population;

    public City(int id, String name, int countryId, int population) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
        this.population = population;
    }

    public City() {

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

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
