package ru.ilgunov.detector;

import java.math.BigDecimal;
import java.math.BigInteger;

public class DataTypeDetector {

    public DataType detectType(String line) {
        try {
            new BigInteger(line);
            return DataType.INTEGER;
        } catch (Exception ignored) {}

        try {
            new BigDecimal(line);
            return DataType.FLOAT;
        } catch (Exception ignored) {}

        return DataType.STRING;
    }
}
