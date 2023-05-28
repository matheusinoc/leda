package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;

import static util.Util.swap;

/**
 * This bubble sort variation has two internal iterations. In the first, it
 * pushes big elements to the right, like the normal bubble sort does. Then in
 * the second, iterates the array backwards, that is, from right to left, while
 * pushing small elements to the left. This process is repeated until the array
 * is sorted.
 */
public class BidirectionalBubbleSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if ((array.length != 0) && !(leftIndex < 0 | rightIndex > array.length-1 | rightIndex < leftIndex)) {
			sortDireita(array, leftIndex, rightIndex);
			sortEsquerda(array, leftIndex, rightIndex);
		}
	}

	public void sortDireita(T[] array, int leftIndex, int rightIndex) {
		boolean houveMudanca = true;
		while (houveMudanca) {
			houveMudanca = false;
			for (int i = leftIndex; i < rightIndex; i++) {
				if (array[i].compareTo(array[i+1]) > 0) {
					swap(array, i, i+1);
					houveMudanca = true;
				}
			}
		}
	}

	public void sortEsquerda(T[] array, int leftIndex, int rightIndex) {
		boolean houveMudanca = true;
		while (houveMudanca) {
			houveMudanca = false;
			for (int i = rightIndex; i < leftIndex; i--) {
				if (array[i].compareTo(array[i-1]) < 0) {
					swap(array, i, i-1);
					houveMudanca = true;
				}
			}
		}
	}
}
