package homework011Addition.Test;

import homework011Addition.Car.Car;
import homework011Addition.Repository.CarsRepository;
import homework011Addition.Repository.CarsRepositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filename = "Java_RTK/src/homework011Addition/Data/cars.txt";

        CarsRepository repo = new CarsRepositoryImpl();

        // загружаем из файла
        repo.loadFromFile(filename);

        // 1. Автомобили по цвету или пробегу
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Введите цвет: ");
        String colorToFind = scanner1.nextLine();
        List<Car> filteredCars = repo.findCarsByColorOrMileage(colorToFind, 0);
        System.out.println("\nАвтомобили с цветом " + colorToFind + " или пробегом 0:");
        //System.out.println(filteredCars);
        repo.appendLineToFile(filename, "\nАвтомобили с цветом " + colorToFind + " или пробегом 0:");
        for (int i = 0; i < filteredCars.size(); i++) {
            String elem = String.valueOf(filteredCars.get(i));
            repo.appendLineToFile(filename, elem.toString());
        }
        filteredCars.forEach(System.out::println);

        // 2. Количество уникальных моделей в диапазоне цен
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("\nВведите диапазоны. \nМинимальный: ");
        long n = scanner2.nextLong();
        System.out.println("\nМаксимальный: ");
        long m = scanner2.nextLong();
        long countModels = repo.countUniqueModelsInPriceRange(n, m);
        System.out.println("\nКоличество уникальных моделей в ценовом диапазоне " + n + "-" + m + ": " + countModels);
        repo.appendLineToFile(filename, "\nКоличество уникальных моделей в ценовом диапазоне " + n + "-" + m + ": ");
        repo.appendLineToFile(filename, Long.toString(countModels));

        // 3. Автомобиль с минимальной ценой
        Optional<Car> optionalCar = repo.getCarWithMinPrice();
        if (optionalCar.isPresent()) {
            Car minPriceCar = optionalCar.get();
            String carInfo = minPriceCar.toString();
            System.out.println("\nАвтомобиль с минимальной ценой: " + carInfo);
            repo.appendLineToFile(filename, "\n3. Автомобиль с минимальной ценой:");
            repo.appendLineToFile(filename, carInfo);
        }

        // 4. Средняя цена по модели
        Scanner scanner3 = new Scanner(System.in);
        System.out.println("\nВведите модель: ");
        String modelToAverage = scanner3.nextLine();
        double avgPrice = repo.getAveragePriceByModel(modelToAverage);
        System.out.println("\nСредняя цена модели " + modelToAverage + ": " + avgPrice);
        repo.appendLineToFile(filename, "\n4. Средняя цена по модели:");
        repo.appendLineToFile(filename, Double.toString(avgPrice));
    }
}
