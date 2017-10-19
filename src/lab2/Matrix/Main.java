package lab2.Matrix;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(new double[][] {
            {1, 2, 3},
            {1},
            {1, 2, 3, 4}
        });

        System.out.println(matrix.toString());
    }
}
