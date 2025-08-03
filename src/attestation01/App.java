package attestation01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Person> people = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        System.out.println("Введите покупателей:");
        // Ввод покупателей
        while (true) {
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("END")) {
                break;
            }
            String[] parts = line.split(";");

            //String personPart = parts[0].trim();
            //String moneyPart = parts[1].trim();

            for (String entry : parts) {
                // Удаляем лишние пробелы вокруг
                entry = entry.trim();

                // Шаг 2: Разделить по '='
                String[] part = entry.split("=");
                if (parts.length == 1) {
                    System.out.println("Некорректный формат: " + entry);
                    continue; // пропускаем неправильные строки
                }
                String personPart = part[0].trim();
                String moneyPart = part[1].trim();
                System.out.println(personPart + "  " + moneyPart);
                try {
                    // Вводим имя
                    //String[] nameParts = personPart.split("\\s+");
                    //String name = nameParts[nameParts.length - 2] + " " + nameParts[nameParts.length - 1]; // например для "Павел Андреевич"
                    // Лучше взять весь first part как имя, если оно состоит из нескольких слов, например, "Павел Андреевич"
                    // Тогда проще: считать имя как весь personPart
                    //String fullName = personPart;

                    if (personPart.trim().isEmpty()) {
                        System.out.println("Имя не может быть пустым");
                        continue;
                    }
                    if (personPart.trim().length() < 3) {
                        System.out.println("Имя не может быть короче 3 символов");
                        continue;
                    }

                    Integer amount = Integer.valueOf(moneyPart);
                    if (amount < 0) {
                        System.out.println("Деньги не могут быть отрицательными");
                        continue;
                    }
                    Person p = new Person(personPart, amount);
                    people.add(p);
                    System.out.println(people);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }


        System.out.println("Введите продукты:");
        // Ввод продуктов
        while (true) {
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("END")) {
                break;
            }
            String[] parts = line.split("=");
            if (parts.length != 2) {
                System.out.println("Некорректный формат. Используйте: Название, Цена");
                continue;
            }
            String productName = parts[0].trim();
            String priceStr = parts[1].trim();
            try {
                if (productName.isEmpty()) {
                    System.out.println("Название продукта не может быть пустым");
                    continue;
                }
                Integer price = Integer.valueOf(priceStr);
                if (price < 0) {
                    System.out.println("Стоимость не может быть отрицательной");
                    continue;
                }
                Product product = new Product(productName, price);
                products.add(product);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // Обработка покупок
        System.out.println("Начинаем процесс покупок:");
        for (
                Person person : people) {
            boolean purchasedAny = false;
            // Перебираем продукты
            for (Product product : new ArrayList<>(products)) {
                boolean bought = person.buyProduct(product);
                if (bought) {
                    System.out.println(person.getName() + " купил " + product.getTitle());
                    purchasedAny = true;
                } else {
                    System.out.println(person.getName() + " не может позволить себе " + product.getTitle());
                }
            }
            if (!purchasedAny) {
                System.out.println(person.getName() + " - Ничего не куплено");
            }
        }

        // Вывод итогов
        System.out.println("Итоговые покупки:");
        for (
                Person person : people) {
            System.out.println(person.toString());
        }
        scanner.close();
    }
}
