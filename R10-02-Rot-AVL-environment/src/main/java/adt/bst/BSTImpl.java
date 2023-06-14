package adt.bst;

import java.util.ArrayList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(getRoot());
	}

	public int height(BSTNode<T> node) {
		if (node.isEmpty()) return -1;
		else return 1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight()));
	}


	@Override
	public BSTNode<T> search(T element) {
		if (element == null) return new BSTNode<>();
		else return search(element, getRoot());
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		if (node.isEmpty() || element.equals(node.getData())) return node;
		else if (element.compareTo(node.getData()) < 0) return search(element, (BSTNode<T>) node.getLeft());
		else return search(element, (BSTNode<T>) node.getRight());
	}

	@Override
	public void insert(T element) {
		insert(element, getRoot());
	}

	private void insert(T element, BSTNode<T> node) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode.Builder<T>().parent(node).build());
			node.setRight(new BSTNode.Builder<T>().parent(node).build());
		} else {
			if (element.compareTo(node.getData()) < 0) insert(element, (BSTNode<T>) node.getLeft());
			else insert(element, (BSTNode<T>) node.getRight());
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (this.isEmpty()) return null;
		else return maximum(getRoot());
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		while(!node.getRight().isEmpty())
			node = (BSTNode<T>) node.getRight();

		return node;
	}

	@Override
	public BSTNode<T> minimum() {
		if (this.isEmpty()) return null;
		else return minimum(getRoot());
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		while(!node.getLeft().isEmpty())
			node = (BSTNode<T>) node.getLeft();

		return node;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);

		if (node.isEmpty()) return null;

		if (!node.getRight().isEmpty()) {
			return minimum((BSTNode<T>) node.getRight());
		} else {
			BSTNode<T> aux = (BSTNode<T>) node.getParent();
			while (aux != null && aux.getData().compareTo(node.getData()) <= 0)
				aux = (BSTNode<T>) aux.getParent();

			return aux;
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);

		if (node.isEmpty()) return null;

		if (!node.getLeft().isEmpty()) {
			return maximum((BSTNode<T>) node.getLeft());
		} else {
			BSTNode<T> aux = (BSTNode<T>) node.getParent();
			while (aux != null && aux.getData().compareTo(node.getData()) >= 0)
				aux = (BSTNode<T>) aux.getParent();

			return aux;
		}
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);

		if (!node.isEmpty()) {

			if (node.isLeaf()) {
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
			} else if (node.getLeft().isEmpty() || node.getRight().isEmpty()) {

				BSTNode<T> child = (BSTNode<T>) (!node.getLeft().isEmpty() ? node.getLeft() : node.getRight());

				if(!node.equals(getRoot())) {
					child.setParent(node.getParent());
					if (isLeftChild(node)) node.getParent().setLeft(child);
					else node.getParent().setRight(child);
				} else {
					this.root = child;
					root.setParent(null);
				}

			} else {
				T sucessor = sucessor(node.getData()).getData();
				remove(sucessor);
				node.setData(sucessor);
			}

		}
	}

	private boolean isLeftChild(BSTNode<T> node) {
		return node.getParent().getLeft().equals(node);
	}

	@Override
	public T[] preOrder() {
		ArrayList<T> lista = new ArrayList<>();
		preOrder(getRoot(), lista);
		return (T[]) lista.toArray(new Comparable[lista.size()]);
	}

	private void preOrder(BSTNode<T> node, ArrayList<T> lista) {
		if (!node.isEmpty()) {
			lista.add(node.getData());
			preOrder((BSTNode<T>) node.getLeft(), lista);
			preOrder((BSTNode<T>) node.getRight(), lista);
		}
	}

	@Override
	public T[] order() {
		ArrayList<T> lista = new ArrayList<>();
		order(getRoot(), lista);
		return (T[]) lista.toArray(new Comparable[lista.size()]);
	}

	private void order(BSTNode<T> node, ArrayList<T> lista) {
		if (!node.isEmpty()) {
			order((BSTNode<T>) node.getLeft(), lista);
			lista.add(node.getData());
			order((BSTNode<T>) node.getRight(), lista);
		}
	}

	@Override
	public T[] postOrder() {
		ArrayList<T> lista = new ArrayList<>();
		postOrder(getRoot(), lista);
		return (T[]) lista.toArray(new Comparable[lista.size()]);
	}

	private void postOrder(BSTNode<T> node, ArrayList<T> lista) {
		if (!node.isEmpty()) {
			postOrder((BSTNode<T>) node.getLeft(), lista);
			postOrder((BSTNode<T>) node.getRight(), lista);
			lista.add(node.getData());
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
