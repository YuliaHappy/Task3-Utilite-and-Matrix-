package com.epam.javauniversity.utils.array;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.Arrays.sort;

public final class ArrayUtils {

    private ArrayUtils() {}

    /**
     * Метод, который расширяет/усекает массив на заданное количество элементов.
     * @param array - исходный массив
     * @param length - количество элементов на которое изменится массив
     * @return - расширенный/усечённый массив
     */
    public static int[] changeDimension(final int[] array, final int length) {
        if (isNull(array)) {
            return null;
        }
        int[] copy = array.clone();
        int resultLength = (array.length < -1*length) ? 0 : array.length + length;
        int[] result = new int[resultLength];
        System.arraycopy(copy, 0, result, 0, (array.length < resultLength) ? array.length : resultLength);
        return result;
    }

    public static boolean compareWithoutOrder(final int[] left, final int[] right) {
        if ((isNull(left) && !isNull(right)) || (!isNull(left) && isNull(right))) {
            return false;
        }
        if (isNull(left) && isNull(right)) {
            return true;
        }
        if (left.length != right.length) {
            return false;
        }
        int[] copyLeft = left.clone();
        int[] copyRight = right.clone();
        sort(copyLeft);
        sort(copyRight);
        for (int i = 0; i < copyLeft.length; i++) {
            if (copyLeft[i] != copyRight[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] shuffle(final int[] array) {
        if (isNull(array)) {
            return null;
        }
        int[] copy = array.clone();
        int index, temp;
        Random random = ThreadLocalRandom.current();
        for (int i = copy.length - 1; i > 0; i--) {
            index = random.nextInt(i);
            temp = copy[i];
            copy[i] = copy[index];
            copy[index] = temp;
        }
        return copy;
    }

    public static String print(final int[] array) {
        String result = "{";
        if(!isNull(array)) {
            for (int i = 0; i < array.length; i++) {
                result += array[i] + "(" + i + ")";
                if (i != array.length - 1) {
                    result += " -- ";
                }
            }
        }
        result += "}";
        return result;
    }

    private static boolean isNull(final int[] array) {
        return (array == null);
    }
}
