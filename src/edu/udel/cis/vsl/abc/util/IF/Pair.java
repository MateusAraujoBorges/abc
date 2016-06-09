package edu.udel.cis.vsl.abc.util.IF;

/*
 * Enriched this type to make it more useful in collections.
 */
public class Pair<S, T> {

	public S left;

	public T right;

	public Pair(S left, T right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public boolean equals(final Object o) {
		if (o instanceof Pair) {
			@SuppressWarnings("unchecked")
			final Pair<S, T> other = (Pair<S, T>) o;
			return this.left.equals(other.left)
					&& this.right.equals(other.right);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.left.hashCode() + this.right.hashCode();
	}

	@Override
	public String toString() {
		return "<" + this.left + ", " + this.right + ">";
	}

}
