package sorting.simpleSorting;

import sorting.AbstractSorting;

import static util.Util.swap;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if ((array.length != 0) && !(leftIndex < 0 | rightIndex > array.length-1 | rightIndex < leftIndex)) {

		}

		for (int i = leftIndex; i < rightIndex+1; i++) {
			for (int j = i; j > leftIndex; j--) {
				if (array[j].compareTo(array[j-1]) < 0) {
					swap(array, j, j-1);
				}
			}
		}
	}
}
