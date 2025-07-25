package DZ_5;

import java.util.Scanner;

public class DZ_5_1 {
    /*Задача 1. Для введенной с клавиатуры буквы английского алфавита
нужно вывести слева стоящую букву на стандартной клавиатуре. При этом
клавиатура замкнута, т.е. справа от буквы «p» стоит буква «a», а слева от "а"
буква "р", также соседними считаются буквы «l» и буква «z», а буква «m» с
буквой «q».
Входные данные: строка входного потока содержит один символ —
маленькую букву английского алфавита.
Выходные данные: следует вывести букву стоящую слева от заданной
буквы, с учетом замкнутости клавиатуры.
*/
    public static void main(String[] args) {
        String klv = "qйwцeуrкtеyнuгiшoщpзaфsыdвfаgпhрjоkлlдzяxчcсvмbиnтmь";
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите букву: ");
        String wrd = scn.nextLine();
        int length = wrd.length();
        int length1 = klv.length();
        int indklv = klv.indexOf(wrd);
        if (length > 1) {
            System.out.println("Вы ввели не 1 букву, а " + length + ". Попробуйте снова.");
        } else if (indklv != -1) {
            if (indklv == 0) {
                System.out.println(klv.charAt(length1 - 1));
            } else if (indklv == length1 - 1) {
                System.out.println(klv.charAt(0));
            } else {
                System.out.println(klv.charAt(indklv - 1));
            }
        } else {
            System.out.println("Значение не найдено!");
        }
    }
}
