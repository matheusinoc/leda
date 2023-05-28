package sorting.divideAndConquer;

import sorting.AbstractSorting;

import static util.Util.swap;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if ((leftIndex >= 0 | rightIndex < array.length) & leftIndex < rightIndex & array.length != 0) {
			T pivot = array[leftIndex];

			int i = leftIndex;
			for (int j = i+1; j <= rightIndex; j++) {
				if (pivot.compareTo(array[j]) > 0) {
					i++;
					swap(array, i, j);
				}
			}

			swap(array, i, leftIndex);

			sort(array, leftIndex, i);
			sort(array, i+1, rightIndex);
		}
	}
}
