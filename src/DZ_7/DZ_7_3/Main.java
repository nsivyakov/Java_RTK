package DZ_7.DZ_7_3;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        PowerfulSet pwrset = new PowerfulSet();

        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        Set<Integer> set2 = new HashSet<>();
        set2.add(0);
        set2.add(1);
        set2.add(2);
        set2.add(4);

        System.out.println("Пересечение: " + pwrset.intersection(set1, set2));
        System.out.println("Объединение: " + pwrset.union(set1, set2));
        System.out.println("Уникальные 1-го набора: " + pwrset.relativeComplement(set1, set2));
    }
}
