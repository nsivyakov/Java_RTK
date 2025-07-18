package DZ_3;

import java.util.Random;

public class DZ_3_2_RPS {
    public static void main(String[] args){
        Random random = new Random();
        int vx = random.nextInt(3); // Назначаем случайное значение Васе
        int px = random.nextInt(3); // Назначаем случайное значение Пете
        if (vx==0) {
            if (px==1) {
                System.out.println("У Васи - " + rps(vx)
                        + ". У Пети - " + rps(px)
                        + System.lineSeparator() + "Выиграл Вася!");
            } else if (px==2) {
                System.out.println("У Васи - " + rps(vx)
                        + ". У Пети - " + rps(px)
                        + System.lineSeparator() + "Выиграл Петя!");
            }
        }
        if (vx==1) {
            if (px==0) {
                System.out.println("У Васи - " + rps(vx)
                        + ". У Пети - " + rps(px)
                        + System.lineSeparator() + "Выиграл Петя!");
            } else if (px==2) {
                System.out.println("У Васи - " + rps(vx)
                        + ". У Пети - " + rps(px)
                        + System.lineSeparator() + "Выиграл Вася!");
            }
        }
        if (vx==2) {
            if (px==0) {
                System.out.println("У Васи - " + rps(vx)
                        + ". У Пети - " + rps(px)
                        + System.lineSeparator() + "Выиграл Вася!");
            } else if (px==1) {
                System.out.println("У Васи - " + rps(vx)
                        + ". У Пети - " + rps(px)
                        + System.lineSeparator() + "Выиграл Петя!");
            }
        }
        if (vx==px) {
            System.out.println("У Васи - " + rps(vx)
                    + ". У Пети - " + rps(px)
                    + System.lineSeparator() + "Ничья! Нужно играть заново!");
        }
    }

    public static String rps (int var) {
        if (var==0) {
            return "Камень";
        } else if (var==1) {
            return "Ножницы";
        } else {
            return "Бумага";
        }
    }
}
