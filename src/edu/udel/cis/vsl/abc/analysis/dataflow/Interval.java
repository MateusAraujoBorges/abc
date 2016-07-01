package edu.udel.cis.vsl.abc.analysis.dataflow;


public class Interval {
	private final Long low;
	private final Long high;

	public Interval(Long value) {
		this.low  = value;
		this.high = value;

		assert isValidInterval(): "Not a valid interval";
	}

	public Interval(Long low, Long high) {
		this.low  = low;
		this.high = high;

		assert isValidInterval(): "Not a valid interval";
	}

	public boolean isValidInterval(){
		if ((low == null) != (high == null)) {
			return false;
		}
		if (low != null && low > high)
			return false;

		return true;
	}

	public Long getLow() {
		return low;
	}

	public Long getHigh() {
		return high;
	}

	public Interval union(Interval other) {
		if (isEmpty() || other.isEmpty()) {
			return createEmptyInterval();
		} else if (low >= other.low && high <= other.high) {
			return other;
		} else {
			return new Interval(Math.min(low, other.low), Math.max(high, other.high));
		}
	}

	public Interval intersect(Interval other) {
		if (this.intersects(other)) {
			return new Interval(Math.max(low, other.low), Math.min(high, other.high));
		} else {
			return createEmptyInterval();
		}
	}

	public boolean intersects(Interval other) {
		if (isEmpty() || other.isEmpty()) {
			return false;
		}

		return (low >= other.low && low <= other.high)
				|| (high >= other.low && high <= other.high)
				|| (low <= other.low && high >= other.high);
	}


	public boolean contains(Interval other) {
		return (!isEmpty() && !other.isEmpty()
				&& low <= other.low && other.high <= high);
	}

	public boolean isEmpty() {
		return low == null && high == null;
	}

	private static Interval createEmptyInterval() {
		return new Interval(null,null);
	}

	public Interval plus(Interval i){

		return null;
	}
	public Interval minus(Interval i){

		return null;
	}
	public Interval multiply(Interval i){

		return null;
	}
	public Interval divide(Interval i){

		return null;
	}

	@Override
	public String toString() {
		return "[" + (low == null ? "" : low) + "; " + (high == null ? "" : high) + "]";
	}

}
