package DZ_5;

import java.util.Scanner;

public class DZ_5_2 {
    /*Задана последовательность, состоящая только из символов ‘>’,
‘<’ и ‘-‘. Требуется найти количество стрел, которые спрятаны в этой
последовательности. Стрелы – это подстроки вида ‘>>-->’ и ‘<--<<’.
Входные данные: в первой строке входного потока записана строка,
состоящая из символов ‘>’, ‘<’ и ‘-‘ (без пробелов). Строка может содержать до
106 символов.
Выходные данные: в единственную строку выходного потока нужно
вывести искомое количество стрелок.*/

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите последовательность символов ‘>’,‘<’ и ‘-‘.: ");
        String inpt = scn.nextLine();
        int s = 0;
        int ind1 = 0;
        int ind2 = 5;
        if (inpt.length() < 5) {
            System.out.println("Стрелки не могут быть найдены - слишком короткая строка!");
        } else {
            for (int i = 0; i <= inpt.length() - 5; i++) {
                //System.out.println(inpt.length() - 4);
                if (inpt.substring(ind1, ind2).equals(">>-->") || inpt.substring(ind1, ind2).equals("<--<<")) {
                    s += 1;
                    //System.out.println(inpt.substring(ind1, ind2));
                }
                ind1 += 1;
                ind2 += 1;
                //System.out.println(ind1 +" " + ind2);
            }
        }
        System.out.println("Всего найдено стрелок: " + s);
    }
}
