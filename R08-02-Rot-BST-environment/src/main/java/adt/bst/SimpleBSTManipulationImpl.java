package adt.bst;

/**
 * - Esta eh a unica classe que pode ser modificada 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		return this.equals((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
	}

	private boolean equals(BSTNode<T> node1, BSTNode<T> node2) {
		boolean res = false;

		if (node1.equals(node2)) {
			if (!node1.isEmpty() && !node2.isEmpty()) {
				res = this.equals((BSTNode<T>) node1.getLeft(), (BSTNode<T>) node2.getLeft())
						&& this.equals((BSTNode<T>) node1.getRight(), (BSTNode<T>) node2.getRight());
			} else {
				res = true;
			}
		}

		return res;
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		return this.isSimilar((BSTNode<T>) tree1.getRoot(), (BSTNode<T>) tree2.getRoot());
	}

	private boolean isSimilar(BSTNode<T> node1, BSTNode<T> node2) {
		boolean res;

		if (!node1.isEmpty() && !node2.isEmpty())
			res = this.isSimilar((BSTNode<T>) node1.getLeft(), (BSTNode<T>) node2.getLeft())
					&& this.isSimilar((BSTNode<T>) node1.getRight(), (BSTNode<T>) node2.getRight());
		else
			res = node1.isEmpty() && node2.isEmpty();

		return res;
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		T res = null;

		if (k >= 1 && k <= tree.size()) {
			BSTNode<T> min = tree.minimum();

			if (k == 1) res = min.getData();
			else if (k == tree.size()) res = tree.maximum().getData();
			else res = this.orderStatistic(tree, min, k);
		}

		return res;
	}

	private T orderStatistic(BST<T> tree, BSTNode<T> node, int k) {
		if (k == 1) return node.getData();
		else return orderStatistic(tree, tree.sucessor(node.getData()), --k);
	}

}
