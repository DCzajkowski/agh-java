package lab11;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class DownloadExample {
    private static AtomicInteger count = new AtomicInteger();
    private static Semaphore semaphore = new Semaphore(0);
    private static String[] toDownload = {
        "http://home.agh.edu.pl/pszwed/wyklad-c/01-jezyk-c-intro.pdf",
        "http://home.agh.edu.pl/~pszwed/wyklad-c/02-jezyk-c-podstawy-skladni.pdf",
        "http://home.agh.edu.pl/~pszwed/wyklad-c/03-jezyk-c-instrukcje.pdf",
        "http://home.agh.edu.pl/~pszwed/wyklad-c/04-jezyk-c-funkcje.pdf",
        "http://home.agh.edu.pl/~pszwed/wyklad-c/05-jezyk-c-deklaracje-typy.pdf",
        "http://home.agh.edu.pl/~pszwed/wyklad-c/06-jezyk-c-wskazniki.pdf",
        "http://home.agh.edu.pl/~pszwed/wyklad-c/07-jezyk-c-operatory.pdf",
        "http://home.agh.edu.pl/~pszwed/wyklad-c/08-jezyk-c-lancuchy-znakow.pdf",
        "http://home.agh.edu.pl/~pszwed/wyklad-c/09-jezyk-c-struktura-programow.pdf",
        "http://home.agh.edu.pl/~pszwed/wyklad-c/10-jezyk-c-dynamiczna-alokacja-pamieci.pdf",
        "http://home.agh.edu.pl/~pszwed/wyklad-c/11-jezyk-c-biblioteka-we-wy.pdf",
        "http://home.agh.edu.pl/~pszwed/wyklad-c/preprocesor-make-funkcje-biblioteczne.pdf",
    };

    public static void main(String[] args) {
        System.out.println("Starting your download...");

        // DownloadExample.sequentialDownload();
        // DownloadExample.concurrentDownload();
        // DownloadExample.concurrentDownload2();
        // DownloadExample.concurrentDownload3();
    }

    private static void sequentialDownload() {
        double t1 = System.nanoTime() / 1e6;

        for (String url : DownloadExample.toDownload) {
            new Downloader(url).run();
        }

        double t2 = System.nanoTime() / 1e6;

        System.out.printf("t2 - t1 = %f\n", t2 - t1);
    }

    private static void concurrentDownload() {
        double t1 = System.nanoTime() / 1e6;

        for (String url : DownloadExample.toDownload) {
            new Downloader(url).start();
        }

        double t2 = System.nanoTime() / 1e6;

        System.out.printf("t2 - t1 = %f\n", t2 - t1); // Given time is of course incorrect, because this line runs before threads end.
    }

    private static void concurrentDownload2() {
        double t1 = System.nanoTime() / 1e6;

        for (String url : DownloadExample.toDownload) {
            new Downloader(url).start();
        }

        while (DownloadExample.count.get() != DownloadExample.toDownload.length) {
            Thread.yield();
        }

        double t2 = System.nanoTime() / 1e6;

        System.out.printf("t2 - t1 = %f\n", t2 - t1);
    }

    private static void concurrentDownload3() {
        double t1 = System.nanoTime() / 1e6;

        for (String url : DownloadExample.toDownload) {
            new Downloader(url).start();
        }

        try {
            DownloadExample.semaphore.acquire(DownloadExample.toDownload.length);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double t2 = System.nanoTime() / 1e6;

        System.out.printf("t2 - t1 = %f\n", t2 - t1);
    }

    public static void incrementCounter() {
        DownloadExample.semaphore.release();
        if (DownloadExample.count != null) DownloadExample.count.addAndGet(1);
    }
}
