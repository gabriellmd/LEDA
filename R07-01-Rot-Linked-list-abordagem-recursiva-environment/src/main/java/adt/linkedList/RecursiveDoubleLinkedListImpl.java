package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {

		if(element != null){
			
			if(this.isEmpty()){
				
				this.setData(element);
				this.setNext(null);
				((RecursiveDoubleLinkedListImpl<T>)this).setPrevious(null);
			}
			else{//////
				RecursiveDoubleLinkedListImpl<T> last;
				this.insert(element);
				last = getLast(this);
				deslocaParaInicio(last);
			}
		}

	}

	private RecursiveDoubleLinkedListImpl<T> getLast(RecursiveDoubleLinkedListImpl<T> atual) {
		
			if(atual.getNext() == null){
					return atual;
			}
			else{
				return atual.getLast((RecursiveDoubleLinkedListImpl<T>) atual.getNext());
			}
		
	}

	private void deslocaParaInicio(RecursiveDoubleLinkedListImpl<T> atual) {
		
		atual = (RecursiveDoubleLinkedListImpl<T>) atual;
		
		if(atual.getPrevious() != null){
			T auxiliar = atual.getPrevious().getData();
			atual.getPrevious().setData(atual.getData());
			atual.setData(auxiliar);
			deslocaParaInicio(atual.getPrevious());
		}
		
	}

	@Override
	public void removeFirst() {
		
		if(!isEmpty()){
		
			if(this.getNext() != null){
				this.setData(getNext().getData());
				this.setNext(getNext().getNext());
			}
			else{
				this.setData(null);
				this.setNext(null);
				((RecursiveDoubleLinkedListImpl<T>)this).setPrevious(null);
				}
		}
	}

	@Override
	public void removeLast() {
		
		if(!isEmpty()){
			if(this.getNext() == null){
				this.setData(null);
				if(this.getPrevious() != null){
					this.getPrevious().setNext(null);
				}
				
			}
			else{
				((RecursiveDoubleLinkedListImpl<T>)this.getNext()).removeLast();
			}
			
		}

	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}
	
	@Override
	public void insert(T element) {
		
		if(element != null){
			
			if(this.isEmpty()){
				this.setData(element);
			}
			else if(this.getNext() == null){
					this.setNext(new RecursiveDoubleLinkedListImpl<T>(element, null, this));
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
						if(getNext() != null){
							((RecursiveDoubleLinkedListImpl<T>)getNext()).setPrevious(this);
						}
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

}
