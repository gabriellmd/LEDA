package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		
		if(array == null)
			return;
		if(array.length == 0)
			return;
		if(leftIndex > rightIndex)
			return;
		if(leftIndex < 0 || rightIndex < 0)
			return;
		if(rightIndex >= array.length)
			return;
		
		//Achando o maior elemento
		int maiorElemento = array[leftIndex];
		for(int i = leftIndex; i <= rightIndex; i++){
			if( maiorElemento < array[i])
				maiorElemento = array[i];
		}
		
		
		// Criando um array auxiliar e setando todos seus valores com 0.
		Integer[] aux = new Integer[maiorElemento + 1];
		for(int i = 0; i < aux.length; i++)
			aux[i] = 0;
	
		
		// Contando quantas vezes o valor se repete no array original.
		for(int i = leftIndex; i<= rightIndex; i++)
			aux[array[i]] += 1;	
		/**
		//Fazendo a acumulada do array auxiliar.
		for(int i = 1; i< aux.length; i++){
			aux[i] = aux[i] + aux[i - 1];
		}
	
		// Ordenando os valores.
		Integer[] ordenado = new Integer[array.length];
		for(int i = rightIndex; i >= leftIndex; i--){
			ordenado[aux[array[i]] - 1] = array[i];
			aux[array[i]] -= 1;
		}

		// Igualando o array original com o ordenado.
		for(int i = leftIndex; i <= rightIndex; i++){
			array[i] = ordenado[i];
		}
		**/
		
		
		// Ordenando o array original.
		int j = leftIndex;
		for(int i = 0; i < aux.length; i++){
			if(aux[i] > 0){
				while(aux[i] > 0){
					array[j] = i;//
					aux[i] = aux[i] - 1;
					j++;
				}
			}	
		}
		
	
	
	}
	
}
