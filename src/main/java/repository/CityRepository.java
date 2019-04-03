package repository;

import model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityRepository {
    private String connectionString = "jdbc:mysql://localhost:3306/world";
    private String userName = "root";
    private String passWord = "";

    public List<City> getCities() {


        List<City> cities = new ArrayList<>();
        try (//Step 1 - Connection to the DB
             Connection conn = DriverManager.getConnection(connectionString, userName, passWord);
             //Step 2 - Create a statement using the connection
             Statement stmt = conn.createStatement();
             //Step 3 - Execute statement
             ResultSet resultSet = stmt.executeQuery("SELECT * FROM city LIMIT 50")) {

            //Step 4 - Loop through the result
            while (resultSet.next()) {
                City city = new City();
                city.setName(resultSet.getString("name"));
                city.setCountryCode(resultSet.getString("countryCode"));
                city.setDistrict(resultSet.getString("district"));
                city.setPopulation(resultSet.getInt("population"));

                cities.add(city);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public void addCity(City city) {

        try (Connection conn = DriverManager.getConnection(connectionString, userName, passWord);
             Statement stmt = conn.createStatement()) {
            String insertString = "INSERT INTO city (name, countryCode, district, population) " +
                    "VALUES('TestName', 'IND', 'TestDistrict', 8)";
            int rowsAffected = stmt.executeUpdate(insertString);
            System.out.println("We have inserted " + rowsAffected + " rows");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeCity(City city) {

        try (Connection conn = DriverManager.getConnection(connectionString, userName, passWord);
             Statement stmt = conn.createStatement()) {
            String removeString = "DELETE FROM city WHERE name = TestName";
            int rowsAffected = stmt.executeUpdate(removeString);
            System.out.println("Deleted " + rowsAffected + " rows");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<City> getCityByName(String name) {

        List<City> cities = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(connectionString, userName, passWord);
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery("SELECT * FROM city WHERE name = " + name)) {

            while (resultSet.next()) {
                City city = new City();
                city.setName(resultSet.getString("name"));
                city.setCountryCode(resultSet.getString("countryCode"));
                city.setDistrict(resultSet.getString("district"));
                city.setPopulation(resultSet.getInt("population"));

                cities.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }
}
