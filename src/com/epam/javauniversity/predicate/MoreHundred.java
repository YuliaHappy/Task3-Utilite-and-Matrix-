package com.epam.javauniversity.predicate;

import com.epam.javauniversity.utils.array.ArrayUtils;

/**
 * Больше 100
 */
public class MoreHundred implements Predicate {
    @Override
    public int[] perform(final int[] array) {
        int[] result = new int[0];
        for (int item : array) {
            if (item > 100) {
                result = ArrayUtils.changeDimension(result, 1);
                result[result.length - 1] = item;
            }
        }
        return result;
    }
}
