package ru.ilgunov.stats;

import java.math.BigDecimal;

public class FullNumStats implements Stats {
    private long cnt = 0;
    private BigDecimal min;
    private BigDecimal max;
    private BigDecimal sum = BigDecimal.ZERO;

    @Override
    public void accept(String s) {
        BigDecimal num = new BigDecimal(s);

        if (cnt == 0) {
            min = max = num;
        } else {
            min = min.min(num);
            max = max.max(num);
        }

        sum = sum.add(num);
        cnt++;
    }

    @Override
    public void print() {
        BigDecimal avg = sum.divide(BigDecimal.valueOf(cnt), BigDecimal.ROUND_HALF_UP);

        System.out.println("Count: " + cnt);
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
        System.out.println("Average: " + avg);
    }
}
