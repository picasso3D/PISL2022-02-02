package by.it.group973601.feshenko.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Видеорегистраторы и площадь 2.
Условие то же что и в задаче А.

        По сравнению с задачей A доработайте алгоритм так, чтобы
        1) он оптимально использовал время и память:
            - за стек отвечает элиминация хвостовой рекурсии,
            - за сам массив отрезков - сортировка на месте
            - рекурсионные вызовы должны проводится на основе 3-разбиения

        2) при поиске подходящих отрезков для точки реализуйте метод бинарного поиска,
        помните при реализации, что поиск множественный
        (т.е. отрезков, подходящих для точки, может быть много)

    Sample Input:
    2 3
    0 5
    7 10
    1 6 11
    Sample Output:
    1 0 0

*/


public class C_QSortOptimized {

    //отрезок
    private class Segment implements Comparable {
        int start;
        int stop;

        Segment(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public int compareTo(Object o) {
            //подумайте, что должен возвращать компаратор отрезков
            Segment right = (Segment) o;
            return Integer.compare(this.stop, right.stop);
        }
    }


    int[] getAccessory2(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //число отрезков отсортированного массива
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        //число точек
        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        //читаем сами отрезки
        for (int i = 0; i < n; i++) {
            //читаем начало и конец каждого отрезка
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }
        //читаем точки
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        //тут реализуйте логику задачи с применением быстрой сортировки
        //в классе отрезка Segment реализуйте нужный для этой задачи компаратор
        qSort(segments, 0, segments.length - 1);
        for (int i = 0; i < segments.length; i++) {
            System.out.println(segments[i].start + " " + segments[i].stop);
        }
        for (int i = 0; i < points.length; i++) {
            int j = 0;
            n = segments.length - 1;

            while (j <= n) {
                m = (j + n) / 2;
                if (points[i] >= segments[m].start && points[i] <= segments[m].stop) {
                    result[i]++;
                    break;
                } else if (segments[m].start < points[i]) {
                    n = m - 1;
                } else {
                    j = m + 1;
                }
            }

        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private void qSort(Segment[] segments, int left, int right) {
        if (right <= left) {
            return;
        }
        Segment v = segments[right];

        int i = left;
        int j = right - 1;
        int p = left - 1;
        int q = right;
        while (i < j) {
            while (v.compareTo(segments[i]) < 0) {
                i++;
            }
            while (v.compareTo(segments[j]) > 0) {
                j--;
            }
            if (i >= j) {
                break;
            }
            swap(segments, i, j);
            if (segments[i].compareTo(v) == 0) {
                p++;
                swap(segments, p, i);
            }
            i++;
            if (segments[j].compareTo(v) == 0) {
                q--;
                swap(segments, q, j);
            }
            j--;
        }
        swap(segments, i, right);
        j = i - 1;
        i++;

        for (int k = 0; k < p; k++, j--) {
            swap(segments, k, j);
        }
        for (int k = right - 1; k > q; k--, i++) {
            swap(segments, k, i);
        }

        if (j - left < right - j) {
            qSort(segments, left, j);
        } else {
            qSort(segments, i, right);
        }
    }


    private <T> void swap(T[] arr, int from, int to) {
        T temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group973601/feshenko/lesson05/dataC.txt");
        C_QSortOptimized instance = new C_QSortOptimized();
        int[] result = instance.getAccessory2(stream);
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

}
