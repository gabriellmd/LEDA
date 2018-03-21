package adt.bst.extended;

import java.util.ArrayList;
import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em
 * suas funcionalidades e possui um metodo de ordenar um array dado como
 * parametro, retornando o resultado do percurso desejado que produz o array
 * ordenado.
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;

	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public T[] sort(T[] array) {

		while (!this.isEmpty()) {
			this.remove(root.getData());
		}

		for (T element : array) {
			this.insert(element);
		}

		return this.order();
	}

	@Override
	public T[] reverseOrder() {

		T[] arr = (T[]) new Comparable[this.size()];
		ArrayList<T> aux = new ArrayList<>();

		if (!this.isEmpty()) {
			reverseOrderRec(root, aux);

			for (int index = 0; index < aux.size(); index++)
				arr[index] = aux.get(index);
		}

		return arr;

	}

	private void reverseOrderRec(BSTNode<T> node, ArrayList<T> array) {

		if (!node.isEmpty()) {
			reverseOrderRec((BSTNode<T>) node.getRight(), array);
			array.add(node.getData());
			reverseOrderRec((BSTNode<T>) node.getLeft(), array);
		}
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
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

		else if (getComparator().compare(element, node.getData()) == 0)
			saida = node;
		else if (getComparator().compare(element, node.getData()) < 0)
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
			node.setLeft(new BSTNode.Builder().build());
			node.setRight(new BSTNode.Builder().build());

			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else {
			if (getComparator().compare(element, node.getData()) < 0)
				insertR(element, (BSTNode<T>) node.getLeft());

			else if (getComparator().compare(element, node.getData()) > 0)
				insertR(element, (BSTNode<T>) node.getRight());
		}
	}

	private BSTNode<T> newNode(T element, BSTNode<T> pai) {

		BSTNode<T> newNode = new BSTNode.Builder().data(element).parent(pai).left(new BSTNode.Builder().build())
				.right(new BSTNode.Builder().build()).build();

		newNode.getLeft().setParent(newNode);
		newNode.getRight().setParent(newNode);

		return newNode;
	}

	@Override
	public BSTNode<T> sucessor(T element) {

		BSTNode<T> node = this.search(element);
		if (!node.isEmpty()) {
			if (!node.getRight().isEmpty())
				return minimumRec((BSTNode<T>) node.getRight());
			else {
				BSTNode<T> parent = (BSTNode<T>) node.getParent();

				while (parent != null && getComparator().compare(parent.getData(), node.getData()) < 0) {
					node = parent;
					parent = (BSTNode<T>) node.getParent();
				}

				return parent;
			}
		}
		return null;
	}

	private BSTNode<T> minimumRec(BSTNode<T> node) {

		if (!node.getLeft().isEmpty()) {
			return minimumRec((BSTNode<T>) node.getLeft());
		} else
			return node;
	}

	@Override
	public BSTNode<T> predecessor(T element) {

		BSTNode<T> node = this.search(element);
		if (!node.isEmpty()) {
			if (!node.getLeft().isEmpty())
				return maximumRec((BSTNode<T>) node.getLeft());
			else {
				BSTNode<T> parent = (BSTNode<T>) node.getParent();

				while (parent != null && getComparator().compare(parent.getData(), node.getData()) > 0) {
					node = parent;
					parent = (BSTNode<T>) node.getParent();
				}

				return parent;
			}
		}
		return null;

	}

	private BSTNode<T> maximumRec(BSTNode<T> node) {

		if (!node.getRight().isEmpty()) {
			return maximumRec((BSTNode<T>) node.getRight());
		} else
			return node;
	}

}
