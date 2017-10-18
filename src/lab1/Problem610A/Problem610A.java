package lab1.Problem610A;

import java.util.Scanner;

public class Problem610A {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        if (n < 1 || n > 2e9) {
            return;
        }

        System.out.println(stick(n));

        // Tests. Should print 'true'
        System.out.println(stick(6) == 1);
        System.out.println(stick(20) == 4);
    }

    private static int stick(int n) {
        int ways = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (i != j && i * 2 + j * 2 == n) {
                    ways += 1;
                }
            }
        }
        return ways / 2;
    }
}
