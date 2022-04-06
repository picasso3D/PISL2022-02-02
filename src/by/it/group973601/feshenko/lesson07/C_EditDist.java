package by.it.group973601.feshenko.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,

    Sample Input 2:
    short
    ports
    Sample Output 2:
    -s,~p,#,#,#,+s,

    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,


    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/


public class C_EditDist {
    private static final char D = '-';
    private static final char I = '+';
    private static final char R = '~';
    private static final char M = '#';
    private static final char DELIM = ',';

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int[][] distBU = editDistBU(one.toCharArray(), two.toCharArray());

        StringBuilder builder = new StringBuilder();
        int i = one.length();
        int j = two.length();
        while (i != 0 || j != 0) {
            if (i == 0) {
                builder.append(DELIM).append(two.charAt(i)).append(D);
                j--;
            } else if (j == 0) {
                builder.append(DELIM).append(one.charAt(j)).append(I);
                i--;
            }else {
                int delete = distBU[i - 1][j];
                int insert = distBU[i][j - 1];
                int cost = ((one.charAt(i - 1) != two.charAt(j - 1)) ? 1 : 0);
                int sub = distBU[i - 1][j - 1];
                int min = min(delete, insert, sub);


                if (min == sub) {
                    if (cost == 1) {
                        builder.append(DELIM).append(two.charAt(j - 1)).append(R);
                    } else {
                        builder.append(DELIM).append(M);
                    }
                    i--;
                    j--;
                } else if (min == delete) {
                    builder.append(DELIM).append(one.charAt(i - 1)).append(D);
                    i--;
                } else if (min == insert) {
                    builder.append(DELIM).append(two.charAt(j - 1)).append(I);
                    j--;
                }
            }

        }


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return builder.reverse().toString();
    }

    int[][] editDistBU(char[] one, char[] two) {
        int[][] matrix = new int[one.length + 1][two.length + 1];

        for (int i = 0; i <= one.length; i++) {
            matrix[i][0] = i;
        }
        for (int i = 0; i <= two.length; i++) {
            matrix[0][i] = i;
        }

        for (int i = 1; i <= one.length; i++) {
            for (int j = 1; j <= two.length; j++) {
                int cost = ((one[i - 1] != two[j - 1]) ? 1 : 0);
                matrix[i][j] = min(matrix[i - 1][j] + 1,
                        matrix[i][j - 1] + 1,
                        matrix[i - 1][j - 1] + cost);
            }
        }
        return matrix;
    }

    static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }

}