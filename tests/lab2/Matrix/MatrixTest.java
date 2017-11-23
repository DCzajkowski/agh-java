package lab2.Matrix;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    private Matrix matrix1 = new Matrix(new double[][]{
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12},
    });

    private Matrix matrix2 = new Matrix(new double[][]{
        {13, 14, 15, 16},
        {17, 18, 19, 20},
        {21, 22, 23, 24},
    });

    @Test
    void test_array_constructor() {
        assertArrayEquals(new double[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
        }, new Matrix(new double[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
        }).asArray());
    }

    @Test
    void test_size_constructor() {
        assertArrayEquals(new double[][]{
            {0, 0},
            {0, 0},
            {0, 0},
        }, new Matrix(3, 2).asArray());
    }

    @Test
    void test_reshape() {
        assertEquals(3, matrix1.asArray().length);
        matrix1.reshape(4, 3);
        assertEquals(4, matrix1.asArray().length);
    }

    @Test
    void test_reshape_throws_exception_for_invalid_parameters() {
        Executable matrixReshape = () -> matrix1.reshape(4, 4);

        assertThrows(RuntimeException.class, matrixReshape, "Exception was expected to be thrown for illegal argument values.");
    }

    @Test
    void test_shape() {
        assertArrayEquals(new int[]{1, 1}, new Matrix(1, 1).shape());
        assertArrayEquals(new int[]{3, 4}, matrix1.shape());
    }

    @Test
    void test_add() {
        assertArrayEquals(
            matrix1.add(matrix2).asArray(),
            matrix2.add(matrix1).asArray()
        );
        assertArrayEquals(
            new double[][]{{14, 16, 18, 20}, {22, 24, 26, 28}, {30, 32, 34, 36}},
            matrix1.add(matrix2).asArray()
        );
    }

    @Test
    void test_sub() {
        assertArrayEquals(
            new double[][]{{-12, -12, -12, -12}, {-12, -12, -12, -12}, {-12, -12, -12, -12}},
            matrix1.sub(matrix2).asArray()
        );
        assertArrayEquals(
            new double[][]{{12, 12, 12, 12}, {12, 12, 12, 12}, {12, 12, 12, 12}},
            matrix2.sub(matrix1).asArray()
        );
    }

    @Test
    void test_mul() {
        assertArrayEquals(
            matrix1.mul(matrix2).asArray(),
            matrix2.mul(matrix1).asArray()
        );
        assertArrayEquals(
            new double[][]{{13.0, 28.0, 45.0, 64.0}, {85.0, 108.0, 133.0, 160.0}, {189.0, 220.0, 253.0, 288.0}},
            matrix1.mul(matrix2).asArray()
        );
    }

    @Test
    void test_div() {
        Matrix matrix1 = new Matrix(new double[][]{
            {16, 8, 4},
            {12, 9, 6},
        });

        Matrix matrix2 = new Matrix(new double[][]{
            {2, 2, 2},
            {3, 3, 3},
        });

        assertArrayEquals(
            new double[][]{{8, 4, 2}, {4, 3, 2}},
            matrix1.div(matrix2).asArray()
        );
    }

    /**
     * Pop quiz
     */

    @Test
    void test_getTransposition() {
        /* Generate matrix */

        Random random = new Random();
        int n = random.nextInt(100) + 1;
        int m = random.nextInt(100) + 1;

        Matrix matrix = new Matrix(n, m);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix.set(i, j, random.nextDouble());
            }
        }

        /* Test shapes */

        int[] matrixShape = matrix.shape();
        assertEquals(n, matrixShape[0]);
        assertEquals(m, matrixShape[1]);

        Matrix transposition = matrix.getTransposition();

        int[] transpositionShape = transposition.shape();
        assertEquals(m, transpositionShape[0]);
        assertEquals(n, transpositionShape[1]);

        /* Test frobenius */

        assertEquals(
            (double) Math.round(matrix.frobenius() * 100000d) / 100000d,
            (double) Math.round(transposition.frobenius() * 100000d) / 100000d
        );

        /* Test transposition ran twice gives the same matrix */

        assertArrayEquals(
            matrix.asArray(),
            transposition.getTransposition().asArray()
        );

        /* Test matrix times its transpose */

        // This test only works for square matrix, as it should.
        // Matrix result = matrix.dot(transposition);

        // double sumOnDiagonal = 0;

        // for (int i = 0; i < ((n > m) ? m : n); i++) {
        //     sumOnDiagonal += result.get(i, i);
        // }

        // assertEquals(
        //     (double) Math.round(matrix.frobenius() * 100000d) / 100000d,
        //     (double) Math.round(sumOnDiagonal * 100000d) / 100000d
        // );
    }

    /**
     * End of pop quiz
     */

    @Test
    void test_transposition() {
        assertArrayEquals(
            new double[][]{{1, 4, 7}, {2, 5, 8}, {3, 6, 9}},
            new Matrix(new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}).getTransposition().asArray()
        );

        assertArrayEquals(
            new double[][]{{1, 3, 5}, {2, 4, 6}},
            new Matrix(new double[][]{{1, 2}, {3, 4}, {5, 6}}).getTransposition().asArray()
        );
    }

    @Test
    void test_dot() {
        assertArrayEquals(
            new double[][]{{110.0, 116.0, 122.0, 128.0}, {314.0, 332.0, 350.0, 368.0}, {518.0, 548.0, 578.0, 608.0}},
            matrix1.dot(matrix2).asArray()
        );
    }

    @Test
    void test_add1() {
        assertArrayEquals(
            new double[][]{{3, 4, 5, 6}, {7, 8, 9, 10}, {11, 12, 13, 14}},
            matrix1.add(2).asArray()
        );
    }

    @Test
    void test_sub1() {
        assertArrayEquals(
            new double[][]{{-1, 0, 1, 2}, {3, 4, 5, 6}, {7, 8, 9, 10}},
            matrix1.sub(2).asArray()
        );
    }

    @Test
    void test_mul1() {
        assertArrayEquals(
            new double[][]{{2, 4, 6, 8}, {10, 12, 14, 16}, {18, 20, 22, 24}},
            matrix1.mul(2).asArray()
        );
    }

    @Test
    void test_div1() {
        assertArrayEquals(
            new double[][]{{2, 4, 8}},
            new Matrix(new double[][]{{4, 8, 16}}).div(2).asArray()
        );
    }

    @Test
    void test_frobenius() {
        assertEquals(
            336.0,
            new Matrix(new double[][]{{4, 8, 16}}).frobenius()
        );
    }

    @Test
    void test_asArray() {
        assertArrayEquals(
            new double[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}},
            matrix1.asArray()
        );
    }

    @Test
    void test_get() {
        assertEquals(7.0, matrix1.get(1, 2));
    }

    @Test
    void test_set() {
        assertEquals(7.0, matrix1.get(1, 2));
        matrix1.set(1, 2, 5);
        assertEquals(5.0, matrix1.get(1, 2));
    }

    @Test
    void test_toString() {
        assertEquals(
            "[[1.0]]",
            new Matrix(new double[][]{{1}}).toString()
        );
        assertEquals(
            "[[1.0, 2.0]]",
            new Matrix(new double[][]{{1, 2}}).toString()
        );
        assertEquals(
            "[[1.0, 2.0, 3.0, 4.0], [1.0, 2.0, 3.0, 4.0], [1.0, 2.0, 3.0, 4.0]]",
            new Matrix(new double[][]{{1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}}).toString()
        );
        assertEquals(
            "[[1.0, 2.0, 3.0, 0.0], [1.0, 0.0, 0.0, 0.0], [1.0, 2.0, 3.0, 4.0]]",
            new Matrix(new double[][]{{1, 2, 3}, {1}, {1, 2, 3, 4}}).toString()
        );
    }
}
