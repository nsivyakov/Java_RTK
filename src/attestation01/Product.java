package attestation01;
// Класс продукты
public class Product {
    private String title;
    private int cost;

    public Product(String title, int cost) {
        setTitle(title);
        setCost(cost);
    }


    public void setTitle(String title) {

        if (title.trim().isEmpty()) {
            throw new IllegalArgumentException("Поле Продукт не может быть пустым");
        } else {
            this.title = title;
        }
    }

    public void setCost(int cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Поле Стоимость не может быть отрицательным");
        } else {
            this.cost = cost;
        }
    }
    public String getTitle() {
        return title;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Продукт: " + "наименование = " + title + ", цена = " + cost + ".";

    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Product)) return false;
        Product other = (Product) obj;
        return title.equals(other.title) && Integer.compare(cost, other.cost) == 0;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        return result;
    }
}