package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;// Admiti que esse atributo eh um contador de quantos elementos existem no array. Todavia nao existe nenhum metodo que permite seu get.

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		
		if(this.isFull())
			throw new QueueOverflowException();
		
		
		if(element != null){
			if(this.isEmpty()){
				this.head++;
			}
			this.tail = (this.tail + 1) % array.length; 
			array[this.tail] = element;
			this.elements++;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		
		
		if(this.isEmpty())
			throw new QueueUnderflowException();
		
		T element = array[this.head];
		
		if(this.head == this.tail){
			this.head = -1;
			this.tail = -1;
			this.elements = 0;
		}
		else{
		this.head = (this.head + 1) % array.length;
		this.elements--;
		}
		
		return element;
	}

	@Override
	public T head() {
		
		T element = null;
		if(!this.isEmpty())
			element = array[head];
		
		return element;
	}

	@Override
	public boolean isEmpty() {
		
		return (this.head == -1 && this.tail == -1);
	}

	@Override
	public boolean isFull() {
		
		return ((this.tail + 1) % array.length) == this.head;	
	}
	
	public int getHead(){
		return this.head;
	}
	
	public int getTail(){
		return this.tail;
	}
}
