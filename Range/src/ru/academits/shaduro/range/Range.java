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

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }


    public Range[] getIntersection(Range range) {
        if (range.getFrom() < to && range.getTo() > from) {
            return new Range[]{new Range(Math.max(from, range.getFrom()), Math.min(to, range.getTo()))};
        }

        return null;
    }

    public Range[] getUnion(Range range) {
        if (range.getFrom() <= to && range.getTo() >= from) {
            return new Range[]{new Range(Math.min(range.getFrom(), from), Math.max(to, range.getTo()))};
        }

        return new Range[]{new Range(from, to), new Range(range.getFrom(), range.getTo())};
    }

    public Range[] getDifference(Range range) {
        if ((range.to <= from) || (range.from >= to) || ((range.getFrom() >= from) && (range.getTo() <= to))) {
            return new Range[0];
        }

        if (range.getFrom() < from && range.getTo() > to) {
            return new Range[]{new Range(range.getFrom(), from), new Range(to, range.getTo())};
        }

        if (range.getFrom() < from) {
            return new Range[]{new Range(range.getFrom(), from)};
        }

        return new Range[]{new Range(to, range.getTo())};
    }

    @Override
    public String toString() {
        return "(" + from + "; " + to + ')';
    }
}