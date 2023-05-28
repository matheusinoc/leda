package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala.
 *
 * Procure evitar desperdício de memória: AO INVÉS de alocar o array de contadores
 * com um tamanho arbitrariamente grande (por exemplo, com o maior valor de entrada possível),
 * aloque este array com o tamanho sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Seu algoritmo deve assumir que o array de entrada nao possui numeros negativos,
 * ou seja, possui apenas numeros inteiros positivos e o zero.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex & array.length > 0) {
			int[] cumulativa = arrayFrequencia(array, leftIndex, rightIndex);

			cumulativa(cumulativa);

			Integer[] arrayOrdenado = arrayOrdenado(array, cumulativa, leftIndex, rightIndex);

			insereArray(array, arrayOrdenado, leftIndex);
		}
	}

	private int max(Integer[] array) {
		Integer max = array[0];

		for (Integer i : array) if (i > max) max = i;

		return max;
	}

	private int[] arrayFrequencia(Integer[] array, int leftIndex, int rightIndex) {
		int max = max(array);
		int[] arrayFrequencia = new int[max + 1];
		for (int i = leftIndex; i <= rightIndex; i++) {
			arrayFrequencia[array[i]] += 1;
		}
		return arrayFrequencia;
	}

	private void cumulativa(int[] cumulativa){
		for (int i = 1; i < cumulativa.length; i++) cumulativa[i] += cumulativa[i-1];
	}

	private Integer[] arrayOrdenado(Integer[] array, int[] cumulativa, int leftIndex, int rightIndex) {
		Integer[] arrayOrdenado = new Integer[rightIndex-leftIndex+1];

		for (int i = rightIndex; i >= leftIndex; i--) {
			arrayOrdenado[cumulativa[array[i]]-1] = array[i];
			cumulativa[array[i]] -= 1;
		}

		return arrayOrdenado;
	}

	private void insereArray(Integer[] array, Integer[] arrayVazio, int initIndex) {
		for (int i = 0; i < arrayVazio.length; i++) {
			array[initIndex + i] = arrayVazio[i];
		}
	}
}
