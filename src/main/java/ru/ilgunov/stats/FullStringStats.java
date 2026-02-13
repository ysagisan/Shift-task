package ru.ilgunov.stats;

public class FullStringStats implements Stats {
    private long cnt = 0;
    private int minLen = Integer.MAX_VALUE;
    private int maxLen = Integer.MIN_VALUE;

    @Override
    public void accept(String s) {
        int len = s.length();
        minLen = Math.min(len, minLen);
        maxLen = Math.max(len, maxLen);
        cnt++;
    }

    @Override
    public void print() {
        System.out.println("Count: " + cnt);
        System.out.println("Min len: " + minLen);
        System.out.println("Max len: " + maxLen);
    }
}
