package org.example;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetupDAO {
    private Connection connection;

    public DatabaseSetupDAO(Connection connection) {
        this.connection = connection;
    }

    public void createTables() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            String createCountriesTable = "CREATE TABLE IF NOT EXISTS countries (" +
                    "country_id SERIAL PRIMARY KEY, " +
                    "country_name VARCHAR(100) NOT NULL, " +
                    "capital VARCHAR(100) NOT NULL, " +
                    "population INT NOT NULL" +
                    ")";
            stmt.executeUpdate(createCountriesTable);

            String createCitiesTable = "CREATE TABLE IF NOT EXISTS cities (" +
                    "city_id SERIAL PRIMARY KEY, " +
                    "city_name VARCHAR(100) NOT NULL, " +
                    "country_id INT REFERENCES countries(country_id), " +
                    "population INT NOT NULL" +
                    ")";
            stmt.executeUpdate(createCitiesTable);
        }
    }

    public void populateTables() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            String insertCountries = "INSERT INTO countries (country_name, capital, population) " +
                    "SELECT 'Україна', 'Київ', 41600000 WHERE NOT EXISTS (SELECT 1 FROM countries WHERE country_name='Україна');" +
                    "INSERT INTO countries (country_name, capital, population) " +
                    "SELECT 'США', 'Вашингтон', 331000000 WHERE NOT EXISTS (SELECT 1 FROM countries WHERE country_name='США');" +
                    "INSERT INTO countries (country_name, capital, population) " +
                    "SELECT 'Канада', 'Оттава', 38000000 WHERE NOT EXISTS (SELECT 1 FROM countries WHERE country_name='Канада');" +
                    "INSERT INTO countries (country_name, capital, population) " +
                    "SELECT 'Німеччина', 'Берлін', 83000000 WHERE NOT EXISTS (SELECT 1 FROM countries WHERE country_name='Німеччина');" +
                    "INSERT INTO countries (country_name, capital, population) " +
                    "SELECT 'Франція', 'Париж', 67000000 WHERE NOT EXISTS (SELECT 1 FROM countries WHERE country_name='Франція');" +
                    "INSERT INTO countries (country_name, capital, population) " +
                    "SELECT 'Італія', 'Рим', 60000000 WHERE NOT EXISTS (SELECT 1 FROM countries WHERE country_name='Італія');" +
                    "INSERT INTO countries (country_name, capital, population) " +
                    "SELECT 'Іспанія', 'Мадрид', 47000000 WHERE NOT EXISTS (SELECT 1 FROM countries WHERE country_name='Іспанія');" +
                    "INSERT INTO countries (country_name, capital, population) " +
                    "SELECT 'Японія', 'Токіо', 126000000 WHERE NOT EXISTS (SELECT 1 FROM countries WHERE country_name='Японія');" +
                    "INSERT INTO countries (country_name, capital, population) " +
                    "SELECT 'Китай', 'Пекін', 1402000000 WHERE NOT EXISTS (SELECT 1 FROM countries WHERE country_name='Китай');" +
                    "INSERT INTO countries (country_name, capital, population) " +
                    "SELECT 'Індія', 'Нью-Делі', 1380000000 WHERE NOT EXISTS (SELECT 1 FROM countries WHERE country_name='Індія');";
            stmt.executeUpdate(insertCountries);

            String insertCities = "INSERT INTO cities (city_name, country_id, population) " +
                    "SELECT 'Київ', 1, 2900000 WHERE NOT EXISTS (SELECT 1 FROM cities WHERE city_name='Київ' AND country_id=1);" +
                    "INSERT INTO cities (city_name, country_id, population) " +
                    "SELECT 'Львів', 1, 720000 WHERE NOT EXISTS (SELECT 1 FROM cities WHERE city_name='Львів' AND country_id=1);" +
                    "INSERT INTO cities (city_name, country_id, population) " +
                    "SELECT 'Вашингтон', 2, 700000 WHERE NOT EXISTS (SELECT 1 FROM cities WHERE city_name='Вашингтон' AND country_id=2);" +
                    "INSERT INTO cities (city_name, country_id, population) " +
                    "SELECT 'Нью-Йорк', 2, 8400000 WHERE NOT EXISTS (SELECT 1 FROM cities WHERE city_name='Нью-Йорк' AND country_id=2);" +
                    "INSERT INTO cities (city_name, country_id, population) " +
                    "SELECT 'Оттава', 3, 1000000 WHERE NOT EXISTS (SELECT 1 FROM cities WHERE city_name='Оттава' AND country_id=3);" +
                    "INSERT INTO cities (city_name, country_id, population) " +
                    "SELECT 'Торонто', 3, 2800000 WHERE NOT EXISTS (SELECT 1 FROM cities WHERE city_name='Торонто' AND country_id=3);" +
                    "INSERT INTO cities (city_name, country_id, population) " +
                    "SELECT 'Берлін', 4, 3600000 WHERE NOT EXISTS (SELECT 1 FROM cities WHERE city_name='Берлін' AND country_id=4);" +
                    "INSERT INTO cities (city_name, country_id, population) " +
                    "SELECT 'Мюнхен', 4, 1500000 WHERE NOT EXISTS (SELECT 1 FROM cities WHERE city_name='Мюнхен' AND country_id=4);" +
                    "INSERT INTO cities (city_name, country_id, population) " +
                    "SELECT 'Париж', 5, 2100000 WHERE NOT EXISTS (SELECT 1 FROM cities WHERE city_name='Париж' AND country_id=5);" +
                    "INSERT INTO cities (city_name, country_id, population) " +
                    "SELECT 'Марсель', 5, 860000 WHERE NOT EXISTS (SELECT 1 FROM cities WHERE city_name='Марсель' AND country_id=5);";
            stmt.executeUpdate(insertCities);
        }
    }
}
