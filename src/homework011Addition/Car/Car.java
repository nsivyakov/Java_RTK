package homework011Addition.Car;

public class Car {
    private String cnumber;      // Номер автомобиля
    private String model;      // Модель
    private String color;      // Цвет
    private int mileage;        // Пробег
    private double price;   // Стоимость

    public Car(String cnumber, String model, String color, int mileage, double price) {
        this.cnumber = cnumber;
        this.model = model;
        this.color = color;
        this.mileage = mileage;
        this.price = price;
    }

    // Геттеры и сеттеры
    public String getCnumber() {
        return cnumber;
    }

    public void setNomer(String cnumber) {
        this.cnumber = cnumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Автомобиль{" +
                "Номер='" + cnumber + '\'' +
                ", Модель='" + model + '\'' +
                ", Цвет='" + color + '\'' +
                ", Пробег=" + mileage +
                ", Стоимость=" + price +
                '}';
    }

    public static Car fromString(String str) {
        String[] parts = str.split("\\|");
         if(parts.length == 5 ) {
            String cnumber = parts[0].trim();
            String model = parts[1].trim();
            String color = parts[2].trim();
            int mileage = Integer.parseInt(parts[3].trim());
            double price = Double.parseDouble(parts[4].trim());
        return new Car(cnumber, model, color, mileage, price);}
        return null;
    }
}
