 package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		
		return this.head.isNIL();
	}

	@Override
	public int size() {
		
		int count = 0;
		SingleLinkedListNode<T> apontador = this.head;

		if(!isEmpty()){
			while(apontador != null){
				count++;
				apontador = apontador.getNext();
			}
		}
		return count;
	}

	@Override
	public T search(T element) {
		
		T achou = null;
		SingleLinkedListNode<T> apontador = this.head;
		
		
		while(apontador != null && apontador.getData() != element){
			apontador = apontador.getNext();
		}
		if(apontador != null)
			achou = element;
		
		return achou;
	}

	@Override
	public void insert(T element) {
		
		SingleLinkedListNode<T> node = new SingleLinkedListNode<T>(element, null);
		
		if(isEmpty() && element != null){
			this.head = node;
		}
		else if(!isEmpty() && element != null){
			SingleLinkedListNode<T> apontador = this.head;
	
			while(apontador.getNext() != null){
				apontador = apontador.getNext();
			}
			apontador.next = node;
		}
		
	}

	@Override
	public void remove(T element) {
		
		if(!isEmpty()){
			
			if(this.head.getData().equals(element)){
				this.head = this.head.next; 
			}
			else{
				
				SingleLinkedListNode<T> apontador = this.head;
				SingleLinkedListNode<T> previous = null;
				
				while(apontador != null && apontador.getData() != element){
					previous = apontador;
					apontador = apontador.getNext();
				}
				if(apontador != null){
					previous.next = apontador.getNext();
				}
					
			}
		}
	}

	@Override
	public T[] toArray() {
		
		SingleLinkedListNode<T> apontador = this.head;
		T[] array = (T[]) new Comparable[this.size()];//////////////////////////// Cria um array generics
		
		int i = 0;
		if(!isEmpty()){
			while(apontador != null){
				array[i] = apontador.getData();
				apontador = apontador.getNext();
				i++;
			}
		}
		
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
