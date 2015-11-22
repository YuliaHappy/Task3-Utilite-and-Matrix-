package com.epam.javauniversity.matrix;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class MatrixTest {

    Matrix matrix;

    @Before
    public void initialize() {
        matrix = new Matrix(new int[][]{new int[]{1, 2, 3},
                                        new int[]{4, 5, 6}});
    }

    @Test
    public void rowCountTestMatrixIsEmpty() throws Exception {
        Matrix matrixEmpty = new Matrix(0, 0);
        Assert.assertThat(matrixEmpty.rowCount(), is(0));
    }

    @Test
    public void rowCountTestOrdinary() throws Exception {
        Assert.assertThat(matrix.rowCount(), is(2));
    }

    @Test
    public void columnCountTestMatrixIsEmpty() throws Exception {
        Matrix matrixEmpty = new Matrix(0, 0);
        Assert.assertThat(matrixEmpty.columnCount(), is(0));
    }

    @Test
    public void columnCountTestOrdinary() throws Exception {
        Assert.assertThat(matrix.columnCount(), is(3));
    }

    @Test
    public void itemTestOrdinary() throws Exception {
        Assert.assertThat(matrix.item(1, 2), is(6));
    }

    @Test
    public void itemTestMatrixIsEmpty() throws Exception {
        Matrix matrixEmpty = new Matrix(0, 0);
        Assert.assertThat(matrixEmpty.item(1, 1), is(Integer.MIN_VALUE));
    }

    @Test
    public void itemTestIndexNotValid() throws Exception {
        Assert.assertThat(matrix.item(5, 6), is(Integer.MIN_VALUE));
    }

    @Test
    public void setItemTestMatrixIsEmpty() throws Exception {
        Matrix matrixEmpty = new Matrix(0, 0);
        matrixEmpty.setItem(1, 1, -5);
        Assert.assertTrue(matrixEmpty.isEqually(new Matrix(0, 0)));
    }

    @Test
    public void setItemTestOrdinary() throws Exception {
        matrix.setItem(1, 1, -5);
        Assert.assertThat(matrix.item(1, 1), is(-5));
    }

    @Test
    public void setItemTestIndexNotValid() throws Exception {
        Matrix matrixNew = new Matrix(new int[][]{new int[]{1, 2, 3},
                                                  new int[]{4, 5, 6}});
        matrixNew.setItem(5, 1, -5);
        Assert.assertTrue(matrix.isEqually(matrixNew));
    }

    @Test
    public void additionTestAddsMatrixIsNull() throws Exception {
        Matrix addsMatrix = null;
        Assert.assertNull(matrix.addition(addsMatrix));
    }

    @Test
    public void additionTestAddsMatrixEmpty() throws Exception {
        Matrix addsMatrix = new Matrix(0, 0);
        Assert.assertNull(matrix.addition(addsMatrix));
    }

    @Test
    public void additionTestThisMatrixEmpty() throws Exception {
        Matrix emptyMatrix = new Matrix(0, 0);
        Assert.assertNull(emptyMatrix.addition(matrix));
    }

    @Test
    public void additionTestDimensionAddsMatrixNotEqually() throws Exception {
        Matrix addsMatrix = new Matrix(new int[][]{new int[]{1, 2, 3},
                                                   new int[]{4, 5, 6},
                                                   new int[]{7, 8, 9}});
        Assert.assertNull(matrix.addition(addsMatrix));
    }

    @Test
    public void additionTestOrdinary() throws Exception {
        Matrix addsMatrix = new Matrix(new int[][]{new int[]{1, 1, 1},
                                                   new int[]{1, 1, 1}});
        Assert.assertTrue(matrix.addition(addsMatrix).isEqually(new Matrix(new int[][]{
                                                   new int[]{2, 3, 4},
                                                   new int[]{5, 6, 7}})));
    }

    @Test
    public void multiplicationTestMultipliedMatrixIsNull() throws Exception {
        Matrix multipliedMatrix = null;
        Assert.assertNull(matrix.multiplication(multipliedMatrix));
    }

    @Test
    public void multiplicationTestMultipliedMatrixEmpty() throws Exception {
        Matrix multipliedMatrix = new Matrix(0, 0);
        Assert.assertNull(matrix.multiplication(multipliedMatrix));
    }

    @Test
    public void multiplicationTestThisMatrixEmpty() throws Exception {
        Matrix emptyMatrix = new Matrix(0, 0);
        Assert.assertNull(emptyMatrix.multiplication(matrix));
    }

    @Test
    public void multiplicationTestDimensionMultipliedMatrixNotValid() throws Exception {
        Matrix multipliedMatrix = new Matrix(new int[][]{new int[]{1, 2, 3},
                                                         new int[]{4, 5, 6}});
        Assert.assertNull(matrix.multiplication(multipliedMatrix));
    }

    @Test
    public void multiplicationTestOrdinary() throws Exception {
        Matrix multipliedMatrix = new Matrix(new int[][]{new int[]{1, 1, 1},
                                                         new int[]{1, 1, 1},
                                                         new int[]{1, 1, 1}});
        Assert.assertTrue(matrix.multiplication(multipliedMatrix).isEqually(new Matrix(new int[][]{
                                                         new int[]{6, 6, 6},
                                                         new int[]{15, 15, 15}})));
    }

    @Test
    public void determinantTestMatrixEmpty() throws Exception {
        Matrix matrixEmpty = new Matrix(0, 0);
        Assert.assertThat(matrixEmpty.determinant(), is(0));
    }

    @Test
    public void determinantTestMatrixIsNotSquare() throws Exception {
        Assert.assertThat(matrix.determinant(), is(0));
    }

    @Test
    public void determinantTestOrdinary() throws Exception {
        Matrix matrix = new Matrix(new int[][]{new int[]{1, 1, 1},
                                               new int[]{1, 3, 1},
                                               new int[]{1, 1, 2}});
        Assert.assertThat(matrix.determinant(), is(2));
    }

    @Test
    public void isEquallyTestMatrixEmpty() throws Exception {
        Matrix firstMatrix = new Matrix(0, 0);
        Matrix secondMatrix = new Matrix(0, 0);
        Assert.assertTrue(firstMatrix.isEqually(secondMatrix));
    }

    @Test
    public void isEquallyTestDimensionIsNotEqually() throws Exception {
        Matrix otherMatrix = new Matrix(new int[][]{new int[]{1, 2}});
        Assert.assertFalse(matrix.isEqually(otherMatrix));
    }

    @Test
    public void isEquallyTestEquallyMatrix() throws Exception {
        Matrix otherMatrix = new Matrix(new int[][]{new int[]{1, 2, 3},
                                                    new int[]{4, 5, 6}});
        Assert.assertTrue(matrix.isEqually(otherMatrix));
    }

    @Test
    public void isEquallyTestNotEquallyMatrix() throws Exception {
        Matrix otherMatrix = new Matrix(new int[][]{new int[]{1, 2, 3},
                                                    new int[]{3, 5, 6}});
        Assert.assertFalse(matrix.isEqually(otherMatrix));
    }
}