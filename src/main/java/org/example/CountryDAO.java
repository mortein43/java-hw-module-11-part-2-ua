package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class CountryDAO {
    private Connection connection;

    public CountryDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Country> getAllCountries() throws SQLException {
        List<Country> countries = new ArrayList<>();
        String query = "SELECT * FROM countries";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Country country = new Country();
                country.setId(rs.getInt("country_id"));
                country.setName(rs.getString("country_name"));
                country.setCapital(rs.getString("capital"));
                country.setPopulation(rs.getInt("population"));
                countries.add(country);
            }
        }
        return countries;
    }

    public Country getCountryByName(String name) throws SQLException {
        Country country = null;
        String query = "SELECT * FROM countries WHERE country_name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    country = new Country();
                    country.setId(rs.getInt("country_id"));
                    country.setName(rs.getString("country_name"));
                    country.setCapital(rs.getString("capital"));
                    country.setPopulation(rs.getInt("population"));
                }
            }
        }
        return country;
    }

    public List<Country> getTopCountriesByCityCount(int limit) throws SQLException {
        List<Country> countries = new ArrayList<>();
        String query = "SELECT co.*, COUNT(ci.city_id) as city_count " +
                "FROM countries co " +
                "JOIN cities ci ON co.country_id = ci.country_id " +
                "GROUP BY co.country_id " +
                "ORDER BY city_count DESC " +
                "LIMIT ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, limit);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Country country = new Country();
                    country.setId(rs.getInt("country_id"));
                    country.setName(rs.getString("country_name"));
                    country.setCapital(rs.getString("capital"));
                    country.setPopulation(rs.getInt("population"));
                    countries.add(country);
                }
            }
        }
        return countries;
    }

    public List<Country> getTopCountriesByPopulation(int limit) throws SQLException {
        List<Country> countries = new ArrayList<>();
        String query = "SELECT * FROM countries ORDER BY population DESC LIMIT ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, limit);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Country country = new Country();
                    country.setId(rs.getInt("country_id"));
                    country.setName(rs.getString("country_name"));
                    country.setCapital(rs.getString("capital"));
                    country.setPopulation(rs.getInt("population"));
                    countries.add(country);
                }
            }
        }
        return countries;
    }

    public List<Country> getBottomCountriesByPopulation(int limit) throws SQLException {
        List<Country> countries = new ArrayList<>();
        String query = "SELECT * FROM countries ORDER BY population ASC LIMIT ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, limit);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Country country = new Country();
                    country.setId(rs.getInt("country_id"));
                    country.setName(rs.getString("country_name"));
                    country.setCapital(rs.getString("capital"));
                    country.setPopulation(rs.getInt("population"));
                    countries.add(country);
                }
            }
        }
        return countries;
    }

    public double getAverageCityPopulation(int countryId) throws SQLException {
        String query = "SELECT AVG(population) as average_population FROM cities WHERE country_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, countryId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("average_population");
                }
            }
        }
        return 0;
    }

    public Map<String, Integer> getCityNameCounts() throws SQLException {
        Map<String, Integer> cityNameCounts = new HashMap<>();
        String query = "SELECT city_name, COUNT(DISTINCT country_id) as country_count " +
                "FROM cities GROUP BY city_name";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                cityNameCounts.put(rs.getString("city_name"), rs.getInt("country_count"));
            }
        }
        return cityNameCounts;
    }

    public Set<String> getUniqueCityNames() throws SQLException {
        Set<String> cityNames = new HashSet<>();
        String query = "SELECT DISTINCT city_name FROM cities";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                cityNames.add(rs.getString("city_name"));
            }
        }
        return cityNames;
    }

    public List<Country> getCountriesByCityCountRange(int min, int max) throws SQLException {
        List<Country> countries = new ArrayList<>();
        String query = "SELECT co.*, COUNT(ci.city_id) as city_count " +
                "FROM countries co " +
                "JOIN cities ci ON co.country_id = ci.country_id " +
                "GROUP BY co.country_id " +
                "HAVING COUNT(ci.city_id) BETWEEN ? AND ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, min);
            pstmt.setInt(2, max);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Country country = new Country();
                    country.setId(rs.getInt("country_id"));
                    country.setName(rs.getString("country_name"));
                    country.setCapital(rs.getString("capital"));
                    country.setPopulation(rs.getInt("population"));
                    countries.add(country);
                }
            }
        }
        return countries;
    }
}
