package adt.bt;

import adt.bst.BSTNode;

public class Util {

	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {

		BSTNode<T> aux = (BSTNode<T>) node.getRight();
		
		node.setRight(aux.getLeft());
		aux.setLeft(node);
		aux.setParent(node.getParent());
		node.setParent(aux);
		
		return aux;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {

		BSTNode<T> aux = (BSTNode<T>) node.getLeft();
		
		node.setLeft(aux.getRight());
		aux.setRight(node);
		aux.setParent(node.getParent());
		node.setParent(aux);
		
		return aux;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
