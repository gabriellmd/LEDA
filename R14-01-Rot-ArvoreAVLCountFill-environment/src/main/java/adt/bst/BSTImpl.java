package adt.bst;

import java.util.ArrayList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {

		return heightRec(root, -1);
	}

	protected int heightRec(BSTNode<T> node, int alturaAtual) {

		if (!node.isEmpty()) {
			int left = heightRec((BSTNode<T>) node.getLeft(), alturaAtual + 1);
			int right = heightRec((BSTNode<T>) node.getRight(), alturaAtual + 1);

			alturaAtual = Math.max(left, right);
		}
		return alturaAtual;
	}

	@Override
	public BSTNode<T> search(T element) {

		if (this.isEmpty())
			return new BSTNode.Builder().build();

		return this.search(element, this.root);
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {

		BSTNode<T> saida;

		if (node.isEmpty())
			saida = new BSTNode.Builder().build();

		else if (element.compareTo(node.getData()) == 0)
			saida = node;
		else if (element.compareTo(node.getData()) < 0)
			saida = search(element, (BSTNode<T>) node.getLeft());
		else
			saida = search(element, (BSTNode<T>) node.getRight());

		return saida;

	}

	@Override
	public void insert(T element) {

		if (this.isEmpty())
			root = newNode(element, null);

		else
			insertR(element, root);

	}

	private void insertR(T element, BSTNode<T> node) {

		if (node.isEmpty()) {
			
			node.setData(element);
			node.setLeft(this.newNode(null, node));
			node.setRight(this.newNode(null, node));
			
		} else {
			if (element.compareTo(node.getData()) < 0)
				insertR(element, (BSTNode<T>) node.getLeft());

			else if (element.compareTo(node.getData()) > 0)
				insertR(element, (BSTNode<T>) node.getRight());
		}
	}

	protected BSTNode<T> newNode(T element, BSTNode<T> pai) {

		BSTNode<T> newNode = new BSTNode.Builder().data(element).parent(pai).build();
		
		//Se element = null entao o no serah um NIL, caso nao, preciso gerar os 2 filhos sendo NIL
		if(element != null) {
			
			newNode.setLeft(new BSTNode.Builder().data(null).parent(newNode).build());
			newNode.setRight(new BSTNode.Builder().data(null).parent(newNode).build());
			
			newNode.getLeft().setParent(newNode);
			newNode.getRight().setParent(newNode);
		}

		return newNode;
	}

	@Override
	public BSTNode<T> maximum() {

		if (this.isEmpty())
			return null;
		else
			return maximumRec(root);
	}

	private BSTNode<T> maximumRec(BSTNode<T> node) {

		if (!node.getRight().isEmpty()) {
			return maximumRec((BSTNode<T>) node.getRight());
		} else
			return node;
	}

	@Override
	public BSTNode<T> minimum() {

		BSTNode<T> min;

		if (this.isEmpty())
			return null;
		else
			return minimumRec(root);
	}

	private BSTNode<T> minimumRec(BSTNode<T> node) {

		if (!node.getLeft().isEmpty()) {
			return minimumRec((BSTNode<T>) node.getLeft());
		} else
			return node;
	}

	@Override
	public BSTNode<T> sucessor(T element) {

		BSTNode<T> node = this.search(element);
		if (!node.isEmpty()) {
			if (!node.getRight().isEmpty())
				return minimumRec((BSTNode<T>) node.getRight());
			else {
				BSTNode<T> parent = (BSTNode<T>) node.getParent();

				while (parent != null && parent.getData().compareTo(node.getData()) < 0) {
					node = parent;
					parent = (BSTNode<T>) node.getParent();
				}

				return parent;
			}
		}
		return null;
	}

	@Override
	public BSTNode<T> predecessor(T element) {

		BSTNode<T> node = this.search(element);
		if (!node.isEmpty()) {
			if (!node.getLeft().isEmpty())
				return maximumRec((BSTNode<T>) node.getLeft());
			else {
				BSTNode<T> parent = (BSTNode<T>) node.getParent();

				while (parent != null && parent.getData().compareTo(node.getData()) > 0) {
					node = parent;
					parent = (BSTNode<T>) node.getParent();
				}

				return parent;
			}
		}
		return null;

	}

	@Override
	public void remove(T element) {

		BSTNode<T> node = search(element);

		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
			} else if (hasOnlyOneChild(node)) {
				if (node.getParent() != null) {
					if (!node.getParent().getLeft().equals(node)) {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					} else {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					}
				} else {
					if (node.getLeft().isEmpty()) {
						root = (BSTNode<T>) node.getRight();
					} else {
						root = (BSTNode<T>) node.getLeft();
					}
					root.setParent(null);
				}

			} else {
				T sucessor = sucessor(node.getData()).getData();
				remove(sucessor);
				node.setData(sucessor);
			}
		}
	}

	protected boolean hasOnlyOneChild(BSTNode<T> node) {

		return (node.getLeft().isEmpty() && !node.getRight().isEmpty())
				|| (!node.getLeft().isEmpty() && node.getRight().isEmpty());
	}

	@Override
	public T[] preOrder() {

		T[] arr = (T[]) new Comparable[this.size()];
		ArrayList<T> aux = new ArrayList<>();

		if (!this.isEmpty()) {
			preOrderRec(root, aux);

			for (int index = 0; index < aux.size(); index++)
				arr[index] = aux.get(index);
		}

		return arr;
	}

	private void preOrderRec(BSTNode<T> node, ArrayList<T> array) {

		if (!node.isEmpty()) {
			array.add(node.getData());
			preOrderRec((BSTNode<T>) node.getLeft(), array);
			preOrderRec((BSTNode<T>) node.getRight(), array);
		}
	}

	@Override
	public T[] order() {

		T[] arr = (T[]) new Comparable[this.size()];
		ArrayList<T> aux = new ArrayList<>();

		if (!this.isEmpty()) {
			OrderRec(root, aux);

			for (int index = 0; index < aux.size(); index++)
				arr[index] = aux.get(index);
		}
		return arr;
	}

	private void OrderRec(BSTNode<T> node, ArrayList<T> array) {

		if (!node.isEmpty()) {
			OrderRec((BSTNode<T>) node.getLeft(), array);
			array.add(node.getData());
			OrderRec((BSTNode<T>) node.getRight(), array);
		}
	}

	@Override
	public T[] postOrder() {

		T[] arr = (T[]) new Comparable[this.size()];
		ArrayList<T> aux = new ArrayList<>();

		if (!this.isEmpty()) {
			postOrderRec(root, aux);

			for (int index = 0; index < aux.size(); index++)
				arr[index] = aux.get(index);
		}

		return arr;
	}

	private void postOrderRec(BSTNode<T> node, ArrayList<T> array) {

		if (!node.isEmpty()) {
			postOrderRec((BSTNode<T>) node.getLeft(), array);
			postOrderRec((BSTNode<T>) node.getRight(), array);
			array.add(node.getData());
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}
}