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
        double[][] filled = this.prepare(data);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.set(i, j, filled[i][j]);
            }
        }
    }

    private void setMatrixDetails(double[][] data) {
        this.rows = data.length;
        this.cols = this.rowLength(data);
        this.data = new double[this.rows * this.cols];
    }

    private double[][] prepare(double[][] data) {
        double[][] newData = new double[this.rows][this.cols];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                try {
                    newData[i][j] = data[i][j];
                } catch (IndexOutOfBoundsException e) {
                    newData[i][j] = 0;
                }
            }
        }

        return newData;
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

    Matrix add(double value) {
        Matrix result = new Matrix(this.rows, this.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.set(i, j, this.get(i, j) + value);
            }
        }

        return result;
    }

    Matrix sub(double value) {
        Matrix result = new Matrix(this.rows, this.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.set(i, j, this.get(i, j) - value);
            }
        }

        return result;
    }

    Matrix mul(double value) {
        Matrix result = new Matrix(this.rows, this.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.set(i, j, this.get(i, j) * value);
            }
        }

        return result;
    }

    Matrix div(double value) {
        Matrix result = new Matrix(this.rows, this.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.set(i, j, this.get(i, j) / value);
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
