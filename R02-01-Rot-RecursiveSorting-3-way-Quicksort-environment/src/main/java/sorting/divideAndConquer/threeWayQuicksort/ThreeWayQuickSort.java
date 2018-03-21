package sorting.divideAndConquer.threeWayQuicksort;

import sorting.AbstractSorting;
import util.Util;

public class ThreeWayQuickSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * No algoritmo de quicksort, selecionamos um elemento como pivot,
	 * particionamos o array colocando os menores a esquerda do pivot 
	 * e os maiores a direita do pivot, e depois aplicamos a mesma estrategia 
	 * recursivamente na particao a esquerda do pivot e na particao a direita do pivot. 
	 * 
	 * Considerando um array com muitoe elementos repetidos, a estrategia do quicksort 
	 * pode ser otimizada para lidar de forma mais eficiente com isso. Essa melhoria 
	 * eh conhecida como quicksort tree way e consiste da seguinte ideia:
	 * - selecione o pivot e particione o array de forma que
	 *   * arr[l..i] contem elementos menores que o pivot
	 *   * arr[i+1..j-1] contem elementos iguais ao pivot.
	 *   * arr[j..r] contem elementos maiores do que o pivot. 
	 *   
	 * Obviamente, ao final do particionamento, existe necessidade apenas de ordenar
	 * as particoes contendo elementos menores e maiores do que o pivot. Isso eh feito
	 * recursivamente. 
	 **/
	
	private void quick(T[] array, int leftIndex, int rightIndex){
			
			
			int indexPivot = leftIndex;
			int i = leftIndex;
			
			for(int j = leftIndex + 1; j <= rightIndex; j++){
				
				if(array[indexPivot].compareTo(array[j]) > 0){
					i++;
					Util.swap(array, i, j);
				}
			}
			Util.swap(array, leftIndex, i);
		}
	
	
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		
		if(array == null)
			return;
		if(array.length == 0)
			return;
		if(leftIndex < 0 || rightIndex < 0)
			return;
		if(leftIndex > rightIndex)
			return;
		if(rightIndex > array.length - 1)
			return;
		
		T pivot = array[leftIndex];
		int i = leftIndex;
		int j = rightIndex;
		int k = leftIndex;
		
		while(k <= j){
			
			if(array[k].compareTo(pivot) < 0){
				Util.swap(array, k, i);
				k++;
				i++;
			}
			else if(array[k].compareTo(pivot) > 0){
				Util.swap(array, j, k);
				j--;
			}
			else{
				k++;
			}	
		}
		sort(array, leftIndex, i - 1);
		sort(array, j + 1, rightIndex);
	}

}
