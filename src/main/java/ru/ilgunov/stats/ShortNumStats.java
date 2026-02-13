package ru.ilgunov.stats;

public class ShortNumStats extends AbstractStats {
    private final String label;

    public ShortNumStats(String label) {
        this.label = label;
    }

    @Override
    public void print() {
        System.out.println(label + " count " + cnt);
    }
}
