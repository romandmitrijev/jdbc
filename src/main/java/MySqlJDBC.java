import model.City;
import repository.CityRepository;

import java.util.List;

public class MySqlJDBC {

    public static void main(String[] args) {

        CityRepository cityRepository = new CityRepository();
        List<City> cities = cityRepository.getCities();

        printCities(cities);

        cityRepository.addCity(null);

    }
    public static void printCities (List<City> cities){
        for (City city : cities){
            System.out.println("Name \t Country code \t District \t Population");
            System.out.print(city.getName() + "\t");
            System.out.print(city.getCountryCode() + "\t");
            System.out.println(city.getDistrict() + "\t");
            System.out.println(city.getPopulation() + "\t");
        }
    }
}
