package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		
		if(isFull())
			throw new StackOverflowException();
		else{
			top.insert(element);
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		
		if(top.isEmpty())
			throw new StackUnderflowException();
		else{
			T element = top.toArray()[top.size() - 1];			
			top.removeLast();
			return element;
		}
		
	}

	@Override
	public T top() {
		
		T retorno = null;
		if(!top.isEmpty())
			retorno = top.toArray()[top.size() - 1];
			
		return retorno;
	}

	@Override
	public boolean isEmpty() {
		
		return top.isEmpty();
	}

	@Override
	public boolean isFull() {
		
		return top.size() == this.size;
	}

}
