package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		
		if(element != null){
			if(isFull()){
				throw new HashtableOverflowException();
			}
		
			int hash = this.generateHashWithProbe(element, 1);
			this.table[hash] = element;
			this.elements++;
		}
	}

	@Override
	public void remove(T element) {
		
		if(element != null){
			if(this.search(element) != null){
			
				int hash = this.generateHashWithProbe(element, -1);
				if(this.table[hash] != null)
					this.table[hash] = new DELETED();
				this.elements--;
			}
		}
	}

	@Override
	public T search(T element) {
		
		T retorno = null;
	
		int hash = this.generateHashWithProbe(element, 0);
		if(this.table[hash] != null && this.table[hash].equals(element))
			retorno = (T) this.table[hash];
		
		return retorno;
	}

	@Override
	public int indexOf(T element) {
		
		int retorno = -1;
	
		int hash = this.generateHashWithProbe(element, 0);
		if(this.table[hash] != null && this.table[hash].equals(element))
			retorno = hash;

		return retorno;
	}
	
	
	/**
	 * 
	 * @param element = elemento a ser inserido, removido ou pesquisado.
	 * 
	 * @param colision = Representa em quanto o parametro COLLISION da classe deve ser acrescido caso haja colisão.
	 * Se esse metodo for usado no insert, o parametro colisions deve ser igual a 1.
	 * Se esse metodo for usado no remove, o parametro colisions deve ser igual a -1.
	 * Se esse metodo for usado no search, o parametro colisions deve ser igual a 0. 
	 * 
	 * @return = hash para esse dado elemento.
	 */
	private int generateHashWithProbe(T element, int colisions){
			
			int probe = 0;
			int hash = ((HashFunctionLinearProbing<T>)this.getHashFunction()).hash(element, probe);
			
			while(this.table[hash] != null && !this.table[hash].equals(element) && probe != this.capacity()){
				
				probe++;
				hash = ((HashFunctionLinearProbing<T>)this.getHashFunction()).hash(element, probe);
				this.COLLISIONS += colisions;
			}
			
			return hash;
		}

}
