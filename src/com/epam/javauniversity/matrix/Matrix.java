package com.epam.javauniversity.matrix;

public class Matrix implements Cloneable{

    private int[][] matrix;
    private int row;
    private int column;

    public Matrix(int rowCount, int columnCount) {
        if (row < 0 || column < 0) {
            this.row = -1;
            this.column = -1;
            this.matrix = null;
            return;
        }
        this.row = rowCount;
        this.column = columnCount;
        matrix = new int[this.row][this.column];
    }

    public Matrix(final int[][] matrix) {
        if (!isMatrix(matrix)) {
            this.row = -1;
            this.column = -1;
            this.matrix = null;
            return;
        }
        this.row = matrix.length;
        this.column = matrix[0].length;
        this.matrix = new int[this.row][this.column];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    @Override
    protected Matrix clone() throws CloneNotSupportedException {
        int [][] copyMatrix = new int[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                copyMatrix[row][column] = matrix[row][column];
            }
        }
        return new Matrix(copyMatrix);
    }

    public int rowCount() {
        return row;
    }

    public int columnCount() {
        return column;
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

    public Matrix addition(final Matrix matrix) {
        if (matrix == null || !isMatrix(matrix.matrix) || !isMatrix(this.matrix) || matrix.rowCount() != row || matrix.columnCount() != column) {
            return null;
        }
        Matrix result = new Matrix(row, column);
        for (int row = 0; row < this.row ; row++) {
            for (int column = 0; column < this.column; column++) {
                result.setItem(row, column, matrix.item(row, column) + this.matrix[row][column]);
            }
        }
        return result;
    }

    public Matrix multiplication(final Matrix matrix) {
        if (matrix == null || !isMatrix(matrix.matrix) || !isMatrix(this.matrix) || this.column != matrix.rowCount() ) {
            return null;
        }
        Matrix result = new Matrix(row, column);
        for (int row = 0; row < this.row ; row++) {
            for (int column = 0; column < this.column; column++) {
                for (int r = 0; r < this.column; r++) {
                    result.setItem(row, column, result.item(row, column) + matrix.item(r, column) * this.matrix[row][r]);
                }
            }
        }
        return result;
    }

    public int determinant() {
        if (!isMatrix(matrix) || !isSquare(matrix)) {
            return 0;
        }
        return minor(matrix);
    }

    public boolean isEqually(Matrix matrix) {
        if(row == 0 && matrix.rowCount() == 0) {
            return true;
        }
        if (this.row == matrix.rowCount() && this.column == matrix.columnCount()) {
            for (int row = 0; row < this.row; row++) {
                for (int column = 0; column < this.column; column++) {
                    if (this.matrix[row][column] != matrix.item(row, column)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

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

    private boolean isMatrix(final int[][] matrix) {
        return (matrix != null && !includeEmptyRow(matrix) && !includeNullRow(matrix) && isRectangular(matrix));
    }

    private boolean includeNullRow(final int[][] matrix) {
        for (int[] row : matrix) {
            if (row == null) {
                return true;
            }
        }
        return false;
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
        return (row < this.row && row >= 0 && column < this.column && column >= 0);
    }

    private boolean isSquare(final int[][] matrix) {
        return (matrix.length == matrix[0].length);
    }
}
