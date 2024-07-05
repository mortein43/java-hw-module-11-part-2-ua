package org.example;

public class Country {
    private int id;
    private String name;
    private String capital;
    private int population;

    public Country(int id, String name, String capital, int population) {
        this.id = id;
        this.name = name;
        this.capital = capital;
        this.population = population;
    }

    public Country() {

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

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
