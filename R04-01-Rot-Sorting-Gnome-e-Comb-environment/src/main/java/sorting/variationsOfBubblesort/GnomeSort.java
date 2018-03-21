package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if(array == null)
			return;
		if(array.length == 0)
			return;
		if(!validaParametros(array.length, leftIndex, rightIndex))
			return;
		
		if(rightIndex >= leftIndex + 1){
		
			int indexPivot = leftIndex + 1;
			while((indexPivot + 1) <= rightIndex){
				if(array[indexPivot].compareTo(array[indexPivot + 1]) <= 0){
					indexPivot++;
				}
				else{
					Util.swap(array, indexPivot, indexPivot + 1);
					if(indexPivot == leftIndex)
						indexPivot++;
					else
						indexPivot--;
				}
			}
		
		}
		else
			return;
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
