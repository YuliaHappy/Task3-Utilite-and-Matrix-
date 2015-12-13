package com.epam.javauniversity.utils.array;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class ArrayUtilsTest {

    @Test
    public void changeDimensionTestOnZeroElement() throws Exception {
        Assert.assertThat(ArrayUtils.changeDimension(new int[]{1, 2}, 0), is(new int[]{1, 2}));
    }

    @Test
    public void changeDimensionTestExpansion() throws Exception {
        Assert.assertThat(ArrayUtils.changeDimension(new int[]{1, 2}, 3), is(new int[]{1, 2, 0, 0, 0}));
    }

    @Test
    public void changeDimensionTestTruncationOnLessLengthArray() throws Exception {
        Assert.assertThat(ArrayUtils.changeDimension(new int[]{1, 2}, -1), is(new int[]{1}));
    }

    @Test
    public void changeDimensionTestTruncationOnMoreLengthArray() throws Exception {
        Assert.assertThat(ArrayUtils.changeDimension(new int[]{1, 2}, -3), is(new int[]{}));
    }

    @Test
    public void changeDimensionTestExpansionEmptyArray() throws Exception {
        Assert.assertThat(ArrayUtils.changeDimension(new int[]{}, 2), is(new int[]{0, 0}));
    }

    @Test
    public void changeDimensionTestTruncationEmptyArray() throws Exception {
        Assert.assertThat(ArrayUtils.changeDimension(new int[]{}, -2), is(new int[]{}));
    }

    @Test
    public void changeDimensionTestArrayIsNull() throws Exception {
        Assert.assertNull(ArrayUtils.changeDimension(null, 2));
    }

    @Test
    public void compareWithoutOrderTestOneArrayIsNull() throws Exception {
        Assert.assertFalse(ArrayUtils.compareWithoutOrder(null, new int[]{}));
    }

    @Test
    public void compareWithoutOrderTestArraysIsNull() throws Exception {
        Assert.assertTrue(ArrayUtils.compareWithoutOrder(null, null));
    }

    @Test
    public void compareWithoutOrderTestNotEqualLength() throws Exception {
        Assert.assertFalse(ArrayUtils.compareWithoutOrder(new int[]{1, 2}, new int[]{1, 2, 3}));
    }

    @Test
    public void compareWithoutOrderTestEqualWithOrder() throws Exception {
        Assert.assertTrue(ArrayUtils.compareWithoutOrder(new int[] {1, 2}, new int[]{1, 2}));
    }

    @Test
    public void compareWithoutOrderTestEqualWithoutOrder() throws Exception {
        Assert.assertTrue(ArrayUtils.compareWithoutOrder(new int[] {1, 2, 3}, new int[]{3, 2, 1}));
    }

    @Test
    public void compareWithoutOrderTestNotEqualWithoutOrder() throws Exception {
        Assert.assertFalse(ArrayUtils.compareWithoutOrder(new int[] {1, 2, 3}, new int[]{3, 2, 4}));
    }

    @Test
    public void shuffleTestArrayIsNull() throws Exception {
        Assert.assertNull(ArrayUtils.shuffle(null));
    }

    @Test
    public void shuffleTestEqualArray() throws Exception {
        Assert.assertThat(ArrayUtils.shuffle(new int[]{}), is(new int[]{}));
    }

    @Test
    public void shuffleTest() throws Exception {
        Assert.assertTrue(ArrayUtils.compareWithoutOrder(ArrayUtils.shuffle(new int[]{2, 1, 3}), new int[]{1, 2, 3}));
    }

    @Test
    public void printTestArrayIsNull() throws Exception {
        Assert.assertTrue(ArrayUtils.print(null).equals("{}"));
    }

    @Test
    public void printTestEmptyArray() throws Exception {
        Assert.assertTrue(ArrayUtils.print(new int[]{}).equals("{}"));
    }

    @Test
    public void printTestOrdinary() throws Exception {
        Assert.assertTrue(ArrayUtils.print(new int[]{1, 2, 3}).equals("{1(0) -- 2(1) -- 3(2)}"));
    }
}