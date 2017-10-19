package lab2.Matrix;

public class Main {
    public static void main(String[] args) {
        double[][] elements = {
            {1, 2, 3},
            {1},
            {1, 2, 3, 4}
        };

        Matrix matrix = new Matrix(elements);

        System.out.println(matrix.toString());
    }
}
