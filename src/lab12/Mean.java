package lab12;

import java.util.Arrays;

/*
    Results:
    Given test shows, that making all of this code is pretty inefficient, because the results
    are at least 3 times slower than the built-in .sum() method and simple division.

    All of this extra code, thread manipulation etc. could be just simply changed to the following one-liner:
        double builtInMean = Arrays.stream(Mean.array).sum() / Mean.array.length;
*/

public class Mean {
    static class MeanCalc extends Thread {
        private final int start;
        private final int end;

        protected double mean = 0;

        public MeanCalc(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void run() {
            for (int i = this.start; i < this.end; i++) {
                this.mean += Mean.array[i];
            }

            this.mean /= this.end - this.start;

            System.out.printf("%d - %d mean = %f\n", this.start, this.end, this.mean);
        }
    }

    static double[] array;

    public static void main(String[] args) throws InterruptedException {
        Mean.initArray(10000000);
        Mean.parallelMean(100);
    }

    static void initArray(int size) {
        Mean.array = new double[size];

        for (int i = 0; i < size; i++) {
            Mean.array[i] = Math.random() * size / (i + 1);
        }
    }

    static void parallelMean(int threadCount) throws InterruptedException {
        MeanCalc[] threads = new MeanCalc[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new MeanCalc(i * (Mean.array.length / threadCount), (i + 1) * (Mean.array.length / threadCount));
        }

        double t1 = System.nanoTime() / 1e6;

        Arrays.stream(threads).forEach(Thread::start);

        double t2 = System.nanoTime() / 1e6;

        for (MeanCalc thread : threads) {
            thread.join();
        }

        double mean = Arrays.stream(threads).mapToDouble(thread -> thread.mean).sum() / threadCount;

        double t3 = System.nanoTime() / 1e6;

        double builtInMean = Arrays.stream(Mean.array).sum() / Mean.array.length;

        double t4 = System.nanoTime() / 1e6;

        System.out.printf("size = %d\nthreadCount = %d\nt2 - t1 = %f\nt3 - t1 = %f\nmean = %f\nreal mean: %f\nbuilt-in mean time: %f\n",
            Mean.array.length,
            threadCount,
            t2 - t1,
            t3 - t1,
            mean,
            builtInMean,
            t4 - t3
        );
    }
}
