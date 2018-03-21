package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if(leftIndex > rightIndex)
			return;
		if(array == null)
			return;
		if(leftIndex < 0 || rightIndex < 0)
			return;
		if(array.length < leftIndex)
			return;
		if(array.length == 0)
			return;
		
		int indiceMenor;
		for(int i = leftIndex; i <= rightIndex; i ++){
			indiceMenor = i;
			for(int j = i + 1; j <= rightIndex; j++){
				if(array[indiceMenor].compareTo(array[j]) > 0){
					indiceMenor = j;
				}
			}
			Util.swap(array, indiceMenor, i);
		}
	}
}
