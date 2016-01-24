package com.epam.javauniversity.utils.array;

import com.epam.javauniversity.predicate.MoreHundred;
import com.epam.javauniversity.predicate.MultipleOfNine;
import com.epam.javauniversity.predicate.Parity;
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
        try {
            ArrayUtils.changeDimension(null, 2);
            Assert.assertFalse(true);
        } catch(IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void compareWithoutOrderTestOneArrayIsNull() throws Exception {
        try {
            ArrayUtils.compareWithoutOrder(null, new int[]{});
            Assert.assertFalse(true);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void compareWithoutOrderTestArraysIsNull() throws Exception {
        try {
            ArrayUtils.compareWithoutOrder(null, null);
            Assert.assertFalse(true);
        } catch(IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void compareWithoutOrderTestNotEqualLength() throws Exception {
        try {
            ArrayUtils.compareWithoutOrder(new int[]{1, 2}, new int[]{1, 2, 3});
            Assert.assertFalse(true);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
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
        try {
            ArrayUtils.compareWithoutOrder(new int[]{1, 2, 3}, new int[]{3, 2, 4});
            Assert.assertFalse(true);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void shuffleTestArrayIsNull() throws Exception {
        try {
            ArrayUtils.shuffle(null);
            Assert.assertFalse(true);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
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
        try {
            ArrayUtils.print(new int[]{1, 2, 3}).equals("{1(0) -- 2(1) -- 3(2)}");
            Assert.assertFalse(true);
        } catch(IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void filterTestArrayIsNullAnyPredicate() {
        try {
            ArrayUtils.filter(null, new Parity());
            Assert.assertFalse(true);
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void filterTestPredicateIsParity() {
        Assert.assertTrue(ArrayUtils.compareWithoutOrder(new int[]{2, 4},
                                                ArrayUtils.filter(new int[]{1, 2, 3, 4}, new Parity())));
    }

    @Test
    public void filterTestPredicateIsMoreHundred() {
        Assert.assertTrue(ArrayUtils.compareWithoutOrder(new int[]{102, 104},
                ArrayUtils.filter(new int[]{1, 102, 93, 104}, new MoreHundred())));
    }

    @Test
    public void filterTestPredicateIsMultipleOfNine() {
        Assert.assertTrue(ArrayUtils.compareWithoutOrder(new int[]{9, 99},
                ArrayUtils.filter(new int[]{7, 9, 19, 99}, new MultipleOfNine())));
    }

    @Test
    public void filterTestPredicateIsNull() {
        Assert.assertTrue(ArrayUtils.compareWithoutOrder(new int[]{1, 2, 3, 4},
                                                ArrayUtils.filter(new int[]{1, 2, 3, 4}, null)));
    }

    @Test
    public void filterTestEmptyArrayAnyPredicate() {
        Assert.assertTrue(ArrayUtils.compareWithoutOrder(new int[]{},
                                                ArrayUtils.filter(new int[]{}, new MultipleOfNine())));
    }
}