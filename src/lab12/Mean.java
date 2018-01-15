package lab12;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

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

            try {
                Mean.results.put(this.mean);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // System.out.printf("%d - %d mean = %f\n", this.start, this.end, this.mean);
        }
    }

    static double[] array;
    static BlockingQueue<Double> results = new ArrayBlockingQueue<>(100);

    public static void main(String[] args) throws InterruptedException {
        Mean.initArray(50000000);

        for (int threadCount : new int[]{1, 2, 4, 8, 16, 32, 64, 128}) {
            Mean.parallelMean(threadCount);
        }

        Mean.builtInMean();
    }

    private static void builtInMean() {
        double t1 = System.nanoTime() / 1e6;

        double builtInMean = Arrays.stream(Mean.array).sum() / Mean.array.length;

        double t2 = System.nanoTime() / 1e6;

        System.out.printf(
            "built-in -> %f\t\tmean = %f\n",
            t2 - t1, builtInMean
        );
    }

    private static void initArray(int size) {
        Mean.array = new double[size];

        for (int i = 0; i < size; i++) {
            Mean.array[i] = Math.random() * size / (i + 1);
        }
    }

    private static void parallelMean(int threadCount) throws InterruptedException {
        MeanCalc[] threads = new MeanCalc[threadCount];

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new MeanCalc(i * (Mean.array.length / threadCount), (i + 1) * (Mean.array.length / threadCount));
        }

        double t1 = System.nanoTime() / 1e6;

        Arrays.stream(threads).forEach(Thread::start);

        double t2 = System.nanoTime() / 1e6;

        double mean = 0;

        for (int i = 0; i < threadCount; i++) {
            mean += Mean.results.take();
        }

        mean /= threadCount;

        double t3 = System.nanoTime() / 1e6;

        // System.out.printf(
        //     "size = %d\nthreadCount = %d\nt2 - t1 = %f\nt3 - t1 = %f\nmean = %f\n",
        //     Mean.array.length, threadCount, t2 - t1, t3 - t1, mean
        // );

        System.out.printf(
            "%d -> %f\t\tmean = %f\n",
            threadCount, t3 - t1, mean
        );
    }
}
