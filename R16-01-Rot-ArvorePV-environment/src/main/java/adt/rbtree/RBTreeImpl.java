package adt.rbtree;

import java.util.ArrayList;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		return blackHeightLeft((RBNode<T>) root); // soh preciso percorrer um caminho da raiz ate uma folha.
	}

	// desco sempre para a esquerda contando os NOHS black
	private int blackHeightLeft(RBNode<T> node) {
		int retorno;
		if (node.isEmpty()) {
			retorno = 0;
		} else if (node.getColour().equals(Colour.RED)) {
			retorno = blackHeightLeft((RBNode<T>) node.getLeft());
		} else {
			retorno = 1 + blackHeightLeft((RBNode<T>) node.getLeft());
		}
		return retorno;
	}

	// desco sempre para a direita contando os NOHS black
	private int blackHeightRight(RBNode<T> node) {
		int retorno;
		if (node.isEmpty())
			retorno = 0;
		else if (node.getColour().equals(Colour.RED))
			retorno = blackHeightRight((RBNode<T>) node.getRight());
		else
			retorno = 1 + blackHeightRight((RBNode<T>) node.getRight());

		return retorno;
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed by
	 * the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
																// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must be
	 * BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {

		return verifyChildrenOfRedNodesRec(this.root);
	}

	private boolean verifyChildrenOfRedNodesRec(BSTNode<T> node) {

		// obs: tentei tirar os multiplos returns mas nao achei um jeito correto que
		// parasse a recurssao assim que achasse um falso.
		if (!node.isEmpty()) {
			if (((RBNode<T>) node).getColour().equals(Colour.RED)) {
				if (((RBNode<T>) node.getLeft()).getColour().equals(Colour.RED)
						|| ((RBNode<T>) node.getRight()).getColour().equals(Colour.RED))
					return false;
			}
			return verifyChildrenOfRedNodesRec((BSTNode<T>) node.getLeft())
					&& verifyChildrenOfRedNodesRec((BSTNode<T>) node.getRight());
		}
		return true;
	}

	/**
	 * Verifies the black-height property from the root. The method blackHeight
	 * returns an exception if the black heights are different.
	 */
	private boolean verifyBlackHeight() {

		if (verifyBlackHeightRec((RBNode<T>) root)) {
			return true;
		}
		throw new RuntimeException();
	}

	private boolean verifyBlackHeightRec(RBNode<T> node) {

		boolean retorno;
		if (node.isEmpty()) {
			retorno = true;
		} else if (blackHeightLeft(node) != blackHeightRight(node)) {
			retorno = false;
		} else
			retorno = (verifyBlackHeightRec((RBNode<T>) node.getLeft()))
					&& (verifyBlackHeightRec((RBNode<T>) node.getRight()));

		return retorno;
	}

	@Override
	public void insert(T value) {

		insertR(value, (RBNode<T>) root);
	}

	private void insertR(T element, RBNode<T> node) {

		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new RBNode<T>());
			node.setRight(new RBNode<T>());

			node.getLeft().setParent(node);
			node.getRight().setParent(node);

			node.setColour(Colour.RED);
			this.fixUpCase1(node);
		} else {
			if (element.compareTo(node.getData()) < 0)
				insertR(element, (RBNode<T>) node.getLeft());

			else if (element.compareTo(node.getData()) > 0)
				insertR(element, (RBNode<T>) node.getRight());
		}
	}

	@Override
	public RBNode<T>[] rbPreOrder() {

		RBNode<T>[] preOrder = new RBNode[this.size()];
		ArrayList<RBNode<T>> aux = new ArrayList();

		rbPreOrderRec(aux, (RBNode<T>) this.root);

		for (int i = 0; i < aux.size(); i++)
			preOrder[i] = aux.get(i);

		return preOrder;
	}

	private void rbPreOrderRec(ArrayList<RBNode<T>> arr, RBNode<T> node) {

		if (!node.isEmpty()) {
			arr.add(node);
			rbPreOrderRec(arr, (RBNode<T>) node.getLeft());
			rbPreOrderRec(arr, (RBNode<T>) node.getRight());
		}

	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {

		if (node.equals(this.root))
			node.setColour(Colour.BLACK);
		else
			this.fixUpCase2(node);
	}

	protected void fixUpCase2(RBNode<T> node) {

		if (((RBNode<T>) node.getParent()).getColour().equals(Colour.RED)) {
			this.fixUpCase3(node);
		}

	}

	protected void fixUpCase3(RBNode<T> node) {

		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandFather = (RBNode<T>) parent.getParent();
		RBNode<T> uncle = getUncle(node);

		if (uncle.getColour().equals(Colour.RED)) {
			uncle.setColour(Colour.BLACK);
			parent.setColour(Colour.BLACK);
			grandFather.setColour(Colour.RED);
			this.fixUpCase1(grandFather);
		} else
			this.fixUpCase4(node);
	}

	private boolean isLeftChild(RBNode<T> node) {

		return node.getParent().getLeft().equals(node);
	}

	private void inverteColour(RBNode<T> node) {

		if (node.getColour().equals(Colour.BLACK))
			node.setColour(Colour.RED);
		else
			node.setColour(Colour.RED);
	}

	private RBNode<T> getUncle(RBNode<T> node) {

		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> uncle;
		if (isLeftChild(parent))
			uncle = (RBNode<T>) parent.getParent().getRight();
		else
			uncle = (RBNode<T>) parent.getParent().getLeft();

		return uncle;
	}

	protected void fixUpCase4(RBNode<T> node) {

		RBNode<T> next = node;
		RBNode<T> parent = (RBNode<T>) node.getParent();

		if (!isLeftChild(node) && isLeftChild(parent)) {
			Util.leftRotation(parent);
			next = (RBNode<T>) node.getLeft();
		} else if (isLeftChild(node) && !isLeftChild(parent)) {
			Util.rightRotation(parent);
			next = (RBNode<T>) node.getRight();
		}

		this.fixUpCase5(next);
	}

	protected void fixUpCase5(RBNode<T> node) {

		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> grandFather = (RBNode<T>) parent.getParent();

		parent.setColour(Colour.BLACK);
		grandFather.setColour(Colour.RED);
		if (isLeftChild(node)) {
			Util.rightRotation(grandFather);
		} else
			Util.leftRotation(grandFather);
	}
}
