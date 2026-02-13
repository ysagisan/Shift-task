package ru.ilgunov.stats;

import ru.ilgunov.detector.DataType;

public class StatsFactory {
    public static Stats createStats(
            DataType type,
            boolean isShort,
            boolean isFull
    ) {
        if (!isShort && !isFull) {
            return null;
        }

        if (isFull) {
            return switch (type) {
                case INTEGER, FLOAT -> new FullNumStats();
                case STRING -> new FullStringStats();
            };
        }

        return switch (type) {
            case INTEGER -> new ShortNumStats("INTEGER");
            case FLOAT -> new ShortNumStats("FLOAT");
            case STRING ->  new ShortStringStats();
        };
    }
}
