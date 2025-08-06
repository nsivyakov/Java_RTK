package attestation01;

import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Person> persons = new HashMap<>();
        Map<String, Product> products = new HashMap<>();
        // Карта покупок: ключ - покупатель, значение - список купленных продуктов
        Map<Person, List<Product>> purchasesMap = new HashMap<>();

        // Ввод покупателей
        System.out.println("Введите данные покупателей, продукты и покупки.");
        System.out.println("Введите пустую строку или END для завершения.");
        System.out.println("Введите покупателей:");
        String line1 = scanner.nextLine().trim();
        String[] entries1 = line1.split(";");
        for (String entry : entries1) {
            String[] parts = entry.split("=");
            if (parts.length != 2) continue;
            String name = parts[0].trim();
            String moneyStr = parts[1].trim();

            Integer cash = Integer.valueOf(moneyStr);
            Person person = new Person(name, cash);
            persons.put(name, person);

        }
        // Ввод продуктов
        System.out.println("Введите продукты:");

        String line2 = scanner.nextLine().trim();
        String[] entries2 = line2.split(";");
        for (String entry : entries2) {
            String[] parts = entry.split("=");
            if (parts.length != 2) continue;
            String title = parts[0].trim();
            String priceStr = parts[1].trim();

            Integer cost = Integer.valueOf(priceStr);
            Product product = new Product(title, cost);
            products.put(title, product);
        }

        while (true) {
            System.out.print("Введите покупки:");
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("END") || line.isEmpty()) break;

            String[] parts = line.split("-");
            if (parts.length != 2) {
                System.out.println("Некорректный формат ввода, повторите.");
                continue;
            }

            String personName = parts[0].trim();
            String productName = parts[1].trim();

            Person person = persons.get(personName);
            Product product = products.get(productName);

            if (person == null) {
                System.out.println("Пользователь не найден: " + personName);
                continue;
            }

            if (product == null) {
                System.out.println("Продукт не найден: " + productName);
                continue;
            }

            // Попытка покупки
            if (person.getCash() >= product.getCost()) {
                boolean bought = person.buyProduct(product);
                if (bought) {
                    if (purchasesMap.containsKey(person)) {
                        // если покупатель уже есть, добавляем продукт в его список
                        List<Product> productsList = purchasesMap.get(person);
                        productsList.add(product);
                    } else {
                        // если его еще нет, создаем новый список и сохраняем
                        List<Product> newList = new ArrayList<>();
                        newList.add(product);
                        purchasesMap.put(person, newList);
                    }
                    System.out.println(person.getName() + " купил " + product.getTitle());
                }
            } else {
                System.out.println(personName + " не может позволить себе " + productName);
            }

        }


        /* Вариант 1 Итоговый вывод покупок по каждому покупателю
       /* System.out.println("\nИтоговые покупки:");
        for (Person person : persons.values()) {
            List<Product> boughtProducts = purchasesMap.get(person);
            if (boughtProducts == null || boughtProducts.isEmpty()) {
                System.out.println(person.getName() + " ничего не купил");
            } else {
                System.out.println(person.getName() + " купил - ");
                for (Product p : boughtProducts) {
                    System.out.print("" + p.getTitle() + ", ");
                }
                System.out.println("Остаток денег: " + person.getCash() + "\n");
            }
        } */
        // Вариант 2 с методом
        for (String person : persons.keySet()) {

            System.out.println(persons.get(person));
        }
        scanner.close();
    }
}