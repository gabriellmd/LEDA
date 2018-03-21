package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	
	@Override
	public void insert(int key, T newValue, int height) {
		
		SkipListNode<T> [] update = new SkipListNode [this.maxHeight];
		SkipListNode<T> node = this.root;
		int index;
		//Pesquisa o local
		for(int i = this.maxHeight; i > 0; i--){ 
			index = i - 1;
			while(node.forward[index] != null && node.forward[index].getKey() < key){
				node = node.forward[index];
			}
			update[index] = node; // Guarda o caminho
		}
		node = node.forward[0];
		if(node.getKey() == key){
			node.setValue(newValue);
		}
		else{
			int v = height;
			node = new SkipListNode<T>(key, v, newValue);
			for(int i = 0; i < v; i++){
				node.forward[i] = update[i].forward[i];
				update[i].forward[i] = node;
			}
		}

	}

	@Override
	public void remove(int key) {
		
		SkipListNode<T> [] update = new SkipListNode [this.maxHeight]; 
		SkipListNode<T> node = this.root;
		int index;
		//Pesquisa o local
		for(int i = this.maxHeight; i > 0; i--){
			index = i - 1;
			while(node.forward[index] != null && node.forward[index].getKey() < key){
				node = node.forward[index];
			}
			update[index] = node; // Guarda o caminho
		}
		node = node.forward[0];
		if(node.getKey() == key){
			//Ajeita os ponteiros
			for(int i = 0; i < this.maxHeight; i++){
				if(update[i].forward[i] != node)
					break;
				else
					update[i].forward[i] = node.forward[i];
			}
		}
	}

	@Override
	public int height() {
		
		int largest = 0;
		if(this.size() != 0) {
			SkipListNode<T> aux = this.root.forward[0];
			while(!aux.equals(NIL)) {
				if(aux.height() > largest) {
					largest = aux.height();
				}
				aux = aux.forward[0];
			}
		}
		return largest;
	}

	@Override
	public SkipListNode<T> search(int key) {
		
		SkipListNode<T> retorno;
		SkipListNode<T> node = this.root;
		int index;
		
		for(int i = this.maxHeight; i > 0; i--){
			index = i - 1;
			while(node.forward[index] != null && node.forward[index].getKey() < key){
				node = node.forward[index];
			}
		}
		node = node.forward[0]; 
		if(node.getKey() == key){
			retorno = node;
		}
		else
			retorno = null;
		
		return retorno;
	}

	@Override
	public int size() {
		
		int size = 0;
		SkipListNode<T> aux = this.root;
		while(aux.forward[0] != NIL) {
			aux = aux.forward[0];
			size++;
		}
		return size;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		
		SkipListNode<T> [] array = new SkipListNode [this.size() + 2];
		SkipListNode<T> aux = this.root;
		int index = 0;
		while(index != this.size() + 2) {
			array[index] = aux;
			aux = aux.forward[0];
			index++;
		}
		
		return array;
	}

}
