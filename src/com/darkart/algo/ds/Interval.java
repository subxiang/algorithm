package com.darkart.algo.ds;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a closed mathematical interval [a, b]. It could be unbounded. If a = null, it means its left is unbounded.
 * If b = null, it means its right is unbounded.
 *
 * @param <C> a comparable type, e.g. Integer, Date, etc.
 */
@SuppressWarnings("serial")
public final class Interval<C extends Comparable<C>> implements Serializable, Comparable<Interval<C>> {
	private C from;
	private C to;

	/**
	 * construct an interval, starting from from and end at to.
	 * @param from the start of the interval, inclusive
	 * @param to   the end of the interval, inclusive
	 */
	public Interval(C from, C to) {
		this.from = from;
		this.to = to;
	}

	/**
	 * get the start of the interval
	 * @return the start of the interval
	 */
	public C getFrom() {
		return from;
	}

	/**
	 * get the end of the interval
	 * @return the end of the interval
	 */
	public C getTo() {
		return to;
	}

	/**
	 * test if the current interval ends before the start of the passed-in one.
	 * @param interval an interval
	 * @return true if the current interval ends before the start of the passed-in one, false otherwise
	 */
	public boolean completelyBefore(Interval<C> interval) {
		return to != null && compare(to, interval.from) < 0;
	}
	
	/**
	 * test if the current interval begins after end of the passed-in one
	 * @param interval an interval
	 * @return true if the current interval starts after the end of the passed-in one, false otherwise
	 */
	public boolean completelyAfter(Interval<C> interval) {
		return interval.to != null && compare(from, interval.to) > 0;
	}

	/**
	 * test if current interval overlaps with the passed-in one
	 * @param interval an interval
	 * @return true if there is any overlap, false otherwise
	 */
	public boolean overlaps(Interval<C> interval) {
		return !(completelyBefore(interval) || completelyAfter(interval));
	}
	
	/**
	 * test if the interval represents a single element, i.e., [a, a]
	 * @return true if this interval's start equals end, false otherwise
	 */
	public boolean isTrivial() {
		return from != null && compare(from, to) == 0;
	}

	/**
	 * create a union of the current interval and the passed-in one. Overlapping intervals will be fused.
	 * @param interval an interval
	 * @return a new interval that includes both intervals if they overlaps, or a list of intervals if they do not overlap.
	 */
	public List<Interval<C>> union(Interval<C> interval) {
		ArrayList<Interval<C>> list = new ArrayList<Interval<C>>();
		if (overlaps(interval)) {
			list.add(new Interval<C>(minFrom(from, interval.from), maxTo(to, interval.to)));
		} else {
			list.add(this);
			list.add(interval);
		}
		return list;
	}

	/**
	 * combine is an operation to create an interval that is large enough to cover both intervals.
	 * @param interval an interval
	 * @return an interval that is large enough to cover both intervals.
	 */
	public Interval<C> combine(Interval<C> interval) {
		return interval == null ? this : new Interval<C>(minFrom(from, interval.from), maxTo(to, interval.to));
	}

	/**
	 * subtracting an interval from another interval has 3 results:
	 * 1. if they do not overlap, or overlaps only at the start point or end point, then the target interval remain unchanged.
	 * 2. if they overlap, but the interval is not fully contained inside the target interval, then the target interval is partially
	 * 	  reduced from either side
	 * 3. if the interval is contained side the target interval, then the target interval is broken into two pieces
	 * @param interval an interval
	 * @return a list of either 1 or 2 intervals
	 */
	public List<Interval<C>> subtract(Interval<C> interval) {
		ArrayList<Interval<C>> list = new ArrayList<Interval<C>>();
		Interval<C> i = intersect(interval);
		if (i != null && !i.isTrivial()) {
			if (compare(from, i.from) < 0) {
				list.add(new Interval<C>(from, i.from));
			}
			if (i.to != null && (to == null || compare(to, i.to) > 0)) {
				list.add(new Interval<C>(i.to, to));
			}
		} else {
			list.add(this);
		}
		return list;
	}

	/**
	 * return the intersection of two intervals
	 * @param interval an interval
	 * @return an interval that belongs to both source intervals.
	 */
	public Interval<C> intersect(Interval<C> interval) {
		if (from != null && to != null) {
			if (compare(interval.from, from) <= 0) {
				if (interval.to == null || compare(interval.to, to) > 0) {
					return this;
				} else if (compare(interval.to, from) >= 0) {
					return new Interval<C>(from, interval.to);
				}
			} else if (compare(interval.from, to) <= 0) {
				if (interval.to != null && compare(interval.to, to) <= 0) {
					return interval;
				} else {
					return new Interval<C>(interval.from, to);
				}
			}
		} else if (from == null && to != null) {
			if (compare(interval.from, to) <= 0) {
				if (interval.to != null && compare(interval.to, to) <= 0) {
					return interval;
				} else {
					return new Interval<C>(interval.from, to);
				}
			}
		} else if (from != null && to == null) {
			if (interval.to == null || compare(interval.to, from) >= 0) {
				if (compare(interval.from, from) <= 0) {
					return new Interval<C>(from, interval.to);
				} else {
					return interval;
				}
			}
		} else {
			return interval;
		}
		
		return null;
	}

	@Override
	public int compareTo(Interval<C> interval) {
		return compare(from, interval.from);
	}

	@Override
	public String toString() {
		return "[" + from + " , " + to + "]";
	}

	private C minFrom(C c1, C c2) {
		return compare(c1, c2) <= 0 ? c1 : c2;
	}

	private C maxTo(C c1, C c2) {
		if (c1 == null) {
			return c1;
		} else if (c2 == null) {
			return c2;
		} else {
			return compare(c1, c2) <= 0 ? c2 : c1;
		}
	}

	private int compare(C c1, C c2) {
		if (c1 != null && c2 != null) {
			return c1.compareTo(c2);
		} else if (c1 == null && c2 != null) {
			return -1;
		} else if (c1 != null && c2 == null) {
			return 1;
		} else {
			return 0;
		}
	}
}
