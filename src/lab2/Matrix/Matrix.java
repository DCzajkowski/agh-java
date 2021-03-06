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
        this.setMatrixDetails(data);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                if (j >= data[i].length) {
                    this.set(i, j, 0);
                } else {
                    this.set(i, j, data[i][j]);
                }
            }
        }
    }

    private void setMatrixDetails(double[][] data) {
        this.rows = data.length;
        this.cols = this.rowLength(data);
        this.data = new double[this.rows * this.cols];
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

    public void reshape(int rows, int cols) {
        if (this.rows * this.cols != rows * cols) {
            throw new RuntimeException(
                String.format("%d x %d matrix can't be reshaped to %d x %d", this.rows, this.cols, rows, cols)
            );
        }

        this.rows = rows;
        this.cols = cols;
    }

    public int[] shape() {
        return new int[]{this.rows, this.cols};
    }

    public Matrix add(Matrix matrix) {
        Matrix result = new Matrix(this.rows, this.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.set(i, j, this.get(i, j) + matrix.get(i, j));
            }
        }

        return result;
    }

    public Matrix sub(Matrix matrix) {
        Matrix result = new Matrix(this.rows, this.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.set(i, j, this.get(i, j) - matrix.get(i, j));
            }
        }

        return result;
    }

    public Matrix mul(Matrix matrix) {
        Matrix result = new Matrix(this.rows, this.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.set(i, j, this.get(i, j) * matrix.get(i, j));
            }
        }

        return result;
    }

    public Matrix div(Matrix matrix) {
        Matrix result = new Matrix(this.rows, this.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.set(i, j, this.get(i, j) / matrix.get(i, j));
            }
        }

        return result;
    }

    public Matrix dot(Matrix matrix) {
        Matrix result = new Matrix(this.rows, matrix.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < matrix.cols; j++) {
                result.set(i, j, 0);
                for (int k = 0; k < matrix.rows; k++) {
                    result.set(i, j, result.get(i, j) + this.get(i, k) * matrix.get(k, j));
                }
            }
        }

        return result;
    }

    public Matrix add(double value) {
        Matrix result = new Matrix(this.rows, this.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.set(i, j, this.get(i, j) + value);
            }
        }

        return result;
    }

    public Matrix sub(double value) {
        Matrix result = new Matrix(this.rows, this.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.set(i, j, this.get(i, j) - value);
            }
        }

        return result;
    }

    public Matrix mul(double value) {
        Matrix result = new Matrix(this.rows, this.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.set(i, j, this.get(i, j) * value);
            }
        }

        return result;
    }

    public Matrix div(double value) {
        Matrix result = new Matrix(this.rows, this.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.set(i, j, this.get(i, j) / value);
            }
        }

        return result;
    }

    public Matrix getTransposition() {
        Matrix result = new Matrix(this.cols, this.rows);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.set(j, i, this.get(i, j));
            }
        }

        return result;
    }

    public double frobenius() {
        double result = 0;

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result += Math.pow(this.get(i, j), 2);
            }
        }

        return result;
    }

    public double[][] asArray() {
        double[][] result = new double[this.rows][this.cols];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result[i][j] = this.get(i, j);
            }
        }

        return result;
    }

    public double get(int r, int c) {
        return this.data[r * this.cols + c];
    }

    public void set(int r, int c, double value) {
        this.data[r * this.cols + c] = value;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("[");

        for (int i = 0; i < this.rows; i++) {
            result.append("[");
            for (int j = 0; j < this.cols; j++) {
                result.append(this.get(i, j)).append(", ");
            }
            result.delete(result.length() - 2, result.length());
            result.append("], ");
        }

        result.delete(result.length() - 2, result.length());
        result.append("]");

        return result.toString();
    }
}
