package sorting.divideAndConquer;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	
	private void merge(T[] array, int leftIndex, int middleIndex, int rightIndex){
		
		T[] arrayAux = Arrays.copyOf(array, array.length);
		
		int i = leftIndex;
		int j = middleIndex + 1;
		int k = leftIndex;
		while(i <= middleIndex && j <= rightIndex){
			
			if(arrayAux[i].compareTo(arrayAux[j]) < 0){
				array[k] = arrayAux[i];
				i++;
			}
			else{
				array[k] = arrayAux[j];
				j++;
			}
			k++;
		}
		
		while(i <= middleIndex){
			array[k] = arrayAux[i];
			i++;
			k++;
		}
		
		while(j <= rightIndex){
			array[k] = arrayAux[j];
			j++;
			k++;
		}
		
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
		
		if(leftIndex < rightIndex){
			int mid = ((rightIndex + leftIndex) / 2);
			
			sort(array, leftIndex, mid);
			sort(array, mid + 1, rightIndex);
	
			merge(array, leftIndex, mid, rightIndex);
		}
	}
}
