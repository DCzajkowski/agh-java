package lab2.Matrix;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(new double[][]{
            {1, 2, 3},
            {1},
            {1, 2, 3, 4},
        });

        System.out.println(matrix.toString());

        Matrix matrix1 = new Matrix(new double[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
        });

        Matrix matrix2 = new Matrix(new double[][]{
            {13, 14, 15, 16},
            {17, 18, 19, 20},
            {21, 22, 23, 24},
        });

        System.out.println(matrix1.add(matrix2).toString());
        System.out.println(matrix1.sub(matrix2).toString());
        System.out.println(matrix1.mul(matrix2).toString());
        System.out.println(matrix1.div(matrix2).toString());
        System.out.println(matrix1.dot(matrix2).toString());

        Matrix m = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        Matrix mT = m.getTransposition();
        System.out.println(mT.toString());
    }
}
