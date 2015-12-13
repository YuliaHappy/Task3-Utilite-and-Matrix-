package com.epam.javauniversity.matrix;

public class Matrix {

    private int[][] matrix;
    private int rowCount;
    private int columnCount;

    public Matrix(int rowCount, int columnCount) {
        if (this.rowCount < 0 || columnCount < 0) {
            this.rowCount = -1;
            this.columnCount = -1;
            this.matrix = null;
            return;
        }
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        matrix = new int[this.rowCount][this.columnCount];
    }

    public Matrix(final int[][] matrix) {
        if (!isCorrectMatrix(matrix)) {
            this.rowCount = -1;
            this.columnCount = -1;
            this.matrix = null;
            return;
        }
        this.rowCount = matrix.length;
        this.columnCount = matrix[0].length;
        this.matrix = new int[this.rowCount][this.columnCount];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    public int rowCount() {
        return rowCount;
    }

    public int columnCount() {
        return columnCount;
    }

    public int item(final int row, final int column) {
        if (!isValidIndex(row, column)) {
            return Integer.MIN_VALUE;
        }
        return matrix[row][column];
    }

    public void setItem(final int row, final int column, final int value) {
        if (!isValidIndex(row, column)) {
            return;
        }
        matrix[row][column] = value;
    }

    public Matrix doAddition(final Matrix matrix) {
        if (matrix == null || !isCorrectMatrix(matrix.matrix) || !isCorrectMatrix(this.matrix) || matrix.rowCount() != rowCount || matrix.columnCount() != columnCount) {
            return null;
        }
        Matrix result = new Matrix(rowCount, columnCount);
        for (int row = 0; row < this.rowCount; row++) {
            for (int column = 0; column < this.columnCount; column++) {
                result.setItem(row, column, matrix.item(row, column) + this.matrix[row][column]);
            }
        }
        return result;
    }

    public Matrix multiplication(final Matrix matrix) {
        if (matrix == null || !isCorrectMatrix(matrix.matrix) || !isCorrectMatrix(this.matrix) || this.columnCount != matrix.rowCount() ) {
            return null;
        }
        Matrix result = new Matrix(rowCount, columnCount);
        for (int row = 0; row < this.rowCount; row++) {
            for (int column = 0; column < this.columnCount; column++) {
                for (int r = 0; r < this.columnCount; r++) {
                    result.setItem(row, column, result.item(row, column) + matrix.item(r, column) * this.matrix[row][r]);
                }
            }
        }
        return result;
    }

    public int determinant() {
        if (!isCorrectMatrix(matrix) || !isSquare(matrix)) {
            return 0;
        }
        return minor(matrix);
    }

    public boolean equal(Matrix matrix) {
        if(rowCount == 0 && matrix.rowCount() == 0) {
            return true;
        }
        if (this.rowCount == matrix.rowCount() && this.columnCount == matrix.columnCount()) {
            for (int row = 0; row < this.rowCount; row++) {
                for (int column = 0; column < this.columnCount; column++) {
                    if (this.matrix[row][column] != matrix.item(row, column)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Рекурсивный метод для поиска детерминанта матрицы, путем разложения по строке с использованием дополнительных миноров
     * @param matrix - подматрица, которая раскладывается на данном шаге
     * @return
     */
    private int minor(int [][] matrix) {
        int result = 0;
        if (matrix.length == 1) {
            return matrix[0][0];
        }
        for (int foldingColumn = 0; foldingColumn < matrix[0].length; foldingColumn++) {
            int[][] smallerMatrix = new int[matrix.length - 1][matrix[0].length - 1];
            for (int row = 1; row < matrix.length; row++) {
                for (int column = 0; column < matrix[0].length; column++) {
                    if (column < foldingColumn) {
                        smallerMatrix[row - 1][column] = matrix[row][column];
                    } else if (column > foldingColumn) {
                        smallerMatrix[row - 1][column - 1] = matrix[row][column];
                    }
                }
            }
            result += Math.pow(-1, foldingColumn % 2) * matrix[0][foldingColumn] * (minor(smallerMatrix));
        }
        return result;
    }

    private boolean isCorrectMatrix(final int[][] matrix) {
        return (matrix != null && !includeEmptyRow(matrix) && isRectangular(matrix));
    }

    private boolean includeEmptyRow(final int[][] matrix) {
        if (matrix.length == 0) {
            return true;
        }
        for (int[] row : matrix) {
            if (row.length == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isRectangular(final int[][] matrix) {
        for (int i = 0; i < matrix.length - 1; i++) {
            if (matrix[i].length == 0 || matrix[i].length != matrix[i + 1].length) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidIndex(final int row, final int column) {
        return (row < this.rowCount && row >= 0 && column < this.columnCount && column >= 0);
    }

    private boolean isSquare(final int[][] matrix) {
        return (matrix.length == matrix[0].length);
    }
}
