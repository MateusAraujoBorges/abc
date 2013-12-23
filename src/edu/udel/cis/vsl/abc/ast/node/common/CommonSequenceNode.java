package edu.udel.cis.vsl.abc.ast.node.common;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.udel.cis.vsl.abc.ast.node.IF.ASTNode;
import edu.udel.cis.vsl.abc.ast.node.IF.NodePredicate;
import edu.udel.cis.vsl.abc.ast.node.IF.SequenceNode;
import edu.udel.cis.vsl.abc.token.IF.Source;

public class CommonSequenceNode<T extends ASTNode> extends CommonASTNode
		implements SequenceNode<T> {

	/**
	 * A name you would like to use when printing this node. Else "Sequence"
	 * will be used.
	 */
	private String name;

	@SuppressWarnings("unchecked")
	public CommonSequenceNode(Source source, String name, List<T> childList) {
		super(source, (List<ASTNode>) childList);
		this.name = name;
	}

	@Override
	public void addSequenceChild(T child) {
		addChild(child);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getSequenceChild(int i) {
		return (T) child(i);
	}

	@Override
	public void setSequenceChild(int i, T child) {
		setChild(i, child);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<T> childIterator() {
		return (Iterator<T>) children();
	}

	@Override
	protected void printBody(PrintStream out) {
		out.print(name);
	}

	protected List<T> childListCopy() {
		List<T> childListCopy = new LinkedList<T>();
		Iterator<T> iter = childIterator();

		while (iter.hasNext()) {
			T child = iter.next();

			if (child == null)
				childListCopy.add(null);
			else {
				@SuppressWarnings("unchecked")
				T childCopy = (T) child.copy();

				childListCopy.add(childCopy);
			}
		}
		return childListCopy;
	}

	/**
	 * Copies the list of children, but keeping only those children for which
	 * the corresponding flag in array <code>heap</code> is <code>true</code>.
	 * 
	 * @param keep
	 *            array of length number of children
	 * @return deep copy of children list for the children being kept
	 */
	protected List<T> childListCopy(boolean[] keep) {
		List<T> childListCopy = new LinkedList<T>();
		Iterator<T> iter = childIterator();
		int count = 0;

		while (iter.hasNext()) {
			T child = iter.next();

			if (keep[count]) {
				if (child == null)
					childListCopy.add(null);
				else {
					@SuppressWarnings("unchecked")
					T childCopy = (T) child.copy();

					childListCopy.add(childCopy);
				}
			}
			count++;
		}
		return childListCopy;
	}

	@Override
	public SequenceNode<T> copy() {
		return new CommonSequenceNode<T>(getSource(), name, childListCopy());
	}

	@Override
	public void keepOnly(NodePredicate keep) {
		keepOnlyAndShift(keep);
	}

	@Override
	public NodeKind nodeKind() {
		return NodeKind.SEQUENCE;
	}

}
