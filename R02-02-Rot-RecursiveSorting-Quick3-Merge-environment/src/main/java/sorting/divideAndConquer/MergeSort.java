package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if ((leftIndex >= 0 | rightIndex < array.length) & leftIndex < rightIndex & array.length != 0) {
			int meio = (leftIndex+rightIndex)/2;
			sort(array, leftIndex, meio);
			sort(array, meio+1, rightIndex);
			merge(array, leftIndex, meio, rightIndex);
		}
	}

	public void merge(T[] array, int l, int meio, int r) {
		T[] aux = array.clone();

		int i = l;
		int j = meio+1;
		int k = l;

		while (i <= meio & j <= r) {
			if (aux[i].compareTo(aux[j]) <= 0) {
				array[k++] = aux[i++];
			}
			else {
				array[k++] = aux[j++];
			}
		}

		while (i <= meio) {
			array[k++] = aux[i++];
		}

		while (j <= r) {
			array[k++] = aux[j++];
		}
	}
}
