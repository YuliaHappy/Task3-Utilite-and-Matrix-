package com.epam.javauniversity.predicate;

/**
 * Больше 100
 */
public class MoreHundred implements Predicate {
    @Override
    public boolean perform(final int item) {
        return (item > 100);
    }
}
