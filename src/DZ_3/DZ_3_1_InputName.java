package DZ_3;

import java.util.Scanner;

public class DZ_3_1_InputName {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя: ");
        String name = scanner.nextLine();
        System.out.println("Hello, " + name + " !");
    }
}
