package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;

import static util.Util.swap;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte:
 * 1. Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2. Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3. Adotar o A[center] como pivô.
 * 4. Colocar o pivô na penúltima posição A[right-1].
 * 5. Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6. Aplicar o algoritmo na particao a esquerda e na particao a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends
		AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if ((leftIndex >= 0 | rightIndex < array.length) & leftIndex < rightIndex & array.length != 0) {
			int i = partition(array, leftIndex, rightIndex);
			sort(array, leftIndex, i);
			sort(array, i + 1, rightIndex);
		}
	}

	public int partition(T[] array, int l, int r) {
		int med = mediana(array, l, r);
		swap(array, l, med);

		T pivot = array[l];
		int i = l;
		for (int j = i+1; j <= r; j++) {
			if (pivot.compareTo(array[j]) > 0) {
				i++;
				swap(array, i, j);
			}
		}
		swap(array, i, l);
		return i;
	}

	private int mediana(T[] array, int l, int r) {
		T a = array[l];
		T b = array[(l+r)/2];
		T c = array[r];
		if (a.compareTo(b) < 0) {
			if (b.compareTo(c) < 0) {
				return (l+r)/2;
			}
			else {
				if (a.compareTo(c) < 0) {
					return r;
				}
				else {
					return l;
				}
			}
		}
		else {
			if (c.compareTo(b) < 0) {
				return (l+r)/2;
			}
			else {
				if (c.compareTo(a) < 0) {
					return r;
				}
				else {
					return l;
				}
			}
		}
	}
}
