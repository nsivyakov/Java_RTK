package DZ_3;

import java.util.Random;

public class DZ_3_2_RPS {
    public static void main(String[] args){
        Random random = new Random();
        int vx = random.nextInt(4); // Назначаем случайное значение Васе
        int px = random.nextInt(4); // Назначаем случайное значение Пете
        System.out.println(rps(vx));
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
