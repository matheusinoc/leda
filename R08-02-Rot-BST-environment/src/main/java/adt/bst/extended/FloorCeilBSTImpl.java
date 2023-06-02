package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {

	@Override
	public Integer floor(Integer[] array, double numero) {
		for (Integer n : array)
			insert(n);

		return floor(numero, root, null);
	}

	private Integer floor(double numero, BSTNode<Integer> node, Integer floor) {
		if (!node.isEmpty()) {
			if (node.getData().equals(numero)) floor = node.getData();
			else if (node.getData() < numero) floor = floor(numero, (BSTNode<Integer>) node.getRight(), node.getData());
			else floor = floor(numero, (BSTNode<Integer>) node.getLeft(), floor);
		}

		return floor;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		for (Integer n : array)
			insert(n);

		return ceil(numero, root, null);
	}

	private Integer ceil(double numero, BSTNode<Integer> node, Integer ceil) {
		if (!node.isEmpty()) {
			if (node.getData().equals(numero)) ceil = node.getData();
			else if (node.getData() > numero) ceil = ceil(numero, (BSTNode<Integer>) node.getLeft(), node.getData());
			else ceil = ceil(numero, (BSTNode<Integer>) node.getRight(), ceil);

		}

		return ceil;
	}

}
