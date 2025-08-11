package DZ_6;
// Класс Покупатели
import attestation01.Product;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private int cash;
    private List<Product> packProducts;

    public Person(String name, int cash) {
        setName(name);
        setCash(cash);
        this.packProducts = new ArrayList<>();
    }

    public void setName(String name) {

        if (name.trim().isEmpty() || name.length()<3) {
            throw new IllegalArgumentException("Поле Имя не может быть пустым и менее 3-ч символов");
        } else {
            this.name = name;
        }
    }

    public void setCash(int cash) {
        if (cash < 0) {
            throw new IllegalArgumentException("Поле Деньги не может быть отрицательным");
        } else {
            this.cash = cash;
        }
    }

    public String getName() {
        return name;
    }

    public double getCash() {
        return cash;
    }

    public List<Product> getPackProducts() {
        return packProducts;
    }
    public boolean buyProduct(Product product) {
        if (product.getCost() <= this.cash) {
            this.packProducts.add(product);
            this.cash -= product.getCost();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (packProducts.isEmpty()) {
            return name + " - Ничего не куплено";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" - ");
        for (int i = 0; i < packProducts.size(); i++) {
            sb.append(packProducts.get(i).getTitle());
            if (i != packProducts.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Person)) return false;
        Person other = (Person) obj;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}