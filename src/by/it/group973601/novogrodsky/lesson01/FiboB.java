package by.it.group973601.novogrodsky.lesson01;

import java.math.BigInteger;
import java.util.stream.IntStream;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {
    private long startTime = System.currentTimeMillis();

    public static void main(String[] args) {
        //вычисление чисел простым быстрым методом
        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    BigInteger fastB(Integer n) {
        //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
        if (n == 0) {
            return BigInteger.ZERO;
        } else if (n == 1) {
            return BigInteger.ONE;
        } else {
            BigInteger[] fiboArray = new BigInteger[n];
            fiboArray[0] = BigInteger.ZERO;
            fiboArray[1] = BigInteger.ONE;

            IntStream.range(2, n)
                    .forEach(index -> fiboArray[index] = fiboArray[index - 1].add(fiboArray[index - 2]));

            return fiboArray[n - 1].add(fiboArray[n - 2]);
        }
    }
}

