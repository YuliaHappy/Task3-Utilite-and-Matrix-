package com.epam.javauniversity.predicate;

/**
 * Кратно 9
 */
public class MultipleOfNine implements Predicate {
    @Override
    public boolean perform(final int item) {
        return (item % 9 == 0);
    }
}
