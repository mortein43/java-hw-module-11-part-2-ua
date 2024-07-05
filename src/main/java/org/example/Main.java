package org.example;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        String dbUrl = "jdbc:postgresql://localhost:5432/";
        String dbName = "countries_db";
        String user = "postgres";
        String password = "postgres";
        try {
            DatabaseInitializer dbInitializer = new DatabaseInitializer(dbUrl, dbName, user, password);
            dbInitializer.initializeDatabase();
            Connection connection = DatabaseConnection.getConnection(dbUrl, dbName, user, password);
            DatabaseSetupDAO setupDAO = new DatabaseSetupDAO(connection);
            CountryDAO countryDAO = new CountryDAO(connection);
            CityDAO cityDAO = new CityDAO(connection);

            setupDAO.createTables();
            setupDAO.populateTables();

            List<Country> countries = countryDAO.getAllCountries();
            out.println("Всі країни:");
            countries.forEach(country -> out.println(country.getId()+ " " + country.getName()));

            Country country = countryDAO.getCountryByName("Україна");
            out.println("Відображення всіх міст з України (які є в базі):");
            if (country != null) {
                List<City> cities = cityDAO.getCitiesByCountryId(country.getId());
                cities.forEach(city -> out.println(city.getId() + " " + city.getName()));
            }

            out.println("Відображення всіх столиць:");
            List<City> capitals = cityDAO.getAllCapitals();
            capitals.forEach(capital -> out.println(capital.getId() + " " + capital.getName()));

            out.println("Показуємо столицю України:");
            Country ukraine = countryDAO.getCountryByName("Україна");
            if (ukraine != null) {
                System.out.println("Столиця України - " + ukraine.getCapital());
            }

            out.println("Три країни з найбільшою кількістю міст:");
            List<Country> topCountriesByCityCount = countryDAO.getTopCountriesByCityCount(3);
            topCountriesByCityCount.forEach(c -> System.out.println(c.getId() + " " + c.getName()));

            out.println("Три країни з найбільшою кількістю жителів:");
            List<Country> topCountriesByPopulation = countryDAO.getTopCountriesByPopulation(3);
            topCountriesByPopulation.forEach(c -> System.out.println(c.getId() + " " + c.getName()));

            out.println("Три країни  найменшою кількістю жителів:");
            List<Country> bottomCountriesByPopulation = countryDAO.getBottomCountriesByPopulation(3);
            bottomCountriesByPopulation.forEach(c -> System.out.println(c.getId() + " " + c.getName()));

            out.println("Середня кількість жителів у місті в Україні:");
            if (ukraine != null) {
                double averagePopulation = countryDAO.getAverageCityPopulation(ukraine.getId());
                System.out.println("Кількість: " + averagePopulation);
            }

            out.println("Кількість міст з однаковою назвою в різних країнах:");
            Map<String, Integer> cityNameCounts = countryDAO.getCityNameCounts();
            cityNameCounts.forEach((cityName, count) -> System.out.println("Мысто: " + cityName + ", Кількість країн: " + count));

            out.println("Унікальні назви міст із різних країн:");
            Set<String> uniqueCityNames = countryDAO.getUniqueCityNames();
            uniqueCityNames.forEach(cityName -> System.out.println(cityName));

            out.println("Країни з кількістю міст в діапазоні від 2 до 4:");
            List<Country> countriesByCityCountRange = countryDAO.getCountriesByCityCountRange(2, 4);
            countriesByCityCountRange.forEach(c -> System.out.println(c.getId() + " " + c.getName()));

            DatabaseConnection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}