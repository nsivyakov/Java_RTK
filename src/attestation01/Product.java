package attestation01;

public class Product {
    private String title;
    private int cost;

    public Product(String title, int cost) {
        setTitle(title);
        setCost(cost);
    }


    public void setTitle(String title) {

        if (title.trim().length() == 0) {
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

    @Override
    public String toString() {
        return "Продукт: " + "наименование = " + title + ", цена = " + cost + ".";

    }
}