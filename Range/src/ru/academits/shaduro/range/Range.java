package ru.academits.shaduro.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double x) {
        this.from = x;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double y) {
        this.to = y;
    }

    public double getLength(double from, double to) {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(double from, double to) {
        if (from < this.to && to > this.from) {
            return new Range(Math.max(this.from, from), Math.min(this.to, to));
        }

        return null;
    }

    public Range[] getUnification(double from, double to) {
        if (from <= this.to && to >= this.from) {
            Range range = new Range(Math.min(from, this.from), Math.max(this.to, to));
            return new Range[]{range};
        }

        Range range1 = new Range(this.from, this.to);
        Range range2 = new Range(from, to);
        return new Range[]{range1, range2};
    }

    public Range[] getDifference(double from, double to) {
        if (getIntersection(from, to) == null || from >= this.from && to <= this.to) {
            return null;
        }

        if (from <= this.from) {
            if (to >= this.to) {
                Range range1 = new Range(from, this.from);
                Range range2 = new Range(this.to, to);
                return new Range[]{range1, range2};
            }

            Range range = new Range(from, this.from);
            return new Range[]{range};
        }

        Range range = new Range(this.from, to);
        return new Range[]{range};
    }
}
