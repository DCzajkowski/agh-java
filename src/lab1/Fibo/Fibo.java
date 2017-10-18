package lab1.Fibo;

import java.util.Scanner;

public class Fibo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        if (n < 1 || n > 45) {
            return;
        }

        System.out.print(arrayJoin(fillWithFibonacci(n), ", "));
    }

    private static String arrayJoin(int[] array, String separator) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < array.length - 1; i++) {
            sb.append(String.valueOf(array[i]));
            sb.append(separator);
        }

        sb.append(String.valueOf(array[array.length - 1]));

        return sb.toString();
    }

    private static int[] fillWithFibonacci(int n) {
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = (i == 0 || i == 1) ? 1 : array[i - 1] + array[i - 2];
        }

        return array;
    }
}
