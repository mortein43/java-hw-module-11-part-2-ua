package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {
    private Connection connection;

    public CityDAO(Connection connection) {
        this.connection = connection;
    }

    public List<City> getCitiesByCountryId(int countryId) throws SQLException {
        List<City> cities = new ArrayList<>();
        String query = "SELECT * FROM cities WHERE country_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, countryId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    City city = new City();
                    city.setId(rs.getInt("city_id"));
                    city.setName(rs.getString("city_name"));
                    city.setCountryId(rs.getInt("country_id"));
                    city.setPopulation(rs.getInt("population"));
                    cities.add(city);
                }
            }
        }
        return cities;
    }

    public List<City> getAllCapitals() throws SQLException {
        List<City> capitals = new ArrayList<>();
        String query = "SELECT * FROM cities c JOIN countries co ON c.city_name = co.capital";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                City city = new City();
                city.setId(rs.getInt("city_id"));
                city.setName(rs.getString("city_name"));
                city.setCountryId(rs.getInt("country_id"));
                city.setPopulation(rs.getInt("population"));
                capitals.add(city);
            }
        }
        return capitals;
    }
}
