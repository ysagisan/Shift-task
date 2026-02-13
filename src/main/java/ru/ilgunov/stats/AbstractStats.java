package ru.ilgunov.stats;

public abstract class AbstractStats implements Stats {
    protected long cnt;

    @Override
    public void accept(String s) {
        cnt++;
    }
}
