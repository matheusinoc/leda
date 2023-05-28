package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex & array.length > 0) {

			int min = min(array, leftIndex, rightIndex);
			int max = max(array, leftIndex, rightIndex);

			int shift = array[min] * -1;

			int size = array[max] - array[min] + 1;

			int[] cumulativa = arrayFrequencia(array, leftIndex, rightIndex, size, shift);

			cumulativa(cumulativa);

			Integer[] arrayOrdenado = arrayOrdenado(array, cumulativa, leftIndex, rightIndex, shift);

			// E por fim, insere no array principal a sequência já ordenada, passando para o array original o array B.
			insereArray(array, arrayOrdenado, leftIndex);
		}
	}

	private int min(Integer[] array, int init, int end) {
		int min = init;

		for (int i = init; i <= end; i++) if (array[i] < array[min]) min = i;

		return min;
	}

	private int max(Integer[] array, int init, int end){
		int max = init;

		for (int i = init; i <= end; i++) if (array[i] > array[max]) max = i;

		return max;
	}

	private int[] arrayFrequencia(Integer[] array, int leftIndex, int rightIndex, int size, int shift) {
		int[] arrayFrequencia = new int[size];
		for (int i = leftIndex; i <= rightIndex; i++) {
			arrayFrequencia[array[i] + shift] += 1;
		}
		return arrayFrequencia;
	}

	private void cumulativa(int[] cumulativa){
		for (int i = 1; i < cumulativa.length; i++) cumulativa[i] += cumulativa[i-1];
	}

	private Integer[] arrayOrdenado(Integer[] array, int[] cumulativa, int leftIndex, int rightIndex, int shift) {
		Integer[] arrayOrdenado = new Integer[rightIndex-leftIndex+1];

		for (int i = rightIndex; i >= leftIndex; i--) {
			arrayOrdenado[cumulativa[array[i] + shift] - 1] = array[i];
			cumulativa[array[i] + shift] -= 1;
		}

		return arrayOrdenado;
	}

	private void insereArray(Integer[] array, Integer[] outroArray, int initIndex) {
		for (int i = 0; i < outroArray.length; i++) {
			array[initIndex + i] = outroArray[i];
		}
	}
}
