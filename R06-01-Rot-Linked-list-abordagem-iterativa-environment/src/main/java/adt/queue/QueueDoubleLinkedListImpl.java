package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;
import adt.stack.StackDoubleLinkedListImpl;
import adt.stack.StackOverflowException;/////
import adt.stack.StackUnderflowException;

//////////////////////==============================================Fila usando Pilha======================================================/////////////////
// Apagar codigo da interface Queue.java e jUnit Queue.test
public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	//protected DoubleLinkedList<T> list;
	protected StackDoubleLinkedListImpl<T> pilha1;
	protected StackDoubleLinkedListImpl<T> pilha2;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		//this.list = new DoubleLinkedListImpl<T>();
		this.pilha1 = new StackDoubleLinkedListImpl<>(size);
		this.pilha2 = new StackDoubleLinkedListImpl<>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException, StackOverflowException { // throws QueueOverflowException {
		
		/**
		if(isFull())
			throw new QueueOverflowException();
		else{
			list.insert(element);
		}
		**/
		if(isFull())
			throw new QueueOverflowException();
		else{
			pilha1.push(element);
		}
		
	}

	@Override
	public T dequeue() throws QueueUnderflowException, StackUnderflowException, StackOverflowException { ////StackUnderflowException,  StackOverflowException 
		
		/**
		if(isEmpty())
			throw new QueueUnderflowException();
		else{
			T element = list.toArray()[0];
			list.removeFirst();
			return element;
		}**/
		if(isEmpty())
			throw new QueueUnderflowException();
		else{
			while(!pilha1.isEmpty()){
				pilha2.push(pilha1.pop());
			}
			T element = pilha2.pop();
			
			while(!pilha2.isEmpty()){
				pilha1.push(pilha2.pop());
			}
			
			return element;
		}
		
	
	}

	@Override
	public T head() throws StackOverflowException, StackUnderflowException { //StackOverflowException, StackUnderflowException
		
		/**
		T retorno;
		if(isEmpty())
			retorno = null;
		else{
			retorno = list.toArray()[0];
		}
		return retorno;
		**/
		T retorno;
		if(pilha1.isEmpty())
			retorno = null;
		else{
			while(!pilha1.isEmpty()){
				pilha2.push(pilha1.pop());
			}
			retorno = pilha2.top();
			
			while(!pilha2.isEmpty()){
				pilha1.push(pilha2.pop());
			}
		}
		return retorno;
	}

	@Override
	public boolean isEmpty() {
		
		return pilha1.isEmpty();
	}

	@Override
	public boolean isFull() {
		
		//return this.size == list.size();
		return pilha1.isFull();
	}

}
