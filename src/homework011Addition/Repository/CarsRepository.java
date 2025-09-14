package homework011Addition.Repository;

import homework011Addition.Car.Car;

import java.util.List;
import java.util.Optional;

public interface CarsRepository {
    void loadFromFile(String filename);

    /* Номера всех автомобилей, имеющих заданный в переменной цвет
    colorToFind или нулевой пробег mileageToFind.*/
    List<Car> findCarsByColorOrMileage(String color, int mileage);

    //Количество уникальных моделей в ценовом диапазоне от n до m тыс.
    long countUniqueModelsInPriceRange(double minPrice, double maxPrice);

    // Вывести цвет автомобиля с минимальной стоимостью.
    Optional<Car> getCarWithMinPrice();

    // Средняя стоимость искомой модели modelToFind
    double getAveragePriceByModel(String model);

    void appendLineToFile(String filename, String data);
}
