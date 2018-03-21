package adt.linkedList.batch;

import adt.linkedList.DoubleLinkedListImpl;
import adt.linkedList.DoubleLinkedListNode;
import adt.linkedList.SingleLinkedListNode;
import util.GenericException;

/**
 * Manipula elementos da LinkedList em bloco (batch).
 * 
 * @author campelo
 * @author adalberto
 *
 * @param <T>
 */
public class BatchLinkedListImpl<T> extends DoubleLinkedListImpl<T> implements BatchLinkedList<T> {

	/* 
	 * Nao modifique nem remova este metodo.
	 */
	public BatchLinkedListImpl() {
		head = new DoubleLinkedListNode<T>();
		last = (DoubleLinkedListNode<T>)head;
	}

	/**
	 * Insere todos do array elementos a partir de uma posicao na lista. As posicoes
	 * da lista são consideradas entre 0 (primeiro) e N-1 (ultimo).
	 * 
	 * Ex: lista = [3,9,4,2].
	 * inserirEmBatch(2,[15,28,7,11]) modifica a lista para [3,9,15,28,7,11,4,2].
	 * 
	 * Note que eh possivel inserir os novos elementos em qualquer posicao da lista. 
	 * Por exemplo, a partir de uma lista com dois elementos [A,B] e de um array com um 
	 * elemento [Z], deve ser possivel obter a listas [A,B], [A,Z,B] ou [A,B,Z].
	 *   
	 * 
	 * @param posicao - a posicao onde inserir o primeiro elemento do array
	 * @param elementos - os elementos a serem inseridos na lista.
	 * 
	 * Restricoes
	 * - nao eh permitido usar os metodos insercao disponiveis na lista.
	 * 
	 * Validacoes:
	 * - caso a posicao seja invalida ou o array elementos for null, o metodo deve lancar uma adt.util.GenericException.
	 * - voce nao precisa se preocupar com possiveis elementos null no array
	 * 
	 */
	@Override
	public void inserirEmBatch(int posicao, T[] elementos) throws GenericException {
		
		if(elementos == null)
			throw new GenericException();
		
		int elements = 0;
		DoubleLinkedListNode<T> element = (DoubleLinkedListNode<T>) this.getHead();
		while(!element.isNIL()){
			elements++;
			element = (DoubleLinkedListNode<T>) element.getNext();
		}
		
		if(posicao > elements + 1)
			throw new GenericException();
		
		DoubleLinkedListNode<T> apontador = (DoubleLinkedListNode<T>) this.getHead(); // apontador virar single
		int cont = 0;
		while(!apontador.isNIL() && cont != posicao){
			cont++;
			apontador = (DoubleLinkedListNode<T>) apontador.getNext();
		}
		if(apontador.isNIL()){
			for(int i = 0; i < elementos.length; i++){
				if(i == 0){
					this.setHead(new DoubleLinkedListNode<T>(elementos[i], new DoubleLinkedListNode<T>(), new DoubleLinkedListNode<>()));
					apontador = (DoubleLinkedListNode<T>) this.getHead();
				}
				else
					apontador.setNext((SingleLinkedListNode<T>)elementos[i]);
				((DoubleLinkedListNode<T>)apontador.getNext()).setPrevious(apontador);
				apontador = (DoubleLinkedListNode<T>) apontador.getNext();
			}
			this.setLast(apontador);
			apontador.setNext(new DoubleLinkedListNode<>());
				
		}else if (!apontador.getPrevious().isNIL()){
				SingleLinkedListNode<T> previous = apontador.getPrevious();
				for(int i = 0; i < elementos.length; i++){
					previous.setNext((SingleLinkedListNode<T>)elementos[i]);
					((DoubleLinkedListNode<T>)previous.getNext()).setPrevious((DoubleLinkedListNode<T>) previous);
					previous = (SingleLinkedListNode<T>) previous.getNext();
				}// fazer a volta
				previous.setNext(apontador);
				apontador.setPrevious((DoubleLinkedListNode<T>) previous);
			}
			else{
				for(int i = elementos.length - 1; i >= 0; i--){
					apontador.setPrevious((DoubleLinkedListNode<T>)elementos[i]);
					apontador.getPrevious().setNext(apontador);
					apontador = apontador.getPrevious();
				}
				this.setHead(apontador);
				apontador.setPrevious(new DoubleLinkedListNode<>());
			}
		}


	/**
	 * Remove uma quantidade de elementos da lista a partir de determinada posicao (inclusive).
	 * Ex: lista = lista = [3,9,4,2].
	 * removerEmBatch(1,3)  modifica lista para [3].
	 * 
	 * @param posicao
	 * @param quantidade
	 * 
	 * Validacoes:
	 * - caso a posicao seja invalida na lista ou a quantidade a ser removida ultrapasse
	 *   o final da lista, o metodo deve lancar uma adt.util.GenericException.
	 *   
	 * Restricoes:
	 * - nao eh permitido usar os metodos de remocao disponiveis na lista
	 */
	@Override
	public void removerEmBatch(int posicao, int quantidade) throws GenericException {
		
		if((posicao + quantidade) > this.size()) /// -1
			throw new GenericException();
		
		int cont = 0;
		SingleLinkedListNode<T> apontador =  this.getHead();
		while(cont != posicao){
			cont ++;
			apontador = apontador.getNext();
		}
		DoubleLinkedListNode<T> firstPart = ((DoubleLinkedListNode<T>)apontador).getPrevious();
		SingleLinkedListNode<T> secondPart = apontador;
		int j = 0;
		while(j < quantidade){
			secondPart = secondPart.getNext();
		}
		if(firstPart.isNIL() && secondPart.isNIL())
			this.setHead(new DoubleLinkedListNode<>());
		else{
			firstPart.setNext(secondPart);
			((DoubleLinkedListNode<T>)secondPart).setPrevious(firstPart);
		}
	}
	
	
	/* 
	 * NAO MODIFIQUE NEM REMOVA ESTE METODO!!!
	 * 
	 * Use este metodo para fazer seus testes
	 * 
	 * Este metodo monta uma String contendo os elementos do primeiro ao ultimo, 
	 * comecando a navegacao pelo Head
	 */
	public String toStringFromHead() {
		
		String result = "";
		DoubleLinkedListNode<T> aNode = (DoubleLinkedListNode<T>)getHead();
		
		while(!aNode.isNIL()) {
			
			if (!result.isEmpty()) {
				result += " ";
			}
				
			result += aNode.getData();
			aNode = (DoubleLinkedListNode<T>) aNode.getNext();
			
		}
		
		return result;
	}
	
	/* 
	 * NAO MODIFIQUE NEM REMOVA ESTE METODO!!!
	 * 
	 * Use este metodo para fazer seus testes
	 * 
	 * Este metodo monta uma String contendo os elementos do primeiro ao ultimo, 
	 * porem comecando a navegacao pelo Last
	 * 
	 * Este metodo produz o MESMO RESULTADO de toStringFromHead() 
	 * 
	 */
	public String toStringFromLast() {
		
		String result = "";
		DoubleLinkedListNode<T> aNode = getLast();
		
		while(!aNode.isNIL()) {
			
			if (!result.isEmpty()) {
				result = " " + result;
			}
				
			result = aNode.getData() + result;
			aNode = (DoubleLinkedListNode<T>) aNode.getPrevious();
			
		}
		
		return result;
	}

	
	@Override
	public String toString() {
		return toStringFromHead();
	}

}
