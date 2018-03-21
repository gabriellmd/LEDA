package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		
		return getData() == null;
	}

	@Override
	public int size() {
		
		if(isEmpty()){
			return 0;
		}
		else if(this.getNext() == null){
			return 1;
		}
		else{
			return 1 + this.getNext().size();
		}
	}

	@Override
	public T search(T element) {
		
		T retorno = null;
		
		if(!isEmpty()){
			if(getData().equals(element)){
				retorno = element;
			}
			else if(this.getNext() != null){
				retorno = getNext().search(element);
			}	
		}

		return retorno;
		
	}

	@Override
	public void insert(T element) {
		
		if(element != null){
			
			if(this.isEmpty()){
				this.setData(element);
			}
			else if(this.getNext() == null){
					this.setNext(new RecursiveSingleLinkedListImpl<T>(element, null));
			}
			else{
				getNext().insert(element);
			}
				
		}

	}

	@Override
	public void remove(T element) {
		
		if(element != null){
			if(!this.isEmpty()){
				
				if(getData().equals(element)){
					if(getNext() != null){
						this.setData(getNext().getData());
						this.setNext(getNext().getNext());
					}
					else{
						this.setData(null);
					}
				}
				else if(getNext() != null){
					getNext().remove(element);	
					}
			}	
		}
	}

	@Override
	public T[] toArray() {
		
		T[] array = (T[]) new Comparable[size()];
		
		return toArrayRecursive(array, 0);
	}

	private T[] toArrayRecursive(T[] array, int cont) {
		
		if( this.getData() != null){
			array[cont] = this.getData();
			
			if(getNext() == null)
				return array;
			else{ 
				return this.getNext().toArrayRecursive(array, cont + 1);
			}
		}
		return array;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
