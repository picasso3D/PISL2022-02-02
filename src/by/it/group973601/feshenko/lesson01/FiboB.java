package by.it.group973601.feshenko.lesson01;

import java.math.BigInteger;

/*
 * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
 * без ограничений на размер результата (BigInteger)
 */

public class FiboB {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        //вычисление чисел простым быстрым методом
        FiboB fibo = new FiboB();
        int n = 55555;
        System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
    }

    BigInteger fastB(Integer n) {
        int size = n < 0 ? 1 : n + 1;
        BigInteger[] buffer = new BigInteger[size];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = i > 1 ? buffer[i - 1].add(buffer[i - 2]) : BigInteger.valueOf(i);
        }
        return buffer[size - 1];
    }

}

