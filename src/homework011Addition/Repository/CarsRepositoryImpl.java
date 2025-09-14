package homework011Addition.Repository;

import homework011Addition.Car.Car;

import java.io.*;
import java.util.*;
import java.util.stream.*;


public class CarsRepositoryImpl implements CarsRepository {
    private List<Car> cars = new ArrayList<>();

    @Override
    public void loadFromFile(String filename) {
        cars.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Car car = Car.fromString(line);
                if (car != null) {
                    cars.add(car);
                }
            }
        } catch (IOException e) {
            System.out.println("Некорректный формат строки: " + e.getMessage());
        }
    }

    @Override
    public List<Car> findCarsByColorOrMileage(String color, int mileage) {
        return cars.stream()
                .filter(a -> a.getColor().equalsIgnoreCase(color) || a.getMileage() == mileage)
                .collect(Collectors.toList());
    }

    @Override
    public long countUniqueModelsInPriceRange(double minPrice, double maxPrice) {
        return cars.stream()
                .filter(a -> a.getPrice() >= minPrice && a.getPrice() <= maxPrice)
                .map(Car::getModel) // a -> a.getModel()
                .distinct()
                .count();
    }

    @Override
    public Optional<Car> getCarWithMinPrice() {
        return cars.stream()
                .min(Comparator.comparingDouble(Car::getPrice));
    }


    @Override
    public double getAveragePriceByModel(String model) {
        return cars.stream()
                .filter(a -> a.getModel().equalsIgnoreCase(model))
                .mapToDouble(Car::getPrice) // a -> a.getPrice()
                .average()
                .orElse(0);
    }
    public void appendLineToFile(String filename, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

