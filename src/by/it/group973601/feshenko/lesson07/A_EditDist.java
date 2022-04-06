package by.it.group973601.feshenko.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class A_EditDist {
    int[][] chars;
    String one, two;

    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        this.one = one;
        this.two = two;

        chars = new int[one.length() + 1][two.length() + 1];
        for (int i = 0; i < chars.length; i++) {
            Arrays.fill(chars[i], Integer.MIN_VALUE);
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return editDistTd(one.length(), two.length());
    }

    int editDistTd(int i, int j){
        if (chars[i][j] == Integer.MIN_VALUE){
            if (i == 0){
                chars[i][j] = j;
            }
            else if (j == 0){
                chars[i][j] = i;
            }else {
                int ins = editDistTd(i, j - 1) + 1;
                int del = editDistTd(i - 1, j) + 1;
                int sub = editDistTd(i - 1, j - 1) + ((one.charAt(i - 1) != two.charAt(j - 1)) ? 1 : 0);
                chars[i][j] = min(ins, del, sub);
            }
        }
        return chars[i][j];
    }

    static int min(int a, int b, int c){
        return Math.min(Math.min(a, b), c);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}
