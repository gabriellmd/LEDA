package adt.btree;

import java.util.Collections;
import java.util.LinkedList;

public class BNode<T extends Comparable<T>> {
	protected LinkedList<T> elements; //PODERIA TRABALHAR COM ARRAY TAMBEM
	protected LinkedList<BNode<T>> children; //PODERIA TRABALHAR COM ARRAY TAMBEM
	protected BNode<T> parent;
	protected int order;
	
	public BNode(int order){
		this.order = order;
		this.elements = new LinkedList<T>();	
		this.children = new LinkedList<BNode<T>>();
	}
	@Override
	public String toString() {
		return this.elements.toString();
	}

	@Override
	public boolean equals(Object obj) {
		boolean resp = false;
		if(obj != null){
			if(obj instanceof BNode){
				if(this.size() == ((BNode<T>) obj).size()){
					resp = true;
					int i = 0;
					while(i<this.size() && resp){
						resp = resp && this.getElementAt(i).equals(((BNode<T>) obj).getElementAt(i));
						i++;
					}
				}
			}
		}
		return resp;
	}
	public boolean isEmpty(){
		return this.size() == 0;
	}
	public int size(){
		return this.elements.size();
	}
	public boolean isLeaf(){
		return this.children.size() == 0;
	}
	public boolean isFull(){
		return this.size()== order - 1;
	}
	public void addElement(T element){
		this.elements.add(element);
		Collections.sort(elements);
	}
	public void removeElement(T element){
		this.elements.remove(element);
	}
	public void removeElement(int position){
		this.elements.remove(position);
	}
	public void addChild(int position, BNode<T> child){
		this.children.add(position, child);
		child.parent = this;
	}
	public void removeChild(BNode<T> child){
		this.children.remove(child);
	}
	public int indexOfChild(BNode<T> child){
		return this.children.indexOf(child);
	}
	public T getElementAt(int index){
		return this.elements.get(index);
	}
	protected void split(){
		T median = elements.get(elements.size() / 2);
		int position, leftPosition, rightPosition;
		BNode<T> largest = new BNode<>(getMaxChildren());
		BNode<T> smaller = new BNode<>(getMaxChildren());
		LinkedList<BNode<T>> childrens = new LinkedList<BNode<T>>();

		saveElements(median, largest, smaller);

		if (parent == null && isLeaf()) {
			setElements(new LinkedList<T>());
			addElement(median);
			addChild(0, smaller);
			addChild(1, largest);
		}

		else if (parent == null && !isLeaf()) {
			childrens = children;
			setElements(new LinkedList<T>());
			addElement(median);
			setChildren(new LinkedList<BNode<T>>());
			addChild(0, smaller);
			addChild(1, largest);

			reorganizesChildrens(childrens, smaller, 0, smaller.size() + 1);
			reorganizesChildrens(childrens, largest, largest.size() + 1, childrens.size());
		}

		else if (isLeaf()) {
			BNode<T> toPromove = new BNode<>(getMaxChildren());
			toPromove.elements.add(median);
			toPromove.setParent(parent);

			smaller.setParent(parent);
			largest.setParent(parent);

			position = searchPositionInParent(toPromove.getParent().getElements(), median);
			leftPosition = position;
			rightPosition = position + 1;
			parent.getChildren().set(leftPosition, smaller);
			parent.getChildren().add(rightPosition, largest);
			toPromove.promote();
		} else {
			childrens = children;
			BNode<T> toPromove = new BNode<>(getMaxChildren());
			toPromove.elements.add(median);
			toPromove.setParent(parent);
			smaller.setParent(parent);
			largest.setParent(parent);

			position = searchPositionInParent(toPromove.getElements(), median);
			leftPosition = position;
			rightPosition = position + 1;

			parent.getChildren().add(leftPosition, smaller);
			parent.getChildren().add(rightPosition, largest);

		}
	}
	
	// Guarda de acordo com a mediana os maiores e menores elementos
		private void saveElements(T median, BNode<T> largest, BNode<T> smaller) {
			
			for (int i = 0; i < getElements().size(); i++) {
				if (median.compareTo(getElementAt(i)) < 0) {
					largest.addElement(getElementAt(i));
				}
				if (median.compareTo(getElementAt(i)) > 0) {
					smaller.addElement(getElementAt(i));
				}
			}
		}
		
		
	protected void promote(){
		
		int position = searchPositionInParent(parent.getElements(), getElementAt(0));
		parent.getElements().add(position, getElementAt(0));
		if (parent.size() > getMaxKeys()) {
			parent.split();
		}
	}
	
	// Procura a posicao no pai
		private int searchPositionInParent(LinkedList<T> list, T mediana) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).compareTo(mediana) > 0) {
					return i;
				}
			}
			return list.size();
		}

		// Reorganiza os filhos de acordo com as posicoes do pai
		private void reorganizesChildrens(LinkedList<BNode<T>> childrens, BNode<T> parent, int first, int last) {
			int position;
			
			for (int i = first; i < last; i++) {
				position = searchPositionInParent(parent.getElements(), childrens.get(i).elements.get(0));
				parent.addChild(position, childrens.get(i));
				
			}
		}

	
	public LinkedList<T> getElements() {
		return elements;
	}
	public void setElements(LinkedList<T> elements) {
		this.elements = elements;
	}
	public LinkedList<BNode<T>> getChildren() {
		return children;
	}
	
	public void setChildren(LinkedList<BNode<T>> children) {
		this.children = children;
	}
	
	public BNode<T> copy(){
		BNode<T> result = new BNode<T>(order);
		result.parent = parent;
		for (int i = 0; i < this.elements.size(); i++) {
			result.addElement(this.elements.get(i));
		}
		for (int i = 0; i < this.children.size(); i++) {
			result.addChild(i,((BNode<T>)this.children.get(i)).copy());
		}
		
		return result;
	}
	public BNode<T> getParent() {
		return parent;
	}
	public void setParent(BNode<T> parent) {
		this.parent = parent;
	}
	public int getMaxKeys() {
		return order - 1;
	}
	public int getMaxChildren() {
		return order;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}

	
}
