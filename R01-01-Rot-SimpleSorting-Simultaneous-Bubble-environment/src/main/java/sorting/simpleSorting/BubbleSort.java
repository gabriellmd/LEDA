package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

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
		
		for (int i = leftIndex; i <= rightIndex; i++){
			for(int j = leftIndex; j < rightIndex - i; j++){
				if(array[j].compareTo(array[j + 1]) > 0){
					Util.swap(array, j, j + 1);
				}
			}
		} 
	}
}
