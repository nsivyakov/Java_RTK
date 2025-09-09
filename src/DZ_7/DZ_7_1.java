package DZ_7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* Реализовать метод, который на вход принимает ArrayList<T>, а возвращает набор
уникальных элементов этого массива. Решить, используя коллекции */
public class DZ_7_1 {
    public static class List_unique<T> {
        private ArrayList<T> list = new ArrayList<>();
        public void add(T element) {
            list.add(element);
        }
        public static <T> Set<T> arrlistToSet(ArrayList<T> list) {
            return new HashSet<>(list);
                    }
        }
// Проверка
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("gg98");
        list1.add("gg");
        list1.add("gg");
        list1.add("gg1");
        list1.add("gg1");
        list1.add("gg1");
        list1.add("gg091");
        Set<String> set1 = List_unique.arrlistToSet(list1);
        System.out.println(set1);
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(1);
        list2.add(2);
        list2.add(2);
        list2.add(3);
        Set<Integer> set2 = List_unique.arrlistToSet(list2);
        System.out.println(set2);
    }
}
