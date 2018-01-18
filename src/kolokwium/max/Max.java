package kolokwium.max;

// Następnie wyznacza największy element tablicy dzieląc
// wyszukiwanie pomiędzy pewną liczbę wątków. Do agregacji wyników użyj kolejki.
// Wyświetl czasy działania. Do generacji liczb użyj

import lab12.Mean;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static java.util.Arrays.stream;

public class Max {
    static class MaxCalc extends Thread {
        private final int start;
        private final int end;

        protected Integer max;

        public MaxCalc(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void run() {
            for (int i = this.start; i < this.end; i++) {
                if (this.max == null || Max.array[i] > this.max) {
                    this.max = Max.array[i];
                }
            }

            try {
                Max.results.put(this.max);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static int[] array;
    static BlockingQueue<Integer> results = new ArrayBlockingQueue<>(100);

    public static void main(String[] args) throws InterruptedException {
        initArray(100000000);

        for (int threadCount : new int[]{1, 2, 4, 8, 16, 32, 64, 128}) {
            Max.parallelMax(threadCount);
        }

        // System.out.printf("Checksum: %d\n", stream(Max.array).max().orElse(0));
    }

    private static void parallelMax(int threadCount) throws InterruptedException {
        MaxCalc[] threads = new MaxCalc[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new MaxCalc(i * (Max.array.length / threadCount), (i + 1) * (Max.array.length / threadCount));
        }

        double t1 = System.nanoTime() / 1e6;

        stream(threads).forEach(Thread::start);

        double t2 = System.nanoTime() / 1e6;

        Integer max = null;

        for (int i = 0; i < threadCount; i++) {
            int maximumCalculated = Max.results.take();

            if (max == null || maximumCalculated > max) {
                max = maximumCalculated;
            }
        }

        double t3 = System.nanoTime() / 1e6;

        System.out.printf(
            "%d -> %f\t\tmaximum = %d\n",
            threadCount, t3 - t1, max
        );
    }

    private static void initArray(int size) {
        Max.array = new int[size];

        Random r = new Random();

        for (int i = 0; i < size; i++) {
            Max.array[i] = r.nextInt(100);
        }
    }
}
