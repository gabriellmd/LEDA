package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		
		BSTNode aux = (BSTNode) node.getRight();
		aux.setParent(node.getParent());
		node.setParent(aux);
		node.setRight(aux.getLeft());
		aux.setLeft(node);
		
		return (BSTNode<T>) node.getRight();
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		
		BSTNode aux = (BSTNode) node.getLeft();
		aux.setParent(node.getParent());
		node.setParent(aux);
		node.setLeft(aux.getRight());
		aux.setRight(node);

		return (BSTNode<T>) node.getLeft();
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
