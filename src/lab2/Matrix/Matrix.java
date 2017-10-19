package lab2.Matrix;

public class Matrix {
    private double[] data;
    private int rows;
    private int cols;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows * cols];
    }

    public Matrix(double[][] data) {
        this.rows = data.length;

        int rowLength = rowLength(data);

        this.cols = rowLength;
        this.data = new double[this.rows * this.cols];

        int k = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                this.data[k] = data[i][j];
                k += 1;
            }

            if (data[i].length < this.cols) {
                for (int j = 0; j < rowLength - data[i].length; j++) {
                    this.data[k] = 0;
                    k += 1;
                }
            }
        }
    }

    private int rowLength(double[][] data) {
        int rowLength = 0;

        for (double[] row : data) {
            if (row.length > rowLength) {
                rowLength = row.length;
            }
        }

        return rowLength;
    }

    public double[][] asArray() {
        double[][] result = new double[this.rows][this.cols];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result[i][j] = this.data[i * this.cols + j];
            }
        }

        return result;
    }

    public double get(int r, int c) {
        return this.data[(r * this.cols) + c];
    }

    public void set(int r, int c, double value) {
        this.data[(r * this.cols) + c] = value;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        double[][] matrix = this.asArray();

        result.append("[");

        for (int i = 0; i < this.rows; i++) {
            result.append("[");
            for (int j = 0; j < this.cols; j++) {
                result.append(matrix[i][j]).append(", ");
            }
            result.delete(result.length() - 2, result.length());
            result.append("], ");
        }

        result.delete(result.length() - 2, result.length());
        result.append("]");

        return result.toString();
    }
}
