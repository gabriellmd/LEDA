package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	@Override
	public void insertFirst(T element) {
		
		DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, null, null);
		DoubleLinkedListNode<T> apontador = this.last;
		
		if(element != null){

				if(isEmpty())
					this.head = newNode;
				else{
						while(apontador.previous !=  null){
							apontador = ((DoubleLinkedListNode<T>)apontador).previous;
						}
						newNode.next = apontador;
						apontador.previous = newNode;
						this.head = newNode;
				}
		}
	}

	@Override
	public void removeFirst() {
		
		DoubleLinkedListNode<T> apontador = this.last;
		DoubleLinkedListNode<T> apontadorAnterior = null;
		
		if(!isEmpty()){
			while(apontador.previous !=  null){
				apontadorAnterior = (DoubleLinkedListNode<T>) apontador;
				apontador = (DoubleLinkedListNode<T>) apontador.previous;
			}
			if(this.head == this.last){
				this.last.previous = null;
				this.head.next = null;
				this.head = new DoubleLinkedListNode<>();
			}
			else{
				apontadorAnterior.setPrevious(null);
				this.head = apontadorAnterior;
			}
		}
		
	}

	@Override
	public void removeLast() {
		
		if(!isEmpty()){
			if(this.head == this.last){
				this.head.next = null;
				this.last.previous = null;
				this.head = new DoubleLinkedListNode<>();
			}
			else{
				this.last.getPrevious().setNext(null);
				this.last = this.last.getPrevious();
			}
		}
		
	}
	
	@Override
	public void insert(T element){
		
		DoubleLinkedListNode<T> node = new DoubleLinkedListNode<T>(element, null, last);
		
		if(isEmpty() && element != null){
			this.head = node;
			this.last = node;
		}
		else if(!isEmpty() && element != null){
			last.setNext(node);
			last = node;
		}
		
	}
	
	@Override
	public void remove(T element){
		
		
		if(!isEmpty() && element != null){
			
			if(head.getData().equals(element) && head.next == null)
				this.head = new SingleLinkedListNode<>();
			else if(head.getData().equals(element)){
				this.head = head.next;
				((DoubleLinkedListNode<T>)head).previous = null;
			}
			else{
				
				DoubleLinkedListNode<T> apontador = (DoubleLinkedListNode<T>) this.head;
				
				while(apontador != null && !apontador.getData().equals(element)){
					apontador = (DoubleLinkedListNode<T>) apontador.getNext();
				}
				if(apontador != null){
					if(apontador.equals(last)){
						last = apontador.previous;
						last.next = null;
					}
					else{
					((DoubleLinkedListNode<T>) apontador.next).previous = apontador.previous;
					apontador.previous.next = apontador.next; 
					}
				}
			}
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
