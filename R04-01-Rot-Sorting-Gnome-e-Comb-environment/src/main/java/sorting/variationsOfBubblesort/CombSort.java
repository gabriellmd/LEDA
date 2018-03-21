package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if(array == null)
			return;
		if(array.length == 0)
			return;
		if(!validaParametros(array.length, leftIndex, rightIndex))
			return;
		
		
		final double fator = 1.25;
		
		int gap = rightIndex - leftIndex + 1;
		
		boolean trocou = true;
		while(gap > 1 || trocou){
			
			if(gap > 1){
				gap = (int) (gap/ fator);
			}
			
			int i = 0;
			trocou = false;
			while(i + gap < (rightIndex - leftIndex + 1)){
				if(array[i].compareTo(array [i + gap]) > 0){
					Util.swap(array, i, i + gap);
					trocou = true;
				}
				i++;
			}
		}	
	}
	
	private static boolean validaParametros(int arrayLength, int leftIndex, int rightIndex){
			
			boolean valido = true;
		
			if(leftIndex < 0 || rightIndex < 0)
				valido = false;
			if(rightIndex >= arrayLength)
				valido = false;
			if(leftIndex > rightIndex)
				valido = false;
			
			return valido;	
		}
}
