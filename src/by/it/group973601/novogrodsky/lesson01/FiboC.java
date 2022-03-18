package by.it.group973601.novogrodsky.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

import java.util.ArrayList;
import java.util.List;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    long fasterC(long n, int m) {
        //решение практически невозможно найти интуитивно
        //вам потребуется дополнительный поиск информации
        //см. период Пизано
        List<Long> fibocList = new ArrayList<>();
        fibocList.add(0L);
        fibocList.add(1L);

        for (int i = 2; i < (m * 6); i++) {
            fibocList.add((fibocList.get(i - 1) + fibocList.get(i - 2)) % m);
            if (fibocList.get(i) == 1 && fibocList.get(i - 1) == 0) {
                fibocList.remove(i);
                fibocList.remove(i - 1);
                break;
            }
        }

        return fibocList.get((int) n % fibocList.size());
    }
}