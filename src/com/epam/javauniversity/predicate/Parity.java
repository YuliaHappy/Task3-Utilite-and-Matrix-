package com.epam.javauniversity.predicate;

/**
 * Четность
 */
public class Parity implements Predicate {
    @Override
    public boolean perform(final int item) {
        return (item % 2 == 0);
    }
}
