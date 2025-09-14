package DZ_7;

import java.util.Arrays;
import java.util.Scanner;

/* С консоли на вход подается две строки s и t. Необходимо вывести true, если одна
строка является валидной анаграммой другой строки, и false – если это не так.
Анаграмма – это слово, или фраза, образованная путем перестановки букв другого
слова или фразы, обычно с использованием всех исходных букв ровно один раз.
Для проверки:
● Бейсбол – бобслей
● Героин – регион
● Клоака – околка */
public class DZ_7_2 {
    public static boolean isAnagram(String s, String t) {
        String sCln = s.replaceAll(" ", "")
                .replaceAll(",", "")
                .replaceAll("-", "")
                .toLowerCase();
        String tCln = t.replaceAll(" ", "")
                .replaceAll(",", "")
                .replaceAll("-", "")
                .toLowerCase();

        if (sCln.length() != tCln.length()) {
            return false;
        }

        char[] sChars = sCln.toCharArray();
        char[] tChars = tCln.toCharArray();

        Arrays.sort(sChars);
        Arrays.sort(tChars);

        return Arrays.equals(sChars, tChars);
    }

    // Проверка
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите первую строку:");
        String s = scanner.nextLine();
        System.out.println("Введите вторую строку:");
        String t = scanner.nextLine();

        boolean result = isAnagram(s, t);
        System.out.println(result);
    }
}
