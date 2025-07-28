package DZ_5;

import java.util.Arrays;
import java.util.Scanner;

public class DZ_5_3 {

    /* Задана строка, состоящая из букв английского алфавита, разделенных одним пробелом.
    Необходимо каждую последовательность символов упорядочить по возрастанию и вывести слова в нижнем регистре.
    Входные данные: в единственной строке последовательность символов представляющее два слова.
    Выходные данные: упорядоченные по возрастанию буквы в нижнем регистре.*/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два слова через пробел:");
        String line = scanner.nextLine();
        String lowerLine = line.toLowerCase();
        String[] words = lowerLine.split(" ");
        if (words.length != 2 || line.indexOf(' ') == -1) {
            System.out.println("Ошибка, требуется ввести два слова через пробел");
        } else {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                char[] chars = word.toCharArray();
                Arrays.sort(chars);
                String sortedWord = new String(chars);
                result.append(sortedWord);
                if (i < words.length - 1) {
                    result.append(" ");
                }
            }
            String rslt=result.toString();
            System.out.println("Результат сортировки: " + rslt);
        }
    }
}

